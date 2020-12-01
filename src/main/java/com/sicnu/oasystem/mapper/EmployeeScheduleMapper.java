package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.EmployeeSchedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @InterfaceName EmployeeScheduleMapper
 * @Description 操控EmployeeSchedule表的mapper
 * @Author Waynejwei
 * @LastChangeDate 2020/11/20 15:14
 * @Version v1.0
 */

@Mapper
public interface EmployeeScheduleMapper {

    /**
     * @MethodName insertEmployeeSchedule
     * @param employeeSchedule 职工日程
     * @Description 添加职工日程映射
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/20
     */
    int insertEmployeeSchedule(EmployeeSchedule employeeSchedule);

    /**
     * @MethodName deleteEmployeeScheduleByEmployeeScheduleId
     * @param employeeScheduleId 职工日程id
     * @Description 删除职工日程映射by职工日程id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/20
     */
    int deleteEmployeeScheduleByEmployeeScheduleId(int employeeScheduleId);

    /**
     * @MethodName deleteEmployeeScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 删除日程信息by日程id
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2020/11/29
     */
    int deleteEmployeeScheduleByScheduleId(int scheduleId);

    /**
     * @MethodName findEmployeeScheduleByScheduleId
     * @param scheduleId 日程id
     * @Description 查找某一日程下的所有职工
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.EmployeeSchedule>
     * @LastChangeDate 2020/11/20
     */
    List<Integer> findEmployeeScheduleByScheduleId(int scheduleId);

    /**
     * @MethodName findEmployeeScheduleByEmployeeId
     * @param employeeId 职工id
     * @Description 查找某一职工的所有日程id
     * @Author Waynejwei
     * @Return java.util.List<java.lang.Integer>
     * @LastChangeDate 2020/11/20
     */
    List<Integer> findEmployeeScheduleByEmployeeId(int employeeId);

    /**
     * @MethodName findEmployeeScheduleByEmployeeIdAndScheduleId
     * @param employeeId 职工id
     * @param scheduleId 日程id
     * @Description 查询职工日程by职工id和日程id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.EmployeeSchedule
     * @LastChangeDate 2020/11/20
     */
    EmployeeSchedule findEmployeeScheduleByEmployeeIdAndScheduleId(int employeeId, int scheduleId);

    /**
     * @MethodName findEmployeeScheduleByEmployeeScheduleId
     * @param employeeScheduleId 职工日程id
     * @Description 查找职工日程by职工日程id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.pojo.EmployeeSchedule
     * @LastChangeDate 2020/11/20
     */
    EmployeeSchedule findEmployeeScheduleByEmployeeScheduleId(int employeeScheduleId);

    /**
     * @MethodName findSelfScheduleByDate
     * @param employeeId 职工id
     * @param start 开始时间
     * @param end 结束时间
     * @Description 获取start到end之间的所有个人日程
     * @Author Waynejwei
     * @Return java.util.List<java.lang.Integer>
     * @LastChangeDate 2020/11/30
     */
    List<Integer> findSelfScheduleByDate(int employeeId, Date start, Date end);

    /**
     * @MethodName findCompanyScheduleByDate
     * @param employeeId 职工id
     * @param start 开始时间
     * @param end 结束时间
     * @Description 获取start到end之间的所有公司日程
     * @Author Waynejwei
     * @Return java.util.List<java.lang.Integer>
     * @LastChangeDate 2020/11/30
     */
    List<Integer> findCompanyScheduleByDate(int employeeId, Date start, Date end);

    /**
     * @MethodName findScheduleByEmployeeIdAndIsCompany
     * @param employeeId 职工id
     * @param isCompany 是否为公司日程
     * @Description 获取职工日程
     * @Author Waynejwei
     * @Return java.util.List<java.lang.Integer>
     * @LastChangeDate 2020/12/1
     */
    List<Integer> findScheduleByEmployeeIdAndIsCompany(int employeeId, int isCompany);
}