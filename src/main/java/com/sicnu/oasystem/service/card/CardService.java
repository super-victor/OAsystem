package com.sicnu.oasystem.service.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Card;

/**
 * @InterfaceName CardService
 * @Description 名片服务接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 16:14
 * @Version v1.0
 */

public interface CardService {

    /**
     * @MethodName insertCard
     * @param card 名片
     * @param cardHolderId 名片夹id
     * @Description 添加名片
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage insertCard(Card card, Integer cardHolderId);

    /**
     * @MethodName deleteCard
     * @param cardId 名片夹id
     * @Description 删除名片by名片id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage deleteCard(int cardId);

    /**
     * @MethodName updateCard
     * @param card 名片夹
     * @param cardId 名片夹id
     * @Description 修改名片相关信息
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage updateCard(Card card, int cardId);

    /**
     * @MethodName shareCard
     * @param cardId 名片id
     * @param cardHolderId 名片夹id
     * @Description 分享名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage shareCard(int cardId, int cardHolderId);

    /**
     * @MethodName findCardByCardHolderId
     * @param cardHolderId 名片夹id
     * @Description 查找名片by名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage findCardByCardHolderId(int cardHolderId);
}