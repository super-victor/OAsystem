package com.sicnu.oasystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName CardHolderMapper
 * @Description 操作CardHolder表的mapper
 * @Author Waynejwei
 * @LastChangeDate 2020/11/6 17:01
 * @Version v1.0
 */

@Mapper
public interface CardHolderMapper {

    /**
     * @MethodName findCardHolderByEmployeeId
     * @param employeeId  职工id
     * @Description 查找名片夹by职工id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.CardHolderClassfy>
     * @LastChangeDate 2020/11/6
     */
    List<com.sicnu.oasystem.pojo.CardHolder> findCardHolderByEmployeeId(int employeeId);

    /**
     * @MethodName findCardHolderByName
     * @param name 名片夹分类名称
     * @param ownerId 名片夹分类拥有者id
     * @Description 查找名片夹分类by名片夹分类名称
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.CardHolderClassfy
     * @LastChangeDate 2020/11/8
     */
    com.sicnu.oasystem.pojo.CardHolder findCardHolderByName(String name, int ownerId);

    /**
     * @MethodName findCardHolderByCardHolderId
     * @param cardHolderId 名片夹分类id
     * @Description 查找名片夹分类通过名片夹分类id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.CardHolderClassfy
     * @LastChangeDate 2020/11/8
     */
    com.sicnu.oasystem.pojo.CardHolder findCardHolderByCardHolderId(int cardHolderId);

    /**
     * @MethodName findCardHolderByCardHolderIdAndEmployeeId
     * @param cardHolderId 名片夹分类id
     * @param employeeId 职工id
     * @Description 判断此人是否有此名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.CardHolderClassfy
     * @LastChangeDate 2020/11/16
     */
    com.sicnu.oasystem.pojo.CardHolder findCardHolderByCardHolderIdAndEmployeeId(int cardHolderId, int employeeId);

    /**
     * @MethodName deleteCardHolderByCardHolderId
     * @param cardHolderId 名片夹分类id
     * @Description 删除名片夹分类by名片夹id,并将原本属于他的划分到"他人名片夹"
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int deleteCardHolderByCardHolderId(int cardHolderId);

    /**
     * @MethodName updateCardHolderNameByCardHolderId
     * @param cardHolderId 名片夹分类id
     * @param name 名片夹分类名称
     * @Description 修改名片夹名称by名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int updateCardHolderNameByCardHolderId(int cardHolderId, String name);

    /**
     * @MethodName insertCardHolderByCardHolderId
     * @param cardHolder 名片夹分类
     * @Description 增加名片夹分类
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/8
     */
    int insertCardHolderByCardHolderId(com.sicnu.oasystem.pojo.CardHolder cardHolder);
}