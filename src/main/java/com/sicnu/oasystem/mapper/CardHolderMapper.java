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
     * @param CardHolderClassfyId
     * @Description 查找名片夹by名片夹分类id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.CardHolder>
     * @LastChangeDate 2020/11/6
     */
    List<CardHolder> findCardHolderByCardHolderClassfyId(int CardHolderClassfyId);

    /**
     * @MethodName findCardHolderByEmployeeId
     * @param EmployeeId
     * @Description 查找名片夹by职工id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.CardHolder>
     * @LastChangeDate 2020/11/6
     */
    List<CardHolder> findCardHolderByEmployeeId(int EmployeeId);

    /**
     * @MethodName findCardHolderByOwnedId
     * @param ownerId
     * @param ownedId
     * @Description 查找某一职工拥有的另一个职工的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.CardHolder
     * @LastChangeDate 2020/11/6
     */
    CardHolder findCardHolderByOwnedId(int ownerId, int ownedId);

    /**
     * @MethodName deleteCardHolderByCardHolderId
     * @param CardHolderId
     * @Description 删除名片夹by他人的职工id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int deleteCardHolderByCardHolderId(int CardHolderId);

    /**
     * @MethodName insertCardHolderByOwnedId
     * @param cardHolder
     * @Description 增加名片夹
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int insertCardHolderByOwnedId(CardHolder cardHolder);

    /**
     * @MethodName updateCardHolderByCardHolderId
     * @param CardHolderId
     * @param cardHolderClassfyId
     * @Description 修改名片夹分类by名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int updateCardHolderByCardHolderId(int CardHolderId, int cardHolderClassfyId);
}