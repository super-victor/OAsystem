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

    public BackFrontMessage insertCardHolder(int ownedId, int cardHolderClassfyId);

    public BackFrontMessage findCardHolderByOwnerId();

    public BackFrontMessage findCardHolderByOwnedId(int ownedId);

    public BackFrontMessage findCardHolderByCardHolderClassfyId(int cardHolderClassfyId);

    public BackFrontMessage deleteCardHolder(int cardHolderId);

    public BackFrontMessage updateCardHolderAboutClassfy(int cardHolderId, int cardHolderClassfyId);
}