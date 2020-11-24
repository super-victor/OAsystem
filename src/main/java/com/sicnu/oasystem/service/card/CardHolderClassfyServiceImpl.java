package com.sicnu.oasystem.service.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderClassfyMapper;
import com.sicnu.oasystem.mapper.EmployeeCardHolderMapper;
import com.sicnu.oasystem.pojo.CardHolderClassfy;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.EmployeeCardHolder;
import com.sicnu.oasystem.service.card.CardHolderClassfyService;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CardHolderClassfyService
 * @Description 名片夹分类服务实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/6 17:00
 * @Version v1.0
 */

@Service
@Slf4j
public class CardHolderClassfyServiceImpl implements CardHolderClassfyService {

    @Resource
    CardHolderClassfyMapper cardHolderClassfyMapper;


    @Resource
    EmployeeCardHolderMapper employeeCardHolderMapper;

    @Override
    public BackFrontMessage findCardHolderClassfyByEmployeeId(){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<CardHolderClassfy> list = cardHolderClassfyMapper.findCardHolderClassfysByEmployeeId(currentEmployee.getEmployeeId());
        //将默认名片夹放在第一位
        int location = 0;
        for (CardHolderClassfy cardHolderClassfy : list) {
            if ("默认名片夹".equals(cardHolderClassfy.getName())){
                break;
            }
            location++;
        }
        CardHolderClassfy defaultClassfy = list.get(location);
        CardHolderClassfy firstClassfy = list.get(0);
        list.set(0, defaultClassfy);
        list.set(location, firstClassfy);
        return new BackFrontMessage(200,"获取成功",list);
    }

    @Override
    public BackFrontMessage deleteCardHolderClassfyByCardHolderClassfyId(int cardHolderClassfyId){
        CardHolderClassfy cardHolderClassfy = cardHolderClassfyMapper
                .findCardHolderClassfyByCardHolderClassfyId(cardHolderClassfyId);
        if (("默认名片夹").equals(cardHolderClassfy.getName())){
            return new BackFrontMessage(500,"默认名片夹分类不可删除",null);
        }
        //获取“默认名片夹”的名片夹分类id
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        CardHolderClassfy defaultClassfy = cardHolderClassfyMapper
                .findCardHolderClassfyByName("默认名片夹", currentEmployee.getEmployeeId());
        //转移前判断是否需要转移
        List<EmployeeCardHolder> hasEmployeeCardHolder = employeeCardHolderMapper.findEmployeeCardHolderByCardHolderClassfyId(cardHolderClassfyId);
        boolean updateCardHolder = false;   //默认不需要转移
        if ( hasEmployeeCardHolder.size() != 0 ) {  //有需要转移的名片夹
            updateCardHolder = true;
        }
        //删除前先将此名片夹分类下的名片夹转移到“默认名片夹”下
        int transferCount = employeeCardHolderMapper.updateOldCardHolderClassfyIdByNewCardHolderClassfyId(
                cardHolderClassfyId, defaultClassfy.getCardHolderClassfyId());
        if (transferCount > 0 || !updateCardHolder){  //转移行数大于0，或者不需要转移
            int result = cardHolderClassfyMapper
                    .deleteCardHolderClassfyByCardHolderClassfyId(cardHolderClassfyId);
            if (result > 0){
                return new BackFrontMessage(200,"删除成功,该分类下的名片夹已转移至‘默认名片夹‘下",null);
            } else {
                return new BackFrontMessage(500,"删除失败",null);
            }
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    @Override
    public BackFrontMessage insertCardHolderClassfy(String name){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        //查看是否有重名的文件夹分类
        if (hasSameName(currentEmployee, name)){
            return new BackFrontMessage(500,"添加失败，您已经此名字的名片夹分类!",null);
        }
        CardHolderClassfy cardHolderClassfy = new CardHolderClassfy();
        cardHolderClassfy.setName(name);
        cardHolderClassfy.setOwnerId(currentEmployee.getEmployeeId());
        int counter = cardHolderClassfyMapper
                .insertCardHolderClassfyByCardHolderClassfyId(cardHolderClassfy);
        if (counter > 0){
            return new BackFrontMessage(200,"添加成功",cardHolderClassfy.getCardHolderClassfyId());
        } else {
            return new BackFrontMessage(500,"添加失败",null);
        }
    }

    @Override
    public BackFrontMessage updateCardHolderClassfyName(int cardHolderClassfyId, String name){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        if(hasSameName(currentEmployee, name)){
            return new BackFrontMessage(500,"修改失败，您已经此名字的名片夹分类!",null);
        }
        int result = cardHolderClassfyMapper.updateCardHolderClassfyNameByCardHolderClassfyId(cardHolderClassfyId, name);
        if (result == 1){
            return new BackFrontMessage(200,"修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }

    /**
     * @MethodName hasSameName
     * @param name 名片夹分类名称
     * @Description 判断某一职工是否拥有此名字的名片夹分类
     * @Author Waynejwei
     * @Return boolean 拥有则返回true
     * @LastChangeDate 2020/11/8
     */
    public boolean hasSameName(Employee currentEmployee, String name){
        CardHolderClassfy cardHolderClassfyByName = cardHolderClassfyMapper.findCardHolderClassfyByName(name, currentEmployee.getEmployeeId());
        return cardHolderClassfyByName != null;
    }
}
