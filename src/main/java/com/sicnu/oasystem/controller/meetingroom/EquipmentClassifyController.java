package com.sicnu.oasystem.controller.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.meetingroom.EquipmentClassifyServicelmpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName EquipmentClassifyController
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/19 20:14
 * @Version v1.0
 */

@RestController
@Api(tags = "设备分类")
public class EquipmentClassifyController {

    @Resource
    EquipmentClassifyServicelmpl equipmentClassifyServicelmpl;

    /**
     * @MethodName addEquipmentClassify
     * @param classifyName
     * @Description 添加设备分类
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @PostMapping("/addEquipmentClassify")
    @ApiOperation("添加设备分类")
    public BackFrontMessage addEquipmentClassify(@RequestParam String classifyName){
        return equipmentClassifyServicelmpl.addEquipmentClassify(classifyName);
    }

    /**
     * @MethodName updateEquipmentClassify
     * @param classifyId
     * @param newClassifyName
     * @Description 修改设备分类信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation("修改设备分类信息")
    @PutMapping("/updateEquipmentClassify")
    public BackFrontMessage updateEquipmentClassify(@RequestParam Integer classifyId, @RequestParam String newClassifyName){
        return equipmentClassifyServicelmpl.updateEquipmentClassify(classifyId,newClassifyName);
    }

    /**
     * @MethodName getAllEquipmentClassify
     * @param
     * @Description 获取所有设备分类信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation("获取所有设备分类信息")
    @GetMapping("/getAllEquipmentClassify")
    public BackFrontMessage getAllEquipmentClassify(){
        return equipmentClassifyServicelmpl.getAllEquipmentClassify();
    }

    /**
     * @MethodName deleteEquipmentClassify
     * @param ClassifyId
     * @Description 删除设备分类
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation("删除设备分类")
    @DeleteMapping("/deleteEquipmentClassify")
    public BackFrontMessage deleteEquipmentClassify(Integer ClassifyId){
        return equipmentClassifyServicelmpl.deleteEquipmentClassify(ClassifyId);
    }
}

