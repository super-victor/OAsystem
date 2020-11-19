package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Schedule;

import java.util.Date;

/**
 * @InterfaceName ScheduleService
 * @Description 日程表服务类接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/17 18:41
 * @Version v1.0
 */

public interface ScheduleService {

    /**
     * @MethodName insertSchedule
     * @param schedule 日程
     * @Description 添加日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/17
     */
    BackFrontMessage insertSchedule(Schedule schedule);

    /**
     * @MethodName updateScheduleByScheduleId
     * @param schedule 日程
     * @param scheduleId 日程id
     * @Description 修改日程信息by日程id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/17
     */
    BackFrontMessage updateScheduleByScheduleId(Schedule schedule, int scheduleId);

    /**
     * @MethodName deleteScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 删除日程by日程id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/17
     */
    BackFrontMessage deleteScheduleByScheduleId(int scheduleId);

    /**
     * @MethodName findScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 通过日程id获取日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/17
     */
    BackFrontMessage findScheduleByScheduleId(int scheduleId);

    /**
     * @MethodName findDeadLineScheduleByTime
     * @param intervalTime 间隔时间
     * @Description 获取快要开始的日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/17
     */
    BackFrontMessage findDeadLineScheduleByTime(Date intervalTime);


}