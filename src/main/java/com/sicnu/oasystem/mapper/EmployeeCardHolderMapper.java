package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.EmployeeCardHolder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName EmployeeCardHolderMapper
 * @Description 职工名片夹相关的mapper
 * @Author Waynejwei
 * @LastChangeDate 2020/11/14 16:06
 * @Version v1.0
 */

@Mapper
public interface EmployeeCardHolderMapper {

    /**
     * @MethodName insertEmployeeCardHolder
     * @param employeeCardHolder 职工名片夹id
     * @Description 分享名片夹
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/14
     */
    int insertEmployeeCardHolder(EmployeeCardHolder employeeCardHolder);

    /**
     * @MethodName deleteEmployeeCardHolderByEmployeeCardHolderIdAndEmployeeId
     * @param employeeCardHolderId 职工名夹id
     * @param employeeId 职工id
     * @Description 删除职工名片夹by职工名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/14
     */
    int deleteEmployeeCardHolderByEmployeeCardHolderIdAndEmployeeId(int employeeCardHolderId, int employeeId);

    /**
     * @MethodName findEmployeeCardHolderByEmployeeIdAndCardHolderId
     * @param employeeId 职工id
     * @param cardHolderId 名片夹id
     * @Description 查找职工名片夹by职工id和名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.EmployeeCardHolder
     * @LastChangeDate 2020/11/14
     */
    EmployeeCardHolder findEmployeeCardHolderByEmployeeIdAndCardHolderId(int employeeId, int cardHolderId);

    /**
     * @MethodName findEmployeeCardHolderByCardHolderId
     * @param cardHolderId 名片夹id
     * @Description 查找职工名片夹by名片夹id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.EmployeeCardHolder>
     * @LastChangeDate 2020/11/14
     */
    List<EmployeeCardHolder> findEmployeeCardHolderByCardHolderId(int cardHolderId);

    /**
     * @MethodName findEmployeeCardHolderByEmployeeCardHolderId
     * @param EmployeeCardHolderId 职工名片夹id
     * @Description 查找职工名片夹by职工名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.EmployeeCardHolder
     * @LastChangeDate 2020/11/14
     */
    EmployeeCardHolder findEmployeeCardHolderByEmployeeCardHolderId(int EmployeeCardHolderId);

    /**
     * @MethodName findEmployeeCardHolderByEmployeeId
     * @param employeeId 职工id
     * @Description 查找职工名片夹by职工id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.EmployeeCardHolder>
     * @LastChangeDate 2020/11/15
     */
    List<EmployeeCardHolder> findEmployeeCardHolderByEmployeeId(int employeeId);

    /**
     * @MethodName findEmployeeCardHolderByCardHolderClassfyId
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 查找职工名片夹by名片夹分类id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.EmployeeCardHolder>
     * @LastChangeDate 2020/11/16
     */
    List<EmployeeCardHolder> findEmployeeCardHolderByCardHolderClassfyId(int cardHolderClassfyId);

    /**
     * @MethodName updateEmployeeCardHolderClassfyByEmployeeCardHolderId
     * @param employeeCardHolderId 职工名片夹id
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 修改名片夹分类id by 职工名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/16
     */
    int updateEmployeeCardHolderClassfyByEmployeeCardHolderId(int employeeCardHolderId, int cardHolderClassfyId);

    /**
     * @MethodName updateOldCardHolderClassfyIdByNewCardHolderClassfyId
     * @param oldCardHolderClassfyId 旧的名片夹分类id
     * @param newCardHolderClassfyId 新的名片夹分类id
     * @Description 修改名片夹分类id by 名片夹分类id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/16
     */
    int updateOldCardHolderClassfyIdByNewCardHolderClassfyId(int oldCardHolderClassfyId, int newCardHolderClassfyId);
}