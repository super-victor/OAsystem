package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.CardHolder;

/**
 * @InterfaceName CardHolderService
 * @Description 名片夹服务接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 16:14
 * @Version v1.0
 */

public interface CardHolderService {

    /**
     * @MethodName insertCardHolder
     * @param cardHolder 名片夹
     * @Description 添加名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage insertCardHolder(CardHolder cardHolder);

    /**
     * @MethodName findCardHolderByCardHolderClassfyId
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 查找职工某一分类下的所有名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage findCardHolderByCardHolderClassfyId(int cardHolderClassfyId);

    /**
     * @MethodName deleteCardHolder
     * @param cardHolderId 名片夹id
     * @Description 删除名片夹by名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage deleteCardHolder(int cardHolderId);

    /**
     * @MethodName updateCardHolderAboutClassfy
     * @param cardHolder 名片夹
     * @param cardHolderId 名片夹id
     * @Description 修改名片夹所在分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage updateCardHolder(CardHolder cardHolder, int cardHolderId);
}