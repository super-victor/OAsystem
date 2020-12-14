package com.sicnu.oasystem.service.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.DataSeeAbleA;
import com.sicnu.oasystem.pojo.Schedule;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @Description 添加个人日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/17
     */
    BackFrontMessage insertSelfSchedule(Schedule schedule);

    /**
     * @MethodName insertCompanySchedule
     * @param schedule 公司日程
     * @Description 添加公司日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/26
     */
    BackFrontMessage insertCompanySchedule(Schedule schedule);

    /**
     * @MethodName updateScheduleByScheduleId
     * @param schedule 日程
     * @param scheduleId 日程id
     * @param isCompany 是否为公司日程
     * @Description 修改日程信息by日程id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/17
     */
    BackFrontMessage updateScheduleByScheduleId(Schedule schedule, int scheduleId, int isCompany);

    /**
     * @MethodName deleteScheduleByScheduleId
     * @param scheduleId 日程id
     * @param isCompany 是否是公司日程
     * @Description 删除日程by日程id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/17
     */
    BackFrontMessage deleteScheduleByScheduleId(int scheduleId, int isCompany);

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
     * @MethodName findCompanyScheduleNumber
     * @Description 获取十五天以内，公司日程每天的个数
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/14
     */
    List<DataSeeAbleA> findCompanyScheduleNumber();


}