package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName CardHolderClassfyService
 * @Description 名片夹分类服务接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 16:17
 * @Version v1.0
 */

public interface CardHolderClassfyService {

    BackFrontMessage findCardHolderClassfyByEmployeeId();

    BackFrontMessage deleteCardHolderClassfyByCardHolderClassfyId(int cardHolderClassfyId);

    BackFrontMessage insertCardHolderClassfy(String name);

    BackFrontMessage updateCardHolderClassfyName(int cardHolderClassfyId, String name);
}