package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderClassfyMapper;
import com.sicnu.oasystem.mapper.CardHolderMapper;
import com.sicnu.oasystem.pojo.CardHolder;
import com.sicnu.oasystem.pojo.CardHolderClassfy;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CardHolderService
 * @Description 名片夹服务实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:32
 * @Version v1.0
 */

@Service
public class CardHolderServiceImpl implements CardHolderService{

    @Resource
    CardHolderMapper cardHolderMapper;

    @Resource
    CardHolderClassfyMapper cardHolderClassfyMapper;

    /**
     * @MethodName insertCardHolder
     * @param ownedId
     * @param cardHolderClassfyId
     * @Description 添加名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage insertCardHolder(int ownedId, int cardHolderClassfyId){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        //判断该职工是否已经拥有此名片夹，拥有则返回名片夹的名片夹分类
        int rest_cardHolderClassfyId = hasOwnedCardHolder(currentEmployee.getEmployeeId(), ownedId);
        if (rest_cardHolderClassfyId != 0){
            CardHolderClassfy cardHolderClassfy = cardHolderClassfyMapper.findCardHolderClassfyByCardHolderClassfyId(rest_cardHolderClassfyId);
            return new BackFrontMessage(500,"您已拥有此职工的名片夹",cardHolderClassfy.getName());
        }
        //新增
        CardHolder cardHolder = new CardHolder();
        cardHolder.setOwnedId(ownedId);
        cardHolder.setOwnerId(currentEmployee.getEmployeeId());
        cardHolder.setCardHolderClassfyId(cardHolderClassfyId);
        int counter = cardHolderMapper.insertCardHolderByOwnedId(cardHolder);
        if (counter > 0){
            return new BackFrontMessage(200,"添加成功",cardHolder.getCardHolderId());
        }
        else{
            return new BackFrontMessage(500,"添加失败",cardHolder.getCardHolderId());
        }
    }

    /**
     * @MethodName hasOwnedCardHolder
     * @param ownerId
     * @param ownedId
     * @Description 判断是否已经拥有某一职工的名片夹
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/8
     */
    public int hasOwnedCardHolder(int ownerId, int ownedId){
        CardHolder cardHolder = cardHolderMapper.findCardHolderByOwnedId(ownerId, ownedId);
        if (cardHolder != null) {
            return cardHolder.getCardHolderClassfyId();
        }
        else{
            return 0;
        }
    }

    /**
     * @MethodName findCardHolderByOwnerId
     * @param
     * @Description 查找职工所拥有的全部名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage findCardHolderByOwnerId() {
        Employee currentEmployeeId = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<CardHolder> cardHolderList = cardHolderMapper.findCardHolderByEmployeeId(currentEmployeeId.getEmployeeId());
        return null;
    }

    /**
     * @MethodName findCardHolderByOwnedId
     * @param ownedId
     * @Description 查找职工拥有的另一个职工的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage findCardHolderByOwnedId(int ownedId) {
        return null;
    }

    /**
     * @MethodName findCardHolderByCardHolderClassfyId
     * @param cardHolderClassfyId
     * @Description 查找职工某一分类下的所有名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage findCardHolderByCardHolderClassfyId(int cardHolderClassfyId) {
        return null;
    }

    /**
     * @MethodName deleteCardHolder
     * @param cardHolderId
     * @Description 删除名片夹by名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage deleteCardHolder(int cardHolderId) {
        int result = cardHolderMapper.deleteCardHolderByCardHolderId(cardHolderId);
        if (result > 0){
            return new BackFrontMessage(200,"删除成功",null);
        }
        else{
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    /**
     * @MethodName updateCardHolderAboutClassfy
     * @param cardHolderClassfyId
     * @Description 修改名片夹所在分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage updateCardHolderAboutClassfy(int cardHolderId, int cardHolderClassfyId) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        if (hasOwnedCardHolderClassfy(currentEmployee.getEmployeeId(), cardHolderClassfyId)) {
            int counter = cardHolderMapper.updateCardHolderByCardHolderId(cardHolderId, cardHolderClassfyId);
            if (counter > 0){
                return new BackFrontMessage(200,"修改成功",null);
            }
            else{
                return new BackFrontMessage(500,"修改失败",null);
            }
        }
        return new BackFrontMessage(500,"修改失败,您还未拥有此名片夹分类",null);
    }

    /**
     * @MethodName hasOwnedCardHolderClassfy
     * @param employeeId
     * @param cardHolderClassfyId
     * @Description 查找该职工是否拥有此名片夹分类
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/8
     */
    public boolean hasOwnedCardHolderClassfy(int employeeId, int cardHolderClassfyId){
        CardHolderClassfy cardHolderClassfy = cardHolderClassfyMapper.findCardHolderClassfyByCardHolderClassfyId(cardHolderClassfyId);
        if (cardHolderClassfy != null) {
            return cardHolderClassfy.getOwnerId() == employeeId;
        }
        else{
            return false;
        }
    }

}
