package com.sicnu.oasystem.controller.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.EmployeeSchedule;
import com.sicnu.oasystem.service.schedule.EmployeeScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName EmployeeScheduleController
 * @Description 职工日程映射控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/20 16:41
 * @Version v1.0
 */

@Api(tags = "职工日程映射管理")
@RestController
public class EmployeeScheduleController {

    @Resource
    EmployeeScheduleService employeeScheduleService;

//    /**
//     * @MethodName insertEmployeeSchedule
//     * @param employeeSchedule 职工日程
//     * @Description 添加职工日程映射关系
//     * @Author Waynejwei
//     * @Return com.sicnu.oasystem.json.BackFrontMessage
//     * @LastChangeDate 2020/11/20
//     */
//    @ApiOperation(value = "添加职工日程映射关系")
//    @PostMapping("/EmployeeSchedule")
//    public BackFrontMessage insertEmployeeSchedule(@RequestBody EmployeeSchedule employeeSchedule){
//        return employeeScheduleService.insertEmployeeSchedule(employeeSchedule);
//    }

    /**
     * @MethodName deleteEmployeeScheduleByEmployeeScheduleId
     * @param employeeScheduleId 职工日程id
     * @Description 删除职工日程映射
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation(value = "删除职工日程映射")
    @DeleteMapping("/EmployeeSchedule")
    public BackFrontMessage deleteEmployeeScheduleByEmployeeScheduleId(@RequestParam int employeeScheduleId){
        return employeeScheduleService.deleteEmployeeSchedule(employeeScheduleId);
    }

    /**
     * @MethodName findEmployeeScheduleByEmployeeId
     * @Description 查找职工所加入的所有日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation(value = "查找职工所加入的所有日程")
    @GetMapping("/findEmployeeScheduleByEmployeeId")
    public BackFrontMessage findEmployeeScheduleByEmployeeId(){
        return employeeScheduleService.findEmployeeScheduleByEmployeeId();
    }

    /**
     * @MethodName findEmployeeScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 查询参加日程的所有职工
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation(value = "查找参加日程的所有职工")
    @GetMapping("/findEmployeeScheduleByScheduleId")
    public BackFrontMessage findEmployeeScheduleByScheduleId(@RequestParam int scheduleId){
        return employeeScheduleService.findEmployeeScheduleByScheduleId(scheduleId);
    }

    /**
     * @MethodName findReadyToStartEmployeeSchedule
     * @param intervalTime 离开始的时间戳
     * @Description 查找职工快要开始的日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation(value = "查找职工快要开始的日程")
    @GetMapping("/findReadyToStartEmployeeSchedule")
    public BackFrontMessage findReadyToStartEmployeeSchedule(@RequestParam long intervalTime){
        return employeeScheduleService.findReadyToStartEmployeeSchedule(intervalTime);
    }

    /**
     * @MethodName findDoingEmployeeSchedule
     * @Description 查询职工正在进行的日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation(value = "查询职工正在进行的日程")
    @GetMapping("/findDoingEmployeeSchedule")
    public BackFrontMessage findDoingEmployeeSchedule(){
        return employeeScheduleService.findDoingEmployeeSchedule();
    }

    /**
     * @MethodName findBeReadyToEndEmployeeSchedule
     * @param intervalTime 离结束的时间
     * @Description 查询职工快要结束的日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation(value = "查询职工快要结束的日程")
    @GetMapping("/findBeReadyToEndEmployeeSchedule")
    public BackFrontMessage findBeReadyToEndEmployeeSchedule(@RequestParam long intervalTime){
        return employeeScheduleService.findBeReadyToEndEmployeeSchedule(intervalTime);
    }

    /**
     * @MethodName findEndEmployeeSchedule
     * @Description 查询职工已经结束的日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @ApiOperation(value = "查询职工已经结束的日程")
    @GetMapping("/findEndEmployeeSchedule")
    public BackFrontMessage findEndEmployeeSchedule(){
        return employeeScheduleService.findEndEmployeeSchedule();
    }
}
