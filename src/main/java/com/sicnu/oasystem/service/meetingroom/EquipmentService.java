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
     * @param equipmentId
     * @param equipmentclassifyId
     * @param meetingroomId
     * @param name
     * @param ismaintain
     * @param num
     * @Description 添加设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage addEquipment(Integer equipmentclassifyId,
                                  Integer meetingroomId, String name, Integer ismaintain, String remark,int num);

    /**
     * @MethodName updateEquipment
     * @param equipmentId
     * @param equipmentclassifyId
     * @param newmeetingroomId
     * @param newname
     * @param newismaintain
     * @param newnum
     * @Description 更新设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage updateEquipment(Integer equipmentId,Integer equipmentclassifyId,
                            Integer newmeetingroomId,String newname,Integer newismaintain,String remark,Integer newnum);

    /**
     * @MethodName deleteEuipment
     * @param equipmentId
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
     * @param equipmentId
     * @Description 根据设备Id获取设备信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage getEquipmentById(Integer equipmentId);

    BackFrontMessage getEquipmentByCondition(Integer meetingroomid,Integer equipmentclassifyId,String name);
}
