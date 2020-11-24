package com.sicnu.oasystem.service.card;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName CardHolderClassfyService
 * @Description 名片夹分类服务接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 16:17
 * @Version v1.0
 */

public interface CardHolderClassfyService {

    /**
     * @MethodName findCardHolderClassfyServiceByEmployeeId
     * @Description 获取某一职工的所用名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    BackFrontMessage findCardHolderClassfyByEmployeeId();

    /**
     * @MethodName deleteCardHolderClassfyByCardHolderClassfyId
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 通过名片夹分类id删除名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/7
     */
    BackFrontMessage deleteCardHolderClassfyByCardHolderClassfyId(int cardHolderClassfyId);

    /**
     * @MethodName insertCardHolderClassfy
     * @param name  名片夹分类名称
     * @Description 增加名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/7
     */
    BackFrontMessage insertCardHolderClassfy(String name);

    /**
     * @MethodName updateCardHolderClassfyName
     * @param cardHolderClassfyId 名片夹分类id
     * @param name 名片夹分类名称
     * @Description 修改名片夹分类名称
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    BackFrontMessage updateCardHolderClassfyName(int cardHolderClassfyId, String name);
}