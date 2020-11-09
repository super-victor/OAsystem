package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName CardHolderService
 * @Description 名片夹服务接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 16:14
 * @Version v1.0
 */

public interface CardHolderService {

    BackFrontMessage insertCardHolder(int ownedId, int cardHolderClassfyId);

    BackFrontMessage findCardHolderByOwnerId();

    BackFrontMessage findCardHolderByOwnedId(int ownedId);

    BackFrontMessage findCardHolderByCardHolderClassfyId(int cardHolderClassfyId);

    BackFrontMessage findSelfCardHolder();

    BackFrontMessage deleteCardHolder(int cardHolderId);

    BackFrontMessage updateCardHolderAboutClassfy(int cardHolderId, int cardHolderClassfyId);
}