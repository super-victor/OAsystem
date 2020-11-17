package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName EqiupmentMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/15 21:42
 * @Version v1.0
 */
@Mapper
public interface EquipmentMapper {
    /**
     * @MethodName addEquipment
     * @param equipmentId
     * @param equipmentclassifyId
     * @param meetingroomId
     * @param name
     * @param ismaintain
     * @param num
     * @Description 添加设备
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/16
     */
    Integer addEquipment(Integer equipmentId,Integer equipmentclassifyId,
                         Integer meetingroomId,String name,Integer ismaintain,int num);

    /**
     * @MethodName updateEquipment
     * @param equipmentId
     * @param equipmentclassifyId
     * @param newmeetingroomId
     * @param newname
     * @param newismaintain
     * @param newnum
     * @Description 根据设备Id更改设备信息
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/16
     */
    Integer updateEquipment(Integer equipmentId,Integer equipmentclassifyId,
                            Integer newmeetingroomId,String newname,Integer newismaintain,int newnum);

    /**
     * @MethodName deleteEuipment
     * @param equipmentId
     * @Description 根据设备Id删除设备
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/16
     */
    Integer deleteEuipment(Integer equipmentId);

    /**
     * @MethodName getallEquipment
     * @param
     * @Description 获取所有的设备
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Equipment>
     * @LastChangeDate 2020/11/16
     */
    List<Equipment> getallEquipment();

    /**
     * @MethodName getEquipmentById
     * @param equipmentId
     * @Description 根据设备Id获取设备信息
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.Equipment
     * @LastChangeDate 2020/11/16
     */
    Equipment getEquipmentById(Integer equipmentId);
}
