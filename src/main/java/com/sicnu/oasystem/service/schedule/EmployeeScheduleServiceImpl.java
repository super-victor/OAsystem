package com.sicnu.oasystem.service.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.mapper.EmployeeScheduleMapper;
import com.sicnu.oasystem.mapper.ScheduleMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.EmployeeSchedule;
import com.sicnu.oasystem.pojo.Schedule;
import com.sicnu.oasystem.util.LogUtil;
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

    @Resource
    ScheduleService scheduleService;

    @Resource
    LogUtil logUtil;

    @Override
    public BackFrontMessage insertEmployeeSchedule(EmployeeSchedule employeeSchedule) {
        //添加时查询此人是否已经加入此日程
        EmployeeSchedule em = employeeScheduleMapper.findEmployeeScheduleByEmployeeIdAndScheduleId(
                employeeSchedule.getEmployeeId(),
                employeeSchedule.getScheduleId());
        if (em != null) {
            return new BackFrontMessage(500,"此人已经加入该日程，不能再次加入",null);
        }
        int result = employeeScheduleMapper.insertEmployeeSchedule(employeeSchedule);
        if (result <= 0){
            logUtil.customException(employeeSchedule.getEmployeeId()+"用户添加日程映射失败，日程id为："+employeeSchedule.getScheduleId());
            return new BackFrontMessage(500,"添加职工日程映射失败",null);
        }else{
            logUtil.insertInfo(employeeSchedule.getEmployeeId()+"用户添加日程映射成功，日程id为："+employeeSchedule.getScheduleId());
            return new BackFrontMessage(200,"添加职工日程映射成功",employeeSchedule.getEmployeeScheduleId());
        }
    }

    @Override
    public BackFrontMessage deleteEmployeeSchedule(int employeeScheduleId) {
        EmployeeSchedule employeeSchedule = employeeScheduleMapper
                .findEmployeeScheduleByEmployeeScheduleId(employeeScheduleId);
        if (dontHasRoleToManageSchedule(employeeSchedule.getScheduleId())){
            return new BackFrontMessage(500,"您不是日程管理者，无法剔除此人",null);
        }
        Schedule schedule = scheduleMapper.findScheduleByScheduleId(employeeSchedule.getScheduleId());
        if (employeeSchedule.getEmployeeId().equals(schedule.getLeader())){
            return new BackFrontMessage(500,"无法退出此日程，退出前请转交日程管理权!",null);
        }
        int result = employeeScheduleMapper.deleteEmployeeScheduleByEmployeeScheduleId(employeeScheduleId);
        Integer employeeId = UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId();
        if (result <= 0){
            logUtil.customException(employeeId+"用户删除职工日程映射失败，职工日程id为："+employeeScheduleId);
            return new BackFrontMessage(500,"删除职工日程映射失败",null);
        }else{
            logUtil.deleteInfo(employeeId+"用户删除职工日程映射成功，职工日程id为："+employeeScheduleId);
            return new BackFrontMessage(200,"删除职工日程映射成功",null);
        }
    }

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
    public BackFrontMessage findReadyToStartEmployeeSchedule(long intervalTime) {
        List<Schedule> scheduleList = scheduleService.findReadyToStartSchedule(intervalTime);
        return new BackFrontMessage(200,"查询成功",getEmployeeScheduleList(scheduleList));
    }

    @Override
    public BackFrontMessage findDoingEmployeeSchedule() {
        List<Schedule> scheduleList = scheduleService.findDoingSchedule();
        return new BackFrontMessage(200,"查询成功",getEmployeeScheduleList(scheduleList));
    }

    @Override
    public BackFrontMessage findBeReadyToEndEmployeeSchedule(long intervaTime) {
        List<Schedule> scheduleList = scheduleService.findReadyToEndSchedule(intervaTime);
        return new BackFrontMessage(200,"查询成功",getEmployeeScheduleList(scheduleList));
    }

    @Override
    public BackFrontMessage findEndEmployeeSchedule() {
        List<Schedule> scheduleList = scheduleService.findEndSchedule();
        return new BackFrontMessage(200,"查询成功",getEmployeeScheduleList(scheduleList));
    }

    /**
     * @MethodName hasRoleToManageSchedule
     * @param scheduleId 日程id
     * @Description 是否有权操作日程，没有权限返回true
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/19
     */
    private boolean dontHasRoleToManageSchedule(int scheduleId){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        Schedule oldSchedule = scheduleMapper.findScheduleByScheduleId(scheduleId);
        return !currentEmployee.getEmployeeId().equals(oldSchedule.getLeader());
    }

    /**
     * @MethodName getEmployeeScheduleList
     * @param scheduleList 日程列表
     * @Description 返回职工所加入的日程列表
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Schedule>
     * @LastChangeDate 2020/11/20
     */
    private List<Schedule> getEmployeeScheduleList(List<Schedule> scheduleList){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<Schedule> list = new ArrayList();
        for (Schedule schedule : scheduleList) {
            //查询此日程职工是否加入
            EmployeeSchedule employeeSchedule = employeeScheduleMapper
                    .findEmployeeScheduleByEmployeeIdAndScheduleId(currentEmployee.getEmployeeId(), schedule.getScheduleId());
            if (employeeSchedule != null) {
                list.add(schedule);
            }
        }
        return list;
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
            map.put("schedule",schedule);
            resultMap.add(map);
        }
        return resultMap;
    }
}
