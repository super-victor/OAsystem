package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderClassfyMapper;
import com.sicnu.oasystem.mapper.CardHolderMapper;
import com.sicnu.oasystem.pojo.CardHolder;
import com.sicnu.oasystem.pojo.CardHolderClassfy;
import com.sicnu.oasystem.pojo.Employee;
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
public class CardHolderClassfyServiceImpl implements CardHolderClassfyService{

    @Resource
    CardHolderClassfyMapper cardHolderClassfyMapper;

    @Resource
    CardHolderMapper cardHolderMapper;

    /**
     * @MethodName findCardHolderClassfyServiceByEmployeeId
     * @Description 获取某一职工的所用名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    @Override
    public BackFrontMessage findCardHolderClassfyByEmployeeId(){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        return new BackFrontMessage(200,"获取成功",cardHolderClassfyMapper.findCardHolderClassfysByEmployeeId(currentEmployee.getEmployeeId()));
    }

    /**
     * @MethodName deleteCardHolderClassfyByCardHolderClassfyId
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 通过名片夹分类id删除名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/7
     */
    @Override
    public BackFrontMessage deleteCardHolderClassfyByCardHolderClassfyId(int cardHolderClassfyId){
        CardHolderClassfy cardHolderClassfy = cardHolderClassfyMapper
                .findCardHolderClassfyByCardHolderClassfyId(cardHolderClassfyId);
        if (("他人名片夹").equals(cardHolderClassfy.getName())){
            return new BackFrontMessage(500,"默认名片夹分类不可删除",null);
        }
        //获取“他人名片夹”的名片夹分类id
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        CardHolderClassfy defaultClassfy = cardHolderClassfyMapper
                .findCardHolderClassfyByName("他人名片夹", currentEmployee.getEmployeeId());
        //转移前判断是否需要转移
        List<CardHolder> hasCardHoder = cardHolderMapper.findCardHolderByCardHolderClassfyId(cardHolderClassfyId);
        boolean updateCardHolder = false;   //默认不需要转移
        if ( hasCardHoder.size() != 0 ) {  //有需要转移的名片夹
            updateCardHolder = true;
        }
        //删除前先将此名片夹分类下的名片夹转移到“他人名片夹”下
        int transferCount = cardHolderMapper.updateCardHolderClassfyIdByOldCardHolderClassfyId(
                cardHolderClassfyId, defaultClassfy.getCardHolderClassfyId());
        if (transferCount > 0 || !updateCardHolder){  //转移行数大于0，或者不需要转移
            int result = cardHolderClassfyMapper
                    .deleteCardHolderClassfyByCardHolderClassfyId(cardHolderClassfyId);
            if (result > 0){
                return new BackFrontMessage(200,"删除成功,该分类下的名片夹已转移至‘他人名片夹‘下",null);
            } else {
                return new BackFrontMessage(500,"删除失败",null);
            }
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    /**
     * @MethodName insertCardHolderClassfy
     * @param name  名片夹分类名称
     * @Description 增加名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/7
     */
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

    /**
     * @MethodName updateCardHolderClassfyName
     * @param cardHolderClassfyId 名片夹分类id
     * @param name 名片夹分类名称
     * @Description 修改名片夹分类名称
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
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
