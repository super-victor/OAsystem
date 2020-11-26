package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.MeetingMapper;
import com.sicnu.oasystem.pojo.Meeting;
import com.sun.xml.bind.v2.model.core.ID;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MeetingServicelmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/24 23:26
 * @Version v1.0
 */

public class MeetingServicelmpl implements MeetingService{

    @Resource
    MeetingMapper meetingMapper;

    @Override
    public BackFrontMessage getAllMeetings() {
        List<Meeting>meetings=meetingMapper.getAllMeetings();
        if (meetings==null){
            return new BackFrontMessage(500,"获取所有的会议失败",null);
        }else {
            return new BackFrontMessage(200,"获取所有会议成功",meetings);
        }
    }

    @Override
    public BackFrontMessage getMeetingById(Integer meetingId) {
        Meeting meeting=meetingMapper.getMeetingById(meetingId);
        if (meeting==null){
            return new BackFrontMessage(500,"获取会议失败",null);
        }else {
            return new BackFrontMessage(200,"获取会议成功",meeting);
        }
    }

    @Override
    public BackFrontMessage deleteMeeting(Integer meetingId) {

        int res=0;
        res=meetingMapper.deleteMeeting(meetingId);
        if (res==0){
            return new BackFrontMessage(500,"删除会议失败",null);
        }else {
            return new BackFrontMessage(200,"删除会议成功",null);
        }
    }

    @Override
    public BackFrontMessage addOrderMeeting(Integer meetingid, Integer status, Integer employeeid, String name, Date startTime, Date endtime, Integer peoplenum) {
        int res=0;
        res=meetingMapper.addOrderMeeting(meetingid,status,employeeid,name,startTime,endtime,peoplenum);
        if(res==0){
            return new BackFrontMessage(500,"预约会议失败",null);
        }else {
            return new BackFrontMessage(200,"预约会议成功",null);
        }
    }

    @Override
    public BackFrontMessage approveMeeting(Integer meetingid, Integer appoinmentstatus) {
        if(appoinmentstatus!=1||appoinmentstatus!=-1){
            return new BackFrontMessage(500,"会议审批失败",null);
        }else {
            int res=0;
            res=meetingMapper.approveMeeting(meetingid,appoinmentstatus);
            if (res==0){
                return new BackFrontMessage(500,"审批会议失败",null);
            }else {
                return new BackFrontMessage(200,"审批会议成功",null);
            }
        }
    }
}

