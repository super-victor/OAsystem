package com.sicnu.oasystem.service.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderClassfyMapper;
import com.sicnu.oasystem.mapper.CardHolderMapper;
import com.sicnu.oasystem.mapper.EmployeeCardHolderMapper;
import com.sicnu.oasystem.pojo.CardHolder;
import com.sicnu.oasystem.pojo.CardHolderClassfy;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.EmployeeCardHolder;
import com.sicnu.oasystem.service.card.CardHolderService;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName CardHolderService
 * @Description 名片夹服务实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:32
 * @Version v1.0
 */

@Service
@Slf4j
public class CardHolderServiceImpl implements CardHolderService {

    @Resource
    CardHolderMapper cardHolderMapper;

    @Resource
    EmployeeCardHolderMapper employeeCardHolderMapper;

    @Resource
    CardHolderClassfyMapper cardHolderClassfyMapper;

    @Override
    public BackFrontMessage insertCardHolder(CardHolder cardHolder, Integer cardHolderClassfyId){
        //添加分类时先判断是不是它的分类
        if (!hasCardHolderClassfy(cardHolderClassfyId)) { //不含此分类
            return new BackFrontMessage(500,"您没有此分类，不能选择此分类!",null);
        }
        int counter = cardHolderMapper.insertCardHolder(cardHolder);
        if (counter > 0){
            //并将此文件夹添加到职工文件夹对应表中
//            log.info("cardHolderId --> "+cardHolder.getCardHolderId());
            Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
            EmployeeCardHolder employeeCardHolder = new EmployeeCardHolder();
            employeeCardHolder.setCardHolderId(cardHolder.getCardHolderId());
            employeeCardHolder.setEmployeeId(currentEmployee.getEmployeeId());
            employeeCardHolder.setCardHolderClassfyId(cardHolderClassfyId);
            int counter2 = employeeCardHolderMapper
                    .insertEmployeeCardHolder(employeeCardHolder);
            if (counter2 > 0){
                return new BackFrontMessage(200,"添加成功",cardHolder.getCardHolderId());
            }
            return new BackFrontMessage(200,"名片夹职工对应表添加失败",null);
        } else {
            return new BackFrontMessage(500,"名片夹表添加失败",null);
        }
    }

    /**
     * @MethodName hasCardHolderClassfy
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 判断职工是否拥有名片夹分类
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/16
     */
    private boolean hasCardHolderClassfy(Integer cardHolderClassfyId) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        CardHolderClassfy cardHolderClassfy = cardHolderClassfyMapper.findCardHolderClassfyByCardHolderClassfyIdAndEmployeeId(
                cardHolderClassfyId, currentEmployee.getEmployeeId());
        return cardHolderClassfy != null;
    }

    @Override
    public BackFrontMessage deleteCardHolder(int cardHolderId) {
        int result = cardHolderMapper.deleteCardHolderByCardHolderId(cardHolderId);
        if (result > 0){
            return new BackFrontMessage(200,"删除成功",null);
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    @Override
    public BackFrontMessage updateCardHolder(CardHolder cardHolder, int cardHolderId) {
        cardHolder.setCardHolderId(cardHolderId);
        int counter = cardHolderMapper.updateCardHolderByCardHolderId(cardHolder);
        if (counter > 0){
            return new BackFrontMessage(200,"修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }


}
