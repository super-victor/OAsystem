package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.MeetingRoom;
import com.sicnu.oasystem.service.MeetingRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName MeetingRoomController
 * @Description 会议室控制器
 * @Author pan
 * @LastChangeDate 2020/11/13 14:32
 * @Version v1.0
 */
@Api(tags = "会议室管理")
@RestController
public class MeetingRoomController {

    @Resource
    MeetingRoomService meetingRoomService;

    /**
     * @MethodName getMeetingRoomInfo
     * @param place
     * @param isOccapy
     * @param MeetingRoomName
     * @Description 按条件获取或直接获取会议室的信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/20
     */
    @GetMapping("/getAllMeetingRoom")
    @ApiOperation("获取所有的会议室信息")
    public BackFrontMessage getMeetingRoomInfo(String place,Integer isOccapy,String MeetingRoomName){
        return meetingRoomService.getMeetRoomInfo(place,isOccapy,MeetingRoomName);
    }

    @PostMapping("/addMeetingRoom")
    @ApiOperation("添加会议室")
    public BackFrontMessage addMeetingRoom(@RequestBody MeetingRoom meetingRoom){
        return meetingRoomService.addMeetingRoom(meetingRoom.getName(),meetingRoom.getPlace(),
                meetingRoom.getIsOccapy(),meetingRoom.getMaxperson());
    }

    @DeleteMapping("/deleteMeetingRoom")
    @ApiOperation("删除会议室")
    public BackFrontMessage deleteMeetingRoom(Integer MeetingRoomId){
        return meetingRoomService.deleteMeetingRoom(MeetingRoomId);
    }

    @GetMapping("/getMeetingRoomById")
    @ApiOperation("通过Id获取会议室")
    public BackFrontMessage getMeetingRoomById(Integer MeetingRoomId){
        return meetingRoomService.getMeetingRoomById(MeetingRoomId);
    }
}

