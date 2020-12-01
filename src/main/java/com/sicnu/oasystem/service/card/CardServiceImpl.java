package com.sicnu.oasystem.service.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderMapper;
import com.sicnu.oasystem.mapper.CardMapper;
import com.sicnu.oasystem.pojo.Card;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import com.sicnu.oasystem.util.ValidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CardServiceImpl
 * @Description 名片服务实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:32
 * @Version v1.0
 */

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    @Resource
    CardMapper cardMapper;


    @Resource
    CardHolderMapper cardHolderMapper;

    @Override
    public BackFrontMessage insertCard(Card card, Integer cardHolderId){
        if (!ValidUtil.isValidMobileNumber(card.getPhone())){
            return new BackFrontMessage(500,"请输入正确的电话格式",null);
        }
        if (card.getEmail() != null && !ValidUtil.isValidEmail(card.getEmail())){
            return new BackFrontMessage(500,"请输入正确的邮箱格式",null);
        }
        //添加名片夹时先判断是不是它的名片夹
        if (!hasCardHolder(cardHolderId)) { //不含此名片夹
            return new BackFrontMessage(500,"您没有此名片夹，不能选择此名片夹!",null);
        }
        if (hasOwnedCardByPhone(card.getPhone())){
            return new BackFrontMessage(500,"您已经拥有了此名片",null);
        }
        //拥有者为本人，不能给其他人添加名片
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        card.setOwnerId(currentEmployee.getEmployeeId());
        int counter = cardMapper.insertCard(card);
        if (counter > 0){
            return new BackFrontMessage(200,"添加成功", card.getCardId());
        } else {
            return new BackFrontMessage(500,"名片添加失败",null);
        }
    }

    @Override
    public BackFrontMessage deleteCard(int cardId) {
        if (!hasOwnedCardByCardId(cardId)) {
            return new BackFrontMessage(500,"您没有此名片",null);
        }
        int result = cardMapper.deleteCardByCardId(cardId);
        if (result > 0){
            return new BackFrontMessage(200,"删除成功",null);
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    @Override
    public BackFrontMessage updateCard(Card card, int cardId) {
        if (card.getPhone() != null && !ValidUtil.isValidMobileNumber(card.getPhone())){
            return new BackFrontMessage(500,"请输入正确的电话格式",null);
        }
        if (card.getEmail() != null && !ValidUtil.isValidEmail(card.getEmail())){
            return new BackFrontMessage(500,"请输入正确的邮箱格式",null);
        }
        if (!hasOwnedCardByCardId(cardId)) {
            return new BackFrontMessage(500,"您没有此名片",null);
        }
        card.setCardId(cardId);
        int counter = cardMapper.updateCardByCardId(card);
        if (counter > 0){
            return new BackFrontMessage(200,"修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }

    @Override
    public BackFrontMessage shareCard(int cardId, int cardHolderId) {
        //添加名片夹时先判断是不是它的名片夹
        if (!hasCardHolder(cardHolderId)) { //不含此名片夹
            return new BackFrontMessage(500,"您没有此名片夹，不能放在次名片夹!",null);
        }
        Card card = cardMapper.findCardByCardId(cardId);
        //通过电话号码判断是否添加了此名片
        if (hasOwnedCardByPhone(card.getPhone())){
            return new BackFrontMessage(500, "您已经拥有了此名片", null);
        }
        card.setCardHolderId(cardHolderId);
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        card.setOwnerId(currentEmployee.getEmployeeId());
        log.info("currentEmployeeId is --> "+currentEmployee.getEmployeeId());
        int result = cardMapper.insertCard(card);
        if (result <= 0){
            return new BackFrontMessage(500, "分享失败", null);
        }else{
            return new BackFrontMessage(200, "分享成功", card.getCardId());
        }
    }

    @Override
    public BackFrontMessage findCardByCardHolderId(int cardHolderId) {
        if (!hasCardHolder(cardHolderId)) {
            return new BackFrontMessage(500, "您没有此名片夹", null);
        }
        List<Map<String,Object>> cardList = new ArrayList<>();
        List<Card> list = cardMapper.findCardByCardHolderId(cardHolderId);
        for (Card card : list) {
            Map<String,Object> cardMap = new HashMap<>(16);
            cardMap.put("cardId",card.getCardId());
            cardMap.put("card",card);
            cardList.add(cardMap);
        }
        return new BackFrontMessage(200, "查找成功", cardList);
    }

    /**
     * @MethodName hasOwnedCard
     * @param phone 电话
     * @Description 每个人的电话都是唯一的，通过电话判断员工是否已经拥有此名片夹
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/25
     */
    private boolean hasOwnedCardByPhone(String phone) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<Card> hasOwned = cardMapper.findCardByPhone(phone, currentEmployee.getEmployeeId());
        return hasOwned.size() >= 1;
    }

    /**
     * @MethodName hasOwnedCardByCardId
     * @param cardId 名片id
     * @Description 通过名片id判断用户是否拥有名片
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/25
     */
    private boolean hasOwnedCardByCardId(int cardId){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        Card card = cardMapper.findCardByCardIdAndEmployeeId(cardId, currentEmployee.getEmployeeId());
        return card != null;
    }

    /**
     * @MethodName hasCardHolder
     * @param cardHolderId 名片夹id
     * @Description 判断职工是否拥有名片夹
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/16
     */
    private boolean hasCardHolder(Integer cardHolderId) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        com.sicnu.oasystem.pojo.CardHolder cardHolder = cardHolderMapper.findCardHolderByCardHolderIdAndEmployeeId(
                cardHolderId, currentEmployee.getEmployeeId());
        return cardHolder != null;
    }
}
