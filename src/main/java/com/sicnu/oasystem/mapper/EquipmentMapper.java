package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName EqiupmentMapper
 * @Description Eqiupment的Mapper
 * @Author pan
 * @LastChangeDate 2020/11/15 21:42
 * @Version v1.0
 */
@Mapper
public interface EquipmentMapper {
    /**
     * @MethodName addEquipment
     * @param
     * @param equipmentclassifyId 设备分类Id
     * @param meetingroomId 会议室Id
     * @param name 会议名称
     * @param ismaintain 是否维修
     * @param num 设备数量
     * @Description 添加设备
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/16
     */
    Integer addEquipment(Integer equipmentclassifyId,
                         Integer meetingroomId,String name,Integer ismaintain,String remark,Integer num);

    /**
     * @MethodName updateEquipment
     * @param equipmentId 设备名称
     * @param equipmentclassifyId 设备分类Id
     * @param newmeetingroomId 新的会议室Id
     * @param newname 新的设备名称
     * @param newismaintain 是否被占用
     * @param newnum 新的设备名称
     * @Description 根据设备Id更改设备信息
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/16
     */
    Integer updateEquipment(Integer equipmentId,Integer equipmentclassifyId,
                            Integer newmeetingroomId,String newname,Integer newismaintain,String remark,Integer newnum);

    Integer updateeq(Integer equipmentId);
    /**
     * @MethodName deleteEuipment
     * @param equipmentId 设备Id
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
     * @param equipmentId 设备Id
     * @Description 根据设备Id获取设备信息
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.Equipment
     * @LastChangeDate 2020/11/16
     */
    Equipment getEquipmentById(Integer equipmentId);

    /**
     * @MethodName getEquipmentByclassifyAndRoomAndname
     * @param equipmentclassifyId 设备分类Id
     * @param meetingroomId 会议室Id
     * @param name 会议室名称
     * @Description 按分类Id或会议室Id或设备名称获取设备
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.Equipment
     * @LastChangeDate 2020/12/10
     */
    Equipment getEquipmentByclassifyAndRoomAndname(Integer equipmentclassifyId, Integer meetingroomId, String name);

    /**
     * @MethodName getEquipmentByCondition
     * @param meetingroomid 会议室Id
     * @param equipmentclassifyId 设备分类Id
     * @param name  设备名称
     * @Description 按条件获取设备
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Equipment>
     * @LastChangeDate 2020/12/10
     */
    List<Equipment> getEquipmentByCondition(Integer meetingroomid,Integer equipmentclassifyId,String name);
}
