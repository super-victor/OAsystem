package com.sicnu.oasystem.service.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeScheduleMapper;
import com.sicnu.oasystem.mapper.ScheduleMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.EmployeeSchedule;
import com.sicnu.oasystem.pojo.Schedule;
import com.sicnu.oasystem.service.schedule.ScheduleService;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ScheduleServiceImpl
 * @Description 日程管理服务的实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/19 20:32
 * @Version v1.0
 */

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    ScheduleMapper scheduleMapper;

    @Resource
    EmployeeScheduleMapper employeeScheduleMapper;


    @Override
    public BackFrontMessage insertSchedule(Schedule schedule) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        schedule.setLeader(currentEmployee.getEmployeeId());
        if (isTimeCorrect(schedule)){
            return new BackFrontMessage(500,"结束时间小于开始时间",null);
        }
        int result = scheduleMapper.insertSchedule(schedule);
        if (result <= 0){
            return new BackFrontMessage(500,"添加日程失败",null);
        }else{
            EmployeeSchedule employeeSchedule = new EmployeeSchedule();
            employeeSchedule.setEmployeeId(currentEmployee.getEmployeeId());
            employeeSchedule.setScheduleId(schedule.getScheduleId());
            int result2 = employeeScheduleMapper.insertEmployeeSchedule(employeeSchedule);
            if (result2 <= 0){
                return new BackFrontMessage(500,"添加职工日程映射失败",schedule.getScheduleId());
            }else{
                return new BackFrontMessage(200,"添加日程成功",schedule.getScheduleId());
            }
        }
    }

    @Override
    public BackFrontMessage updateScheduleByScheduleId(Schedule schedule, int scheduleId) {
        schedule.setScheduleId(scheduleId);
        if (dontHasRoleToManageSchedule(scheduleId)){
            return new BackFrontMessage(500,"您不是日程领导者无权修改此日程",null);
        }
        if (isTimeCorrect(schedule)){
            return new BackFrontMessage(500,"结束时间小于开始时间",null);
        }
        if (schedule.getLeader() != null){
            EmployeeSchedule es = employeeScheduleMapper
                    .findEmployeeScheduleByEmployeeIdAndSchedule(schedule.getLeader(), scheduleId);
            if (es == null) {
                return new BackFrontMessage(500,"此人没有加入到日程中，不能转交管理权！",null);
            }
        }
        int result = scheduleMapper.updateScheduleByScheduleId(schedule);
        if (result <= 0){
            return new BackFrontMessage(500,"修改日程失败",null);
        }else{
            return new BackFrontMessage(200,"修改日程成功",null);
        }
    }

    @Override
    public BackFrontMessage deleteScheduleByScheduleId(int scheduleId) {
        if (dontHasRoleToManageSchedule(scheduleId)){
            return new BackFrontMessage(500,"您不是日程领导者无权删除此日程",null);
        }
        int result = scheduleMapper.deleteScheduleByScheduleId(scheduleId);
        if (result <= 0){
            return new BackFrontMessage(500,"删除日程失败",null);
        }else{
            return new BackFrontMessage(200,"删除日程成功",null);
        }
    }

    @Override
    public BackFrontMessage findScheduleByScheduleId(int scheduleId) {
        Schedule schedule = scheduleMapper.findScheduleByScheduleId(scheduleId);
        return new BackFrontMessage(200,"查找日成功",schedule);
    }

    @Override
    public List<Schedule> findReadyToStartSchedule(long intervalTime) {
        intervalTime = intervalTime * 1000 * 60;  //化为以分钟为单位
        long nowTime = new Date().getTime();
        Date startTime = new Date(nowTime + intervalTime);
        return scheduleMapper.findReadyToStartSchedule(startTime, new Date());
    }

    @Override
    public List<Schedule> findDoingSchedule() {
        return scheduleMapper.findDoingSchedule(new Date());
    }

    @Override
    public List<Schedule> findEndSchedule() {
        return scheduleMapper.findEndSchedule(new Date());
    }

    @Override
    public List<Schedule> findReadyToEndSchedule(long intervalTime) {
        intervalTime = intervalTime * 1000 * 60;  //化为以分钟为单位
        long nowTime = new Date().getTime();
        Date endTime = new Date(nowTime + intervalTime);
        return scheduleMapper.findReadyToEndSchedule(endTime, new Date());
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
     * @MethodName isTimeCorrect
     * @param schedule 日程
     * @Description 查看日程结束时间是都小于开始时间，是返回true
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/19
     */
    private boolean isTimeCorrect(Schedule schedule){
        if (schedule.getScheduleId() != null){  //修改时的判断时间是否合理
            if (schedule.getStartTime() == null){
                Schedule nowSchedule = scheduleMapper.findScheduleByScheduleId(schedule.getScheduleId());
                if (schedule.getEndTime() == null){   //都为空，不修改时间
                    return false;
                }else{
                    return schedule.getEndTime().compareTo(nowSchedule.getStartTime()) < 0;
                }
            }else{
                if (schedule.getEndTime() == null){
                    Schedule nowSchedule = scheduleMapper.findScheduleByScheduleId(schedule.getScheduleId());
                    return nowSchedule.getEndTime().compareTo(schedule.getStartTime()) < 0;
                }else{
                    return schedule.getEndTime().compareTo(schedule.getStartTime()) < 0;
                }
            }
        }else{  //增加时判断时间是否合理
            if (schedule.getStartTime() != null && schedule.getEndTime() != null){
                return schedule.getEndTime().compareTo(schedule.getStartTime()) < 0;
            }else{
                return true;
            }
        }
    }
}
