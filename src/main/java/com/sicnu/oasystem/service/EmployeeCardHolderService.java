package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName EmployeeCardHolderService
 * @Description 添加描述
 * @Author Waynejwei
 * @LastChangeDate 2020/11/14 16:13
 * @Version v1.0
 */

public interface EmployeeCardHolderService {

    /**
     * @MethodName insertEmployeeCardHolder
     * @param cardHolderId 名片夹id
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 分享名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/14
     */
    BackFrontMessage insertEmployeeCardHolder(int cardHolderId, int cardHolderClassfyId);

    /**
     * @MethodName deleteEmployeeCardHolderByEmployeeCardHolderId
     * @param employeeCardHolderId 职工名片夹id
     * @Description 删除职工名片夹by职工名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/14
     */
    BackFrontMessage deleteEmployeeCardHolderByEmployeeCardHolderId(int employeeCardHolderId);

    /**
     * @MethodName findEmployeeCardHolderByEmployeeId
     * @Description 查找某一职工所拥有的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/15
     */
    BackFrontMessage findEmployeeCardHolderByEmployeeId();

    /**
     * @MethodName findEmployeeCardHolderByCardHolderClassfyId
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 查找名片夹by名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage findEmployeeCardHolderByCardHolderClassfyId(int cardHolderClassfyId);

    /**
     * @MethodName updateCardHolderClassfyIdBy
     * @param employeeCardHolderId 职工名片夹id
     * @param newCardHolderClassfyId 新的名片夹分类id
     * @Description 修改名片夹分类by职工名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage updateCardHolderClassfyIdBy(int employeeCardHolderId, int newCardHolderClassfyId);
}