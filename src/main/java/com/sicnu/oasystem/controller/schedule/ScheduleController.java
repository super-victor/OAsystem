package com.sicnu.oasystem.controller.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Schedule;
import com.sicnu.oasystem.service.schedule.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName ScheduleController
 * @Description 日程控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/17 18:42
 * @Version v1.0
 */

@Api(tags = "日程管理")
@RestController
public class ScheduleController {

    @Resource
    ScheduleService scheduleService;

    /**
     * @MethodName insertCompanySchedule
     * @param schedule 日程
     * @Description 添加公司日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation(value = "添加公司日程")
    @PostMapping("/CompanySchedule")
    public BackFrontMessage insertCompanySchedule(@Validated({Schedule.Company.class}) Schedule schedule){
        return scheduleService.insertCompanySchedule(schedule);
    }

    /**
     * @MethodName insertSelfSchedule
     * @param schedule 日程
     * @Description 添加个人日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    @ApiOperation("添加个人日程")
    @PostMapping("/SelfSchedule")
    public BackFrontMessage insertSelfSchedule(@Validated({Schedule.Self.class}) Schedule schedule){
        return scheduleService.insertSelfSchedule(schedule);
    }

    /**
     * @MethodName updateSchedule
     * @param schedule 日程
     * @Description 修改日程信息
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation(value = "修改个人日程信息")
    @PutMapping("/SelfSchedule")
    public BackFrontMessage updateSelfSchedule(@Validated({Schedule.Update.class}) Schedule schedule){
        return scheduleService.updateScheduleByScheduleId(schedule, schedule.getScheduleId(), 0);
    }

    /**
     * @MethodName updateCompanySchedule
     * @param schedule 日程
     * @Description 修改公司日程信息
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    @ApiOperation(value = "修改公司日程信息")
    @PutMapping("/CompanySchedule")
    public BackFrontMessage updateCompanySchedule(@Validated({Schedule.Update.class}) Schedule schedule){
        return scheduleService.updateScheduleByScheduleId(schedule, schedule.getScheduleId(), 1);
    }

    /**
     * @MethodName deleteCompanySchedule
     * @param scheduleId 日晨id
     * @Description 删除公司日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation(value = "删除公司日程")
    @DeleteMapping("/CompanySchedule")
    public BackFrontMessage deleteCompanySchedule(@RequestParam int scheduleId){
        return scheduleService.deleteScheduleByScheduleId(scheduleId, 1);
    }

    /**
     * @MethodName deleteSelfSchedule
     * @param scheduleId 日程id
     * @Description 删除个人日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    @ApiOperation(value = "删除个人日程")
    @DeleteMapping("/SelfSchedule")
    public BackFrontMessage deleteSelfSchedule(@RequestParam int scheduleId){
        return scheduleService.deleteScheduleByScheduleId(scheduleId, 0);
    }

    /**
     * @MethodName findScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 通过日程id查找日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation(value = "通过日程id获取日程信息")
    @GetMapping("/findScheduleByScheduleId")
    public BackFrontMessage findScheduleByScheduleId(@RequestParam int scheduleId){
        return scheduleService.findScheduleByScheduleId(scheduleId);
    }
}
