package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.CardHolder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName CardHolderMapper
 * @Description 操作CardHolder表的mapper
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:27
 * @Version v1.0
 */

@Mapper
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
     * @MethodName deleteCardHolderByCardHolderId
     * @param cardHolderId 名片夹id
     * @Description 删除名片夹by名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/14
     */
    int deleteCardHolderByCardHolderId(int cardHolderId);

    /**
     * @MethodName insertCardHolderByOwnedId
     * @param cardHolder 名片夹
     * @Description 增加名片夹
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int insertCardHolder(CardHolder cardHolder);

    /**
     * @MethodName updateCardHolderByCardHolderId
     * @param cardHolder 名片夹
     * @Description 修改名片夹信息by名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/14
     */
    int updateCardHolderByCardHolderId(CardHolder cardHolder);

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