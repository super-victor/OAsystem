package com.sicnu.oasystem.controller.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.schedule.EmployeeScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    /**
     * @MethodName findSelfSchedule
     * @param start 时间(yyyy-MM-dd)
     * @param end 结束时间
     * @Description  获取某段时间的个人日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/30
     */
    @ApiOperation(value = "获取该员工一段时间的个人日程")
    @GetMapping("/findSelfSchedule")
    public BackFrontMessage findSelfSchedule(@RequestParam String start, @RequestParam String end){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return employeeScheduleService.findSelfScheduleByDate(simpleDateFormat.parse(start), simpleDateFormat.parse(end));
        }catch (ParseException e){
            return new BackFrontMessage(500,"时间格式错误",null);
        }
    }

    /**
     * @MethodName findCompanySchedule
     * @param start 开始时间
     * @param end 结束时间
     * @Description 获取某个人在一段时间内的公司日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/30
     */
    @ApiOperation(value = "获取该员工一段时间的公司日程")
    @GetMapping("/findCompanySchedule")
    public BackFrontMessage findCompanySchedule(@RequestParam String start, @RequestParam String end){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return employeeScheduleService.findCompanyScheduleByDate(simpleDateFormat.parse(start), simpleDateFormat.parse(end));
        }catch (ParseException e){
            return new BackFrontMessage(500,"时间格式错误",null);
        }
    }
}
