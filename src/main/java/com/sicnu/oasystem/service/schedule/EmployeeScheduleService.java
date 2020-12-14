package com.sicnu.oasystem.service.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;

import java.text.ParseException;
import java.util.Date;

/**
 * @InterfaceName EmployeeScheduleService
 * @Description 职工日程服务的接口
 * @Author Waynejwei
 * @LastChangeDate 2020/11/20 15:27
 * @Version v1.0
 */

public interface EmployeeScheduleService {

    /**
     * @MethodName findEmployeeScheduleByEmployeeId
     * @Description 查找职工所有日程信息
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage findEmployeeScheduleByEmployeeId();

    /**
     * @MethodName findSelfScheduleByDate
     * @param start 开始时间
     * @param end 结束时间
     * @Description 获取该职工在start、end之间的个人日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/30
     */
    BackFrontMessage findSelfScheduleByDate(Date start, Date end) throws ParseException;

    /**
     * @MethodName findCompanyScheduleByDate
     * @param start 开始时间
     * @param end 结束时间
     * @Description 获取该职工在start、end之间的公司日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/30
     */
    BackFrontMessage findCompanyScheduleByDate(Date start, Date end);

    /**
     * @MethodName findSelfScheduleByEmployeeId
     * @Description 获取职工所有的个人日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/1
     */
    BackFrontMessage findSelfScheduleByEmployeeId();

    /**
     * @MethodName findCompanyScheduleByEmployeeId
     * @Description 获取职工所有的公司日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/1
     */
    BackFrontMessage findCompanyScheduleByEmployeeId();

    /**
     * @MethodName findEmployeeScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 查找某一日程下的所有职工
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage findEmployeeScheduleByScheduleId(int scheduleId);

}