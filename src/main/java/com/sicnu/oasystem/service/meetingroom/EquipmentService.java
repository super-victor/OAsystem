package com.sicnu.oasystem.service.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Equipment;

import java.util.List;

/**
 * @InterfaceName EquipmentService
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/16 23:36
 * @Version v1.0
 */

public interface EquipmentService {

    /**
     * @MethodName addEquipment
     * @param equipmentclassifyId 设备分类Id
     * @param meetingroomId 会议室Id
     * @param name  设备名称
     * @param ismaintain 是否维修
     * @param num 数量
     * @Description 添加设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage addEquipment(Integer equipmentclassifyId, Integer meetingroomId, String name, Integer ismaintain, String remark,int num);

    /**
     * @MethodName updateEquipment
     * @param equipmentId 设备Id
     * @param equipmentclassifyId 设备分类Id
     * @param newmeetingroomId 新的会议室Id
     * @param newname 新的设备名称
     * @param newismaintain 是否被占用
     * @param newnum 新的数量
     * @Description 更新设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage updateEquipment(Integer equipmentId,Integer equipmentclassifyId,
                            Integer newmeetingroomId,String newname,Integer newismaintain,String remark,Integer newnum);

    /**
     * @MethodName deleteEuipment
     * @param equipmentId 设备Id
     * @Description 删除设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage deleteEuipment(Integer equipmentId);

    /**
     * @MethodName getallEquipment
     * @param
     * @Description 获取所有设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage getallEquipment();

    /**
     * @MethodName getEquipmentById
     * @param equipmentId 设备Id
     * @Description 根据设备Id获取设备信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage getEquipmentById(Integer equipmentId);

    /**
     * @MethodName getEquipmentByCondition
     * @param meetingroomid 会议室Id
     * @param equipmentclassifyId 设备分类Id
     * @param name  设备名称
     * @Description 按条件获取设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage getEquipmentByCondition(Integer meetingroomid,Integer equipmentclassifyId,String name);
}
