package com.sicnu.oasystem.controller.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Equipment;
import com.sicnu.oasystem.service.meetingroom.EquipmentServicelpml;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @ClassName EquipmentController
 * @Description 设备控制器
 * @Author pan
 * @LastChangeDate 2020/11/19 20:15
 * @Version v1.0
 */
@RestController
@Api(tags = "设备管理")
@Validated
public class EquipmentController {

    @Resource
    EquipmentServicelpml equipmentServicelpml;

    /**
     * @MethodName addEquipment
     * @param equipment 设备
     * @Description 添加设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation("添加设备")
    @PostMapping("/addEquipment")
        public BackFrontMessage addEquipment(@Validated(Equipment.Add.class) Equipment equipment){
        return equipmentServicelpml.addEquipment(equipment.getEquipmentClassifyId()
                ,equipment.getMeetingRoomId(),equipment.getName(),equipment.getIsMaintain(),equipment.getRemark(),equipment.getNum());
    }

    /**
     * @MethodName updateEquipment
     * @param equipment 设备
     * @Description 修改设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation("修改设备")
    @PutMapping("/updateEquipment")
    public BackFrontMessage updateEquipment(@Validated(Equipment.update.class) Equipment equipment){
        return equipmentServicelpml.updateEquipment(equipment.getEquipmentId(),equipment.getEquipmentClassifyId(),
                equipment.getMeetingRoomId(),equipment.getName(),equipment.getIsMaintain(),equipment.getRemark(),equipment.getNum());
    }

    /**
     * @MethodName deleteEuipment
     * @param equipmentId 设备Id
     * @Description 删除设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation("删除设备")
    @DeleteMapping("/deleteEuipment")
    public BackFrontMessage deleteEuipment(@RequestParam @Validated @NotNull(message = "设备Id不能为空") Integer equipmentId){
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

    /**
     * @MethodName getEquipmentById
     * @param equipmentId 设备Id
     * @Description 通过设备Id获取设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    @ApiOperation("通过设备Id获取设备信息")
    @GetMapping("/getEquipmentById")
    public BackFrontMessage getEquipmentById(@Validated @NotNull(message = "设备id不能为空") Integer equipmentId){
        return equipmentServicelpml.getEquipmentById(equipmentId);
    }

    /**
     * @MethodName getEquipmentByCondition
     * @param meetingroomid 会议室Id
     * @param equipmentclassifyId 设备分类Id
     * @param name 设备名称
     * @Description 按条件获取设备
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    @ApiOperation("按条件获取设备信息")
    @GetMapping("/getEquipmentByCondition") //todo 未添加权限
    public BackFrontMessage getEquipmentByCondition(Integer meetingroomid, Integer equipmentclassifyId, String name){
        return equipmentServicelpml.getEquipmentByCondition(meetingroomid,equipmentclassifyId,name);
    }

}

