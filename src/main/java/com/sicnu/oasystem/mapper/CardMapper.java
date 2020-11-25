package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @InterfaceName CardMapper
 * @Description 操作Card表的mapper
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:27
 * @Version v1.0
 */

@Mapper
public interface CardMapper {


    /**
     * @MethodName deleteCardByCardId
     * @param cardId 名片id
     * @Description 删除名片by名片id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/14
     */
    int deleteCardByCardId(int cardId);

    /**
     * @MethodName insertCard
     * @param card 名片
     * @Description 增加名片
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int insertCard(Card card);

    /**
     * @MethodName updateCardByCardId
     * @param card 名片
     * @Description 修改名片信息by名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/14
     */
    int updateCardByCardId(Card card);

    /**
     * @MethodName updateOldCardHolderIdByNewCardHolderId
     * @param oldCardHolderId 旧的名片夹id
     * @param newCardHolderId 新的名片夹id
     * @Description 通过名片夹id修改名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/24
     */
    int updateOldCardHolderIdByNewCardHolderId(int oldCardHolderId, int newCardHolderId);

    /**
     * @MethodName findCardByCardId
     * @param cardId 名片id
     * @Description 查找card by cardId
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.CardHolder
     * @LastChangeDate 2020/11/17
     */
    Card findCardByCardId(int cardId);

    /**
     * @MethodName findCardByCardHolderId
     * @param cardHolderId 名片夹id
     * @Description 查找名片by名片夹id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Card>
     * @LastChangeDate 2020/11/24
     */
    List<Card> findCardByCardHolderId(int cardHolderId);

    /**
     * @MethodName findCardByPhone
     * @param phone 电话号码
     * @Description 通过电话号码查询名片——>判别是否已经有此人的名片(电话号码不重复)
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Card>
     * @LastChangeDate 2020/11/24
     */
    List<Card> findCardByPhone(String phone, int ownerId);

    /**
     * @MethodName findCardByCardHolderIdAndEmployeeId
     * @param cardId 名片夹id
     * @param ownerId 用户id
     * @Description 查找名片by名片夹id和用户id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.Card
     * @LastChangeDate 2020/11/25
     */
    Card findCardByCardIdAndEmployeeId(int cardId, int ownerId);
}