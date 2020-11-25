package com.sicnu.oasystem.service.card;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName CardHolderService
 * @Description 名片夹服务接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 16:17
 * @Version v1.0
 */

public interface CardHolderService {

    /**
     * @MethodName findCardHolderByEmployeeId
     * @Description 获取某一职工的所用名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    BackFrontMessage findCardHolderByEmployeeId();

    /**
     * @MethodName deleteCardHolderByCardHolderId
     * @param cardHolderId 名片夹id
     * @Description 通过名片夹id删除名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/7
     */
    BackFrontMessage deleteCardHolderByCardHolderId(int cardHolderId);

    /**
     * @MethodName insertCardHolder
     * @param name  名片夹名称
     * @Description 增加名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/7
     */
    BackFrontMessage insertCardHolder(String name);

    /**
     * @MethodName updateCardHolderName
     * @param cardHolderId 名片夹id
     * @param name 名片夹名称
     * @Description 修改名片夹分类名称
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage updateCardHolderName(int cardHolderId, String name);
}