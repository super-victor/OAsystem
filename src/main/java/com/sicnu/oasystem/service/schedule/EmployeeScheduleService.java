package com.sicnu.oasystem.service.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.EmployeeSchedule;

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
     * @MethodName insertEmployeeSchedule
     * @param employeeSchedule 职工日程
     * @Description 添加职工日程对应
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage insertEmployeeSchedule(EmployeeSchedule employeeSchedule);

    /**
     * @MethodName deleteEmployeeSchedule
     * @param employeeScheduleId 日程id
     * @Description 删除日程by日程id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage deleteEmployeeSchedule(int employeeScheduleId);

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

    /**
     * @MethodName findReadyToStartEmployeeSchedule
     * @param intervalTime 离开始时间的间隔时间
     * @Description 查找某一职工快要开始的日程信息
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage findReadyToStartEmployeeSchedule(long intervalTime);

    /**
     * @MethodName findDoingEmployeeSchedule
     * @Description 查找某一职工正在进行的日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage findDoingEmployeeSchedule();

    /**
     * @MethodName findBeReadyToEndEmployeeSchedule
     * @param intervaTime 离结束时间的间隔时间
     * @Description 查找某一职工快要结束的日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage findBeReadyToEndEmployeeSchedule(long intervaTime);

    /**
     * @MethodName findEndEmployeeSchedule
     * @Description 查询某一职工快要截至的日程
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    BackFrontMessage findEndEmployeeSchedule();
}