package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.CardHolder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName CardHolderMapper
 * @Description 操作CardHolder表的mapper
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:27
 * @Version v1.0
 */

@Mapper
@Component
public interface CardHolderMapper {

    /**
     * @MethodName findCardHolderByCardHolderClassfyId
     * @param CardHolderClassfyId 名片夹分类id
     * @Description 查找名片夹by名片夹分类id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.CardHolder>
     * @LastChangeDate 2020/11/6
     */
    List<CardHolder> findCardHolderByCardHolderClassfyId(int CardHolderClassfyId);

    /**
     * @MethodName findCardHolderByEmployeeId
     * @param EmployeeId 职工id
     * @Description 查找名片夹by职工id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.CardHolder>
     * @LastChangeDate 2020/11/6
     */
    List<CardHolder> findCardHolderByEmployeeId(int EmployeeId);

    /**
     * @MethodName findCardHolderByOwnedId
     * @param ownerId 拥有者id
     * @param ownedId 被拥有者id
     * @Description 查找某一职工拥有的另一个职工的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.CardHolder
     * @LastChangeDate 2020/11/6
     */
    CardHolder findCardHolderByOwnerIdAndOwnedId(int ownerId, int ownedId);

    /**
     * @MethodName deleteCardHolderByCardHolderIdAndOwnerId
     * @param CardHolderId 名片夹id
     * @param ownerId 拥有者id
     * @Description 删除名片夹by他人的职工id和职工名
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int deleteCardHolderByCardHolderIdAndOwnerId(int CardHolderId, int ownerId);

    /**
     * @MethodName insertCardHolderByOwnedId
     * @param cardHolder 名片夹
     * @Description 增加名片夹
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int insertCardHolderByOwnedId(CardHolder cardHolder);

    /**
     * @MethodName updateCardHolderClassfyIdByCardHolderId
     * @param CardHolderId 名片夹id
     * @param newCardHolderClassfyId 名片夹分类id
     * @Description 修改名片夹分类by名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int updateCardHolderClassfyIdByCardHolderId(int CardHolderId, int newCardHolderClassfyId);

    /**
     * @MethodName updateCardHolderClassfyIdByOldCardHolderClassfyId
     * @param oldCardHolderClassfyId 原本名片夹分类id
     * @param newCardHolderClassfyId 新的名片夹分类id
     * @Description 将某一分类下的所有名片夹转移到另一分类中
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/9
     */
    int updateCardHolderClassfyIdByOldCardHolderClassfyId(int oldCardHolderClassfyId, int newCardHolderClassfyId);
}