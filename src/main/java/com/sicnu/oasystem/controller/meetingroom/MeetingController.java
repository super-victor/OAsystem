package com.sicnu.oasystem.controller.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.meetingroom.MeetingServicelmpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MeetingController
 * @Description 会议控制器
 * @Author pan
 * @LastChangeDate 2020/11/13 14:32
 * @Version v1.0
 */
@Api(tags = "会议管理")
@RestController
public class MeetingController {

    @Resource
    MeetingServicelmpl meetingServicelmpl;

    @ApiOperation("获取所有会议")
    @GetMapping("/getAllMeetings")
    public BackFrontMessage getAllMeetings(){
        return meetingServicelmpl.getAllMeetings();
    };

    @GetMapping("/getMeetingById")
    @ApiOperation("按会议Id获取会议信息")
    public BackFrontMessage getMeetingById(Integer meetingId){
        return meetingServicelmpl.getMeetingById(meetingId);
    }

    @ApiOperation("会议审批会议")
    @PutMapping("/approveMeeting")
    public BackFrontMessage approveMeeting(@RequestParam Integer meetingid, @RequestParam Integer appoinmentstatus){
        return meetingServicelmpl.approveMeeting(meetingid,appoinmentstatus);
    }

    @ApiOperation("获取未被审批的会议")
    @GetMapping("/getNotApprovedMeeting")
    public BackFrontMessage getNotApprovedMeeting(){
        return meetingServicelmpl.getNotApprovedMeeting();
    }

    @ApiOperation("预约会议")
    @PostMapping("/addOrderMeeting")
    public BackFrontMessage addOrderMeeting(@RequestParam Integer meetingroomid, @RequestParam Integer status, @RequestParam Integer employeeid, @RequestParam String name, @RequestParam String startTime,
                                            @RequestParam String endtime, @RequestParam Integer peoplenum, @RequestParam String remark){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date StartTime=null;
        Date Endtine=null;
        try {
            StartTime=sdf.parse(startTime);
            Endtine=sdf.parse(endtime);
        } catch (ParseException e) {
            return new BackFrontMessage(500,"预约会议失败",null);
        }
        if(StartTime.after(Endtine)){
            return new BackFrontMessage(500,"预约会议信息失败",null);
        }
        return meetingServicelmpl.addOrderMeeting(meetingroomid,status,employeeid,name,StartTime,Endtine,peoplenum,remark);
    }
}

