package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Equipment;
import com.sicnu.oasystem.service.EquipmentService;
import com.sicnu.oasystem.service.EquipmentServicelpml;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName EquipmentController
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/19 20:15
 * @Version v1.0
 */
@RestController
@Api(tags = "设备管理")
public class EquipmentController {

    @Resource
    EquipmentServicelpml equipmentServicelpml;

    /**
     * @MethodName addEquipment
     * @param equipment
     * @Description 添加设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation("添加设备")
    @PostMapping("/addEquipment")
        public BackFrontMessage addEquipment(@RequestBody Equipment equipment){
        System.out.println(equipment);
        return equipmentServicelpml.addEquipment(equipment.getEquipmentClassifyId()
                ,equipment.getMeetingRoomId(),equipment.getName(),equipment.getIsMaintain(),equipment.getNum());
    }

    /**
     * @MethodName updateEquipment
     * @param equipment
     * @Description 修改设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation("修改设备")
    @PutMapping("/updateEquipment")
    public BackFrontMessage updateEquipment(@RequestBody Equipment equipment){
        System.out.println(equipment);
//        return new BackFrontMessage(200,"ds",null);
        return equipmentServicelpml.updateEquipment(equipment.getEquipmentId(),equipment.getEquipmentClassifyId(),equipment.getMeetingRoomId(),equipment.getName(),equipment.getIsMaintain(),equipment.getNum());
    }

    /**
     * @MethodName deleteEuipment
     * @param equipmentId
     * @Description 删除设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation("删除设备")
    @DeleteMapping("/deleteEuipment")
    public BackFrontMessage deleteEuipment(@RequestParam Integer equipmentId){
        return equipmentServicelpml.deleteEuipment(equipmentId);
    }

    /**
     * @MethodName getallEquipment
     * @param
     * @Description 获取所有的设备信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation("获取所有的设备信息")
    @GetMapping("/getallEquipment")
    public BackFrontMessage getallEquipment(){
        return equipmentServicelpml.getallEquipment();
    }

    @GetMapping("/getEquipmentById")
    public BackFrontMessage getEquipmentById(Integer equipmentId){
        return equipmentServicelpml.getEquipmentById(equipmentId);
    }

    @GetMapping("/getEquipmentByCondition") //todo 未添加权限
    public BackFrontMessage getEquipmentByCondition(Integer meetingroomid, Integer equipmentclassifyId, String name){
        return equipmentServicelpml.getEquipmentByCondition(meetingroomid,equipmentclassifyId,name);
    }

}

