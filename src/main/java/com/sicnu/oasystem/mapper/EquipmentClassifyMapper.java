package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.EquipmentClassify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @InterfaceName EquipmentClassifyMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/15 21:43
 * @Version v1.0
 */
@Mapper
public interface EquipmentClassifyMapper {

    /**
     * @MethodName addEquipmentClassify
     * @param classifyName  分类名称
     * @Description 添加一个设备分类
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/15
     */
    Integer addEquipmentClassify(String classifyName);

    /**
     * @MethodName updateEquipmentClassify
     * @param classifyId  分类Id
     * @param newClassifyName 设备分类新名称
     * @Description 添加通过id修改设备分类名称
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/15
     */
    Integer updateEquipmentClassify(Integer classifyId,String newClassifyName);

    /**
     * @MethodName deleteEquipmentClassify
     * @param ClassifyId  分类Id
     * @Description 通过设备分类ID删除设备分类
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/15
     */
    Integer deleteEquipmentClassify(Integer ClassifyId);

    /**
     * @MethodName EquipmentClassifyIfExitByName
     * @param ClassifyName 分类名称
     * @Description 判断一个设备分类是否存在，如果存在则返回ID
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/15
     */
    Integer EquipmentClassifyIfExitByName(String ClassifyName);

    /**
     * @MethodName getAllEquipmentClassify
     * @param
     * @Description 查询所有的设备分类
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.EquipmentClassify>
     * @LastChangeDate 2020/11/15
     */
    List<EquipmentClassify> getAllEquipmentClassify();

    /**
     * @MethodName getEquipmentClassify
     * @param ClassifyId 分类Id
     * @Description 通过Id查找EquipmentClassify
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.EquipmentClassify
     * @LastChangeDate 2020/11/16
     */
    EquipmentClassify getEquipmentClassifyById(Integer ClassifyId);
}
