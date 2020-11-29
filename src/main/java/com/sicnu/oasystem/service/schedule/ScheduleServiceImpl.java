package com.sicnu.oasystem.service.schedule;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.mapper.EmployeeScheduleMapper;
import com.sicnu.oasystem.mapper.ScheduleMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.EmployeeSchedule;
import com.sicnu.oasystem.pojo.Role;
import com.sicnu.oasystem.pojo.Schedule;
import com.sicnu.oasystem.service.message.MessageService;
import com.sicnu.oasystem.util.DataUtil;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


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

    @Resource
    MessageService messageService;

    @Resource
    EmployeeMapper employeeMapper;


    @Override
    public BackFrontMessage insertSelfSchedule(Schedule schedule) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        schedule.setLeader(currentEmployee.getEmployeeId());
        schedule.setIsCompany(0);
        if (isTimeCorrect(schedule)){
            return new BackFrontMessage(500,"结束时间小于开始时间",null);
        }
        int result = scheduleMapper.insertSchedule(schedule);
        if (result <= 0){
            return new BackFrontMessage(500,"添加日程失败",null);
        }else{
            EmployeeSchedule employeeSchedule = new EmployeeSchedule();
            employeeSchedule.setScheduleId(schedule.getScheduleId());
            employeeSchedule.setEmployeeId(currentEmployee.getEmployeeId());
            int result2 = employeeScheduleMapper.insertEmployeeSchedule(employeeSchedule);
            if (result2 <= 0){
                return new BackFrontMessage(500,"添加员工日程映射失败",schedule.getScheduleId());
            }
            return new BackFrontMessage(200,"添加日程成功",schedule.getScheduleId());
        }
    }

    @Override
    public BackFrontMessage insertCompanySchedule(Schedule schedule, String joiner) {
        //TODO:转列表前，判断string是否符合格式(如：1,2,3,4)
        String[] joiners = joiner.split(",");
        List<String> joinerList = new ArrayList<>();
        //检查是否有重复的参与者。若有重复的，则自动去重。
        for (String join : joiners) {
            if (!joinerList.contains(join)) {
                joinerList.add(join);
            }
        }
        //判断设置的leader在不在参与人列表里面。如果不在则自动在列表中添加上
        if (!joinerList.contains(String.valueOf(schedule.getLeader()))) {
            joinerList.add(String.valueOf(schedule.getLeader()));
        }
        schedule.setIsCompany(1);
        if (isTimeCorrect(schedule)){
            return new BackFrontMessage(500,"结束时间小于开始时间",null);
        }
        int result = scheduleMapper.insertSchedule(schedule);
        if (result <= 0){
            return new BackFrontMessage(500,"添加日程失败",null);
        }else{
            //将参与者加入职工日程对应表
            for (String employeeId : joinerList) {
                EmployeeSchedule employeeSchedule = new EmployeeSchedule();
                //TODO:转int前需要判断是否可以转换为int
                employeeSchedule.setEmployeeId(Integer.parseInt(employeeId));
                employeeSchedule.setScheduleId(schedule.getScheduleId());
                int result2 = employeeScheduleMapper.insertEmployeeSchedule(employeeSchedule);
                if (result2 <= 0){
                    return new BackFrontMessage(500,"添加职工日程映射失败",schedule.getScheduleId());
                }
                //发送消息
                messageService.send(Integer.parseInt(employeeId), DataUtil.MESSAGE_TYPE_INFO, DataUtil.MESSAGE_TITLE_SCHEDULE, "您收到了一个关于'"+schedule.getContent()+"'的公司日程");
            }
            return new BackFrontMessage(200,"添加日程成功",schedule.getScheduleId());
        }
    }

    @Override
    public BackFrontMessage updateScheduleByScheduleId(Schedule schedule, int scheduleId, int isCompany) {
        schedule.setScheduleId(scheduleId);
        if (isTimeCorrect(schedule)){
            return new BackFrontMessage(500,"结束时间小于开始时间",null);
        }
        //如果是公司日程，则需要判断权限
        if (isCompany == 1){
            //判断是否为日程管理员或者日程领导者。若都不是，则无权修改
            int employeeId = UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId();
            int isScheduleManager = employeeMapper.findRoleByEmployeeIdAndRoleId(
                    employeeId, 4);
            if (isScheduleManager == 0 && dontHasRoleToManageSchedule(scheduleId)){
                return new BackFrontMessage(500,"您无权修改公司日程",null);
            }
            if (schedule.getLeader() != null){
                EmployeeSchedule es = employeeScheduleMapper
                        .findEmployeeScheduleByEmployeeIdAndScheduleId(schedule.getLeader(), scheduleId);
                if (es == null) {
                    return new BackFrontMessage(500,"此人没有加入到日程中，不能转交管理权！",null);
                }
            }
        }
        if (isCompany == 0){
            schedule.setLeader(null);  //个人日程的领导者默认为自己，不能修改
        }
        int result = scheduleMapper.updateScheduleByScheduleId(schedule);
        if (result <= 0){
            return new BackFrontMessage(500,"修改日程失败",null);
        }else{
            return new BackFrontMessage(200,"修改日程成功",null);
        }
    }

    @Override
    public BackFrontMessage deleteScheduleByScheduleId(int scheduleId, int isCompany) {
        //个人日程只有自己才可以删除，公司日程管理员才可以删除
        if (isCompany == 0){
            EmployeeSchedule employeeSchedule = employeeScheduleMapper.findEmployeeScheduleByEmployeeIdAndScheduleId(
                    UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),
                    scheduleId);
            if (employeeSchedule == null){
                return new BackFrontMessage(500,"无权删除此日程",null);
            }
        }
        List<Integer> employeeIdList = employeeScheduleMapper.findEmployeeScheduleByScheduleId(scheduleId);
        //删除日程前，需要删除职工日程映射
        int result1 = employeeScheduleMapper.deleteEmployeeScheduleByScheduleId(scheduleId);
        if (result1 <= 0){
            return new BackFrontMessage(500,"删除职工日程映射失败",null);
        }else{
            if (isCompany == 1){
                //删除职工日程后，通知职工日程结束
                for (Integer employeeId : employeeIdList) {
                    String content = scheduleMapper.findScheduleByScheduleId(scheduleId).getContent();
                    messageService.send(employeeId, DataUtil.MESSAGE_TYPE_INFO, DataUtil.MESSAGE_TITLE_SCHEDULE, "有关'"+content+"'内容的公司日程已经结束");
                }
            }
            int result2 = scheduleMapper.deleteScheduleByScheduleId(scheduleId);
            if (result2 <= 0){
                return new BackFrontMessage(500,"删除日程失败",null);
            }else{
                return new BackFrontMessage(200,"删除日程成功",null);
            }
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

        //TODO:查找公司日程、个人日程
    }
}
