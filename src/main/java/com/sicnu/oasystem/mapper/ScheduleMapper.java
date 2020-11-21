package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @InterfaceName ScheduleMapper
 * @Description 操作日程表的mapper
 * @Author Waynejwei
 * @LastChangeDate 2020/11/17 18:38
 * @Version v1.0
 */

@Mapper
public interface ScheduleMapper {

    /**
     * @MethodName findScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 查找日程表by日程id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.Schedule
     * @LastChangeDate 2020/11/17
     */
    Schedule findScheduleByScheduleId(int scheduleId);

    /**
     * @MethodName findReadyToStartSchedule
     * @param startTime 要截至的开始时间
     * @param nowTime 当前时间
     * @Description 获取开始时间小于等于要截至的开始时间
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Schedule>
     * @LastChangeDate 2020/11/17
     */
    List<Schedule> findReadyToStartSchedule(Date startTime, Date nowTime);

    /**
     * @MethodName findReadyToEndSchedule
     * @param endTime 要截至的结束时间
     * @param nowTime 当前时间
     * @Description 获取快要结束的日程
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Schedule>
     * @LastChangeDate 2020/11/20
     */
    List<Schedule> findReadyToEndSchedule(Date endTime, Date nowTime);

    /**
     * @MethodName findDoingSchedule
     * @param nowTime 当前时间
     * @Description 获取正在进行的日程
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Schedule>
     * @LastChangeDate 2020/11/20
     */
    List<Schedule> findDoingSchedule(Date nowTime);

    /**
     * @MethodName findEndSchedule
     * @param nowTime 当前时间
     * @Description 获取结束的日程
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Schedule>
     * @LastChangeDate 2020/11/20
     */
    List<Schedule> findEndSchedule(Date nowTime);

    /**
     * @MethodName insertSchedule
     * @param schedule 日程
     * @Description 添加日程信息
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/17
     */
    int insertSchedule(Schedule schedule);

    /**
     * @MethodName updateScheduleByScheduleId
     * @param schedule 日程信息
     * @Description 修改日程信息by日程id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/17
     */
    int updateScheduleByScheduleId(Schedule schedule);

    /**
     * @MethodName deleteScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 删除日程表by日程id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/17
     */
    int deleteScheduleByScheduleId(int scheduleId);
}