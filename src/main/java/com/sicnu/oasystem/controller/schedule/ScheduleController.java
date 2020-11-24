package com.sicnu.oasystem.controller.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Schedule;
import com.sicnu.oasystem.service.schedule.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * @MethodName insertSchedule
     * @param schedule 日程
     * @Description 添加日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation(value = "添加日程")
    @PostMapping("/Schedule")
    public BackFrontMessage insertSchedule(@RequestBody Schedule schedule){
        return scheduleService.insertSchedule(schedule);
    }

    /**
     * @MethodName updateSchedule
     * @param schedule 日程
     * @param scheduleId 日程id
     * @Description 修改日程信息
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation(value = "修改日程信息")
    @PutMapping("/Schedule")
    public BackFrontMessage updateSchedule(@RequestBody Schedule schedule, @RequestParam int scheduleId){
        return scheduleService.updateScheduleByScheduleId(schedule,scheduleId);
    }

    /**
     * @MethodName deleteSchedule
     * @param scheduleId 日晨id
     * @Description 删除日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/19
     */
    @ApiOperation(value = "删除日程")
    @DeleteMapping("/Schedule")
    public BackFrontMessage deleteSchedule(@RequestParam int scheduleId){
        return scheduleService.deleteScheduleByScheduleId(scheduleId);
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
