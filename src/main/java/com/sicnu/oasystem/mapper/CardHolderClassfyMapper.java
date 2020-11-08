package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.CardHolderClassfy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName CardHolderClassfyMapper
 * @Description 操作CardHolderClassfy表的mapper
 * @Author Waynejwei
 * @LastChangeDate 2020/11/6 17:01
 * @Version v1.0
 */

@Mapper
@Component
public interface CardHolderClassfyMapper {

    /**
     * @MethodName findCardHolderClassfysByEmployeeId
     * @param employeeId
     * @Description 查找名片夹by职工id
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.CardHolderClassfy>
     * @LastChangeDate 2020/11/6
     */
    List<CardHolderClassfy> findCardHolderClassfysByEmployeeId(int employeeId);

    /**
     * @MethodName findCardHolderClassfyByName
     * @param name
     * @param ownerId
     * @Description 查找名片夹分类by名片夹分类名称
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.CardHolderClassfy
     * @LastChangeDate 2020/11/8
     */
    CardHolderClassfy findCardHolderClassfyByName(String name, int ownerId);

    /**
     * @MethodName findCardHolderClassfyByCardHolderClassfyId
     * @param cardHolderClassfyId
     * @Description 查找名片夹分类通过名片夹分类id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.CardHolderClassfy
     * @LastChangeDate 2020/11/8
     */
    CardHolderClassfy findCardHolderClassfyByCardHolderClassfyId(int cardHolderClassfyId);

    /**
     * @MethodName deleteCardHolderClassfyByCardHolderClassfyId
     * @param CardHolderClassfyId
     * @Description 删除名片夹分类by名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int deleteCardHolderClassfyByCardHolderClassfyId(int CardHolderClassfyId);

    /**
     * @MethodName updateCardHolderClassfyByCardHolderClassfyId
     * @param CardHolderClassfyId
     * @param name
     * @Description 修改名片夹名称by名片夹id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int updateCardHolderClassfyByCardHolderClassfyId(int CardHolderClassfyId, String name);

    /**
     * @MethodName insertCardHolderClassfyByCardHolderClassfyId
     * @param cardHolderClassfy
     * @Description 增加名片夹分类
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/8
     */
    int insertCardHolderClassfyByCardHolderClassfyId(CardHolderClassfy cardHolderClassfy);


}