package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.CardHolder;
import org.apache.ibatis.annotations.Mapper;


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
}