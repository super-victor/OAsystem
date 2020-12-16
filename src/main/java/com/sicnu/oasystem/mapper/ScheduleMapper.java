package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

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

    /**
     * @MethodName findCompanyScheduleByDate
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @Description 获取startTime到endTime之间的日程
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int findCompanyScheduleByDate(Date startTime, Date endTime);
}