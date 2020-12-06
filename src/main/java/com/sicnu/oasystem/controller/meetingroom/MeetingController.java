package com.sicnu.oasystem.controller.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.meetingroom.MeetingServicelmpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName MeetingController
 * @Description 会议控制器
 * @Author pan
 * @LastChangeDate 2020/11/13 14:32
 * @Version v1.0
 */
@Api(tags = "会议管理")
@RestController
@Validated
public class MeetingController {

    @Resource
    MeetingServicelmpl meetingServicelmpl;

    @ApiOperation("获取所有预约成功的会议")
    @GetMapping("/getAllMeetings")
    public BackFrontMessage getAllMeetings(){
        return meetingServicelmpl.getAllMeetings();
    };

    @GetMapping("/getCurrentAllMeeting")
    public  BackFrontMessage getCurrentAllMeeting(){
        return meetingServicelmpl.getCurrentAllMeeting();
    }

    @GetMapping("/getMeetingById")
    @ApiOperation("按会议Id获取会议信息")
    public BackFrontMessage getMeetingById(@Validated @NotNull(message = "会议Id不能为空") Integer meetingId){
        return meetingServicelmpl.getMeetingById(meetingId);
    }

    @ApiOperation("会议审批会议")
    @PutMapping("/approveMeeting")
    public BackFrontMessage approveMeeting(@RequestParam @Validated @NotNull(message = "会议Id不能为空") Integer meetingid, @RequestParam @Validated @NotNull(message = "会议预约信息不能为空") Integer appoinmentstatus){
        return meetingServicelmpl.approveMeeting(meetingid,appoinmentstatus);
    }

    @ApiOperation("获取未被审批的会议")
    @GetMapping("/getNotApprovedMeeting")
    public BackFrontMessage getNotApprovedMeeting(){
        return meetingServicelmpl.getNotApprovedMeeting();
    }

    @ApiOperation("预约会议")
    @PostMapping("/addOrderMeeting")
    public BackFrontMessage addOrderMeeting(@RequestParam @Validated @NotNull(message = "会议是Id不能为空") Integer meetingroomid,
                                            @RequestParam @Validated @NotNull(message = "预约者不能为空") Integer employeeid,
                                            @RequestParam @Validated @NotBlank(message = "会议名称不能为空") String name,
                                            @RequestParam @Validated @NotBlank(message = "会议开始时间不能为空") String startTime,
                                            @RequestParam @Validated @NotBlank(message = "会议结束时间不能为空") String endtime,
                                            @RequestParam @Validated @NotNull(message = "人数不能为空") @Min(value = 0,message = "人数最小为0") Integer peoplenum,
                                            @RequestParam  String remark){
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
            return new BackFrontMessage(500,"预约会议失败",null);
        }
        return meetingServicelmpl.addOrderMeeting(meetingroomid,employeeid,name,StartTime,Endtine,peoplenum,remark);
    }
    //todo 查询某一天会议占用情况。

    @ApiOperation("获取某一天某一个会议室的会议预约")
    @PostMapping("/getAllMeetingTimeByRoomAndTime")
    BackFrontMessage getAllMeetingTimeByRoomAndTime(@Validated @NotNull(message = "会议室id不能为空") Integer meetingroomid,
                                                    @Validated @NotBlank(message = "会议时间不能为空") String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date starttime= null;
        Date enttime=null;
        try {
            starttime = sdf.parse(date);
            enttime=sdf.parse(date);
            Calendar calendar=new GregorianCalendar();
            calendar.setTime(enttime);
            calendar.add(Calendar.DATE,1);
            enttime=calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return meetingServicelmpl.getAllMeetingTimeByRoomAndTime(meetingroomid,starttime,enttime);
    }
}

