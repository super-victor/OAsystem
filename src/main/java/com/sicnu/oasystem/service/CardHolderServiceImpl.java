package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderClassfyMapper;
import com.sicnu.oasystem.mapper.CardHolderMapper;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.CardHolder;
import com.sicnu.oasystem.pojo.CardHolderClassfy;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    EmployeeMapper employeeMapper;

    /**
     * @MethodName insertCardHolder
     * @param ownedId 被拥有者id
     * @param cardHolderClassfyId 名片夹分类id
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
        //cardHolder.setOwnedId(ownedId);
        //cardHolder.setOwnerId(currentEmployee.getEmployeeId());
        cardHolder.setCardHolderClassfyId(cardHolderClassfyId);
        int counter = cardHolderMapper.insertCardHolderByOwnedId(cardHolder);
        if (counter > 0){
            return new BackFrontMessage(200,"添加成功",cardHolder.getCardHolderId());
        } else {
            return new BackFrontMessage(500,"添加失败",cardHolder.getCardHolderId());
        }
    }

    /**
     * @MethodName hasOwnedCardHolder
     * @param ownerId 拥有者id
     * @param ownedId 被拥有者id
     * @Description 判断是否已经拥有某一职工的名片夹
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/8
     */
    public int hasOwnedCardHolder(int ownerId, int ownedId){
        CardHolder cardHolder = cardHolderMapper.findCardHolderByOwnerIdAndOwnedId(ownerId, ownedId);
        if (cardHolder != null) {
            return cardHolder.getCardHolderClassfyId();
        } else {
            return 0;
        }
    }

    /**
     * @MethodName findCardHolderByOwnerId
     * @Description 查找职工所拥有的全部名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage findCardHolderByOwnerId() {
        Employee currentEmployeeId = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<CardHolder> cardHolderList = cardHolderMapper.findCardHolderByEmployeeId(currentEmployeeId.getEmployeeId());
        return new BackFrontMessage(200,"查找成功",getCardListContent(cardHolderList));
    }

    /**
     * @MethodName getCardContent
     * @param employee 职工
     * @Description 返回名片夹中的值
     * @Author Waynejwei
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     * @LastChangeDate 2020/11/8
     */
    public Map<String,Object> getCardContent(Employee employee){
        Map<String,Object> map = new HashMap<>(16);
        map.put("name",employee.getName());
        map.put("homeAddress",employee.getHomeAddress());
        map.put("phone",employee.getPhone());
        map.put("emial",employee.getEmail());
        return map;
    }

    /**
     * @MethodName getCardListContent
     * @param cardHolderList 名片夹列表
     * @Description 返回名片夹列表中的值
     * @Author Waynejwei
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @LastChangeDate 2020/11/8
     */
    public List<Map<String,Object>> getCardListContent(List<CardHolder> cardHolderList){
        List<Map<String,Object>> resultList = new ArrayList<>();
        for (CardHolder cardHolder : cardHolderList) {
           // Employee employee = employeeMapper.findEmployeeByEmployeeId(cardHolder.getOwnedId());
           // resultList.add(getCardContent(employee));
        }
        return resultList;
    }

    /**
     * @MethodName findCardHolderByOwnedId
     * @param ownedId 被拥有者id
     * @Description 查找职工拥有的另一个职工的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage findCardHolderByOwnedId(int ownedId) {
        Employee employee = employeeMapper.findEmployeeByEmployeeId(ownedId);
        return new BackFrontMessage(200,"查找成功",getCardContent(employee));
    }

    /**
     * @MethodName findCardHolderByCardHolderClassfyId
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 查找职工某一分类下的所有名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage findCardHolderByCardHolderClassfyId(int cardHolderClassfyId) {
        List<CardHolder> cardHolderList = cardHolderMapper.findCardHolderByCardHolderClassfyId(cardHolderClassfyId);
        return new BackFrontMessage(200,"查找成功",getCardListContent(cardHolderList));
    }

    /**
     * @MethodName findSelfCardHolder
     * @Description 获取自己的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage findSelfCardHolder() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        return new BackFrontMessage(200,"查找成功",getCardContent(currentEmployee));
    }

    /**
     * @MethodName deleteCardHolder
     * @param cardHolderId 名片夹id
     * @Description 删除名片夹by名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage deleteCardHolder(int cardHolderId) {
        Employee currrentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        int result = cardHolderMapper.deleteCardHolderByCardHolderIdAndOwnerId(cardHolderId, currrentEmployee.getEmployeeId());
        if (result > 0){
            return new BackFrontMessage(200,"删除成功",null);
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    /**
     * @MethodName updateCardHolderAboutClassfy
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 修改名片夹所在分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @Override
    public BackFrontMessage updateCardHolderAboutClassfy(int cardHolderId, int cardHolderClassfyId) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        if (hasOwnedCardHolderClassfy(currentEmployee.getEmployeeId(), cardHolderClassfyId)) {
            int counter = cardHolderMapper.updateCardHolderClassfyIdByCardHolderId(cardHolderId, cardHolderClassfyId);
            if (counter > 0){
                return new BackFrontMessage(200,"修改成功",null);
            } else {
                return new BackFrontMessage(500,"修改失败",null);
            }
        }
        return new BackFrontMessage(500,"修改失败,您还未拥有此名片夹分类",null);
    }

    /**
     * @MethodName hasOwnedCardHolderClassfy
     * @param employeeId 职工id
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 查找该职工是否拥有此名片夹分类
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/8
     */
    public boolean hasOwnedCardHolderClassfy(int employeeId, int cardHolderClassfyId){
        CardHolderClassfy cardHolderClassfy = cardHolderClassfyMapper.findCardHolderClassfyByCardHolderClassfyId(cardHolderClassfyId);
        if (cardHolderClassfy != null) {
            return cardHolderClassfy.getOwnerId() == employeeId;
        } else {
            return false;
        }
    }

}
