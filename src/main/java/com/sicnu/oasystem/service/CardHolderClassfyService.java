package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Employee;

/**
 * @InterfaceName CardHolderClassfyService
 * @Description 名片夹分类服务接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 16:17
 * @Version v1.0
 */

public interface CardHolderClassfyService {

    public BackFrontMessage findCardHolderClassfyByEmployeeId();

    public BackFrontMessage deleteCardHolderClassfyByCardHolderClassfyId(int cardHolderClassfyId);

    public BackFrontMessage insertCardHolderClassfy(String name);

    public BackFrontMessage updateCardHolderClassfyName(int cardHolderClassfyId, String name);
}