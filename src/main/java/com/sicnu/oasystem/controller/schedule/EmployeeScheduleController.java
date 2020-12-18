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
     * @MethodName findSelfScheduleByDate
     * @param start 时间(yyyy-MM-dd)
     * @param end 结束时间
     * @Description  获取某段时间的个人日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/30
     */
    @ApiOperation(value = "获取该员工一段时间的个人日程")
    @GetMapping("/findSelfScheduleByDate")
    public BackFrontMessage findSelfScheduleByDate(@RequestParam String start, @RequestParam String end){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return employeeScheduleService.findSelfScheduleByDate(simpleDateFormat.parse(start), simpleDateFormat.parse(end));
        }catch (ParseException e){
            return new BackFrontMessage(500,"时间格式错误",null);
        }
    }

    /**
     * @MethodName findCompanyScheduleByDate
     * @param start 开始时间
     * @param end 结束时间
     * @Description 获取某个人在一段时间内的公司日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/30
     */
    @ApiOperation(value = "获取该员工一段时间的公司日程")
    @GetMapping("/findCompanyScheduleByDate")
    public BackFrontMessage findCompanyScheduleByDate(@RequestParam String start, @RequestParam String end){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return employeeScheduleService.findCompanyScheduleByDate(simpleDateFormat.parse(start), simpleDateFormat.parse(end));
        }catch (ParseException e){
            return new BackFrontMessage(500,"时间格式错误",null);
        }
    }

    /**
     * @MethodName findCompanySchedule
     * @Description 获取该员工所有的公司日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/1
     */
    @ApiOperation(value = "获取该员工所有的公司日程")
    @GetMapping("/findCompanySchedule")
    public BackFrontMessage findCompanySchedule(){
        return employeeScheduleService.findCompanyScheduleByEmployeeId();
    }

    /**
     * @MethodName findSelfSchedule
     * @Description 获取该员工所有的个人日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/1
     */
    @ApiOperation(value = "获取该员工所有的个人日程")
    @GetMapping("/findSelfSchedule")
    public BackFrontMessage findSelfSchedule(){
        return employeeScheduleService.findSelfScheduleByEmployeeId();
    }

    /**
     * @MethodName findEmployeeScheduleCount
     * @Description 获取职工所有日程数目
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/16
     */
    @ApiOperation(value = "获取职工未开始的日程数目")
    @GetMapping("/findScheduleCount")
    public BackFrontMessage findEmployeeScheduleCount(){
        return employeeScheduleService.findEmployeeScheduleCount();
    }

    /**
     * @MethodName findEmployeeSchedule
     * @Description 获取职工最近一个未开始的日程信息
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/16
     */
    @ApiOperation(value = "获取职工最近一个未开始的日程信息")
    @GetMapping("/findSchedule")
    public BackFrontMessage findEmployeeSchedule(){
        return employeeScheduleService.findEmployeeScheduleNotStart();
    }
}
