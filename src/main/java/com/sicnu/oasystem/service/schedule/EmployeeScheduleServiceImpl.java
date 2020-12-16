package com.sicnu.oasystem.service.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.mapper.EmployeeScheduleMapper;
import com.sicnu.oasystem.mapper.ScheduleMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.Schedule;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName EmployeeScheduleServiceImpl
 * @Description 职工日程对应服务的实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/20 15:37
 * @Version v1.0
 */

@Service
@Slf4j
public class EmployeeScheduleServiceImpl implements EmployeeScheduleService {

    @Resource
    EmployeeScheduleMapper employeeScheduleMapper;

    @Resource
    ScheduleMapper scheduleMapper;

    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public BackFrontMessage findEmployeeScheduleByEmployeeId() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<Integer> scheduleIdList = employeeScheduleMapper
                .findEmployeeScheduleByEmployeeId(currentEmployee.getEmployeeId());
        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
    }

    @Override
    public BackFrontMessage findSelfScheduleByDate(Date start, Date end) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<Integer> scheduleIdList = employeeScheduleMapper
                .findSelfScheduleByDate(currentEmployee.getEmployeeId(), start, end);
        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
    }



    @Override
    public BackFrontMessage findCompanyScheduleByDate(Date start, Date end) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<Integer> scheduleIdList = employeeScheduleMapper
                .findCompanyScheduleByDate(currentEmployee.getEmployeeId(), start, end);
        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
    }

    @Override
    public BackFrontMessage findSelfScheduleByEmployeeId() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<Integer> scheduleIdList = employeeScheduleMapper
                .findScheduleByEmployeeIdAndIsCompany(currentEmployee.getEmployeeId(), 0);
        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
    }

    @Override
    public BackFrontMessage findCompanyScheduleByEmployeeId() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<Integer> scheduleIdList = employeeScheduleMapper
                .findScheduleByEmployeeIdAndIsCompany(currentEmployee.getEmployeeId(), 1);
        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
    }

    @Override
    public BackFrontMessage findEmployeeScheduleByScheduleId(int scheduleId) {
        List<Integer> employeeIdList = employeeScheduleMapper
                .findEmployeeScheduleByScheduleId(scheduleId);
        List<Employee> employeeList = new ArrayList<>();
        for (Integer employeeId : employeeIdList) {
            Employee employee = employeeMapper.findEmployeeByEmployeeId(employeeId);
            employeeList.add(employee);
        }
        return new BackFrontMessage(200,"查找成功",employeeList);
    }

    @Override
    public BackFrontMessage findEmployeeScheduleCount() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        int companyScheduleCount = employeeScheduleMapper
                .findEmployeeCompanyScheduleCount(currentEmployee.getEmployeeId());
        int selfScheduleCount = employeeScheduleMapper
                .findEmployeeSelfScheduleCount(currentEmployee.getEmployeeId());
        Map<String, Integer> map = new HashMap<>();
        map.put("companyScheduleCount", companyScheduleCount);
        map.put("selfScheduleCount", selfScheduleCount);
        return new BackFrontMessage(200, null, map);
    }

    @Override
    public BackFrontMessage findEmployeeScheduleNotStart() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        Date date = new Date();
        Schedule companySchedule = employeeScheduleMapper
                .findEmployeeCompanySchedule(currentEmployee.getEmployeeId(), date);
        Schedule selfSchedule = employeeScheduleMapper
                .findEmployeeSelfSchedule(currentEmployee.getEmployeeId(), date);
        Map<String, Schedule> map = new HashMap<>();
        map.put("companyScheule", companySchedule);
        map.put("selfSchedule", selfSchedule);
        return new BackFrontMessage(200, null, map);
    }

    /**
     * @MethodName getScheduleListByEmployeeSchduleList
     * @param scheduleIdList 职工日程列表
     * @Description 通过职工日程映射列表，返回职工参与的日程信息
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Schedule>
     * @LastChangeDate 2020/11/30
     */
    private List<Map<String,Object>> getScheduleListByEmployeeSchduleList(List<Integer> scheduleIdList){
        List<Map<String,Object>> resultMap = new ArrayList<>();
        for (Integer scheduleId : scheduleIdList) {
            Map<String,Object> map = new HashMap<>(16);
            map.put("scheduleId",scheduleId);
            Schedule schedule = scheduleMapper.findScheduleByScheduleId(scheduleId);
            map.put("startTime",schedule.getStartTime());
            map.put("endTime",schedule.getEndTime());
            map.put("content",schedule.getContent());
            map.put("location",schedule.getLocation());
            map.put("remark",schedule.getRemark());
            map.put("type",schedule.getType());
            map.put("leader",employeeMapper.findEmployeeByEmployeeId(schedule.getLeader()).getName());
            List<Integer> joinerList = employeeScheduleMapper.findEmployeeScheduleByScheduleId(scheduleId);
            log.info("joinerList --> "+joinerList);
            List<String> joiner = new ArrayList<>();
            for (Integer employeeId : joinerList) {
                Employee employee = employeeMapper.findEmployeeByEmployeeId(employeeId);
                joiner.add(employee.getName());
            }
            map.put("joiner",joiner);
            resultMap.add(map);
        }
        return resultMap;
    }
}
