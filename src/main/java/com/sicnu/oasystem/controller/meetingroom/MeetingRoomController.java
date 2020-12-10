package com.sicnu.oasystem.controller.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.MeetingRoom;
import com.sicnu.oasystem.service.meetingroom.MeetingRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName MeetingRoomController
 * @Description 会议室控制器
 * @Author pan
 * @LastChangeDate 2020/11/13 14:32
 * @Version v1.0
 */
@Api(tags = "会议室管理")
@RestController
@Validated
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

    /**
     * @MethodName addMeetingRoom
     * @param meetingRoom
     * @Description 添加会议室
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    @PostMapping("/addMeetingRoom")
    @ApiOperation("添加会议室")
    public BackFrontMessage addMeetingRoom(@Validated MeetingRoom meetingRoom){
        return meetingRoomService.addMeetingRoom(meetingRoom.getName(),meetingRoom.getPlace(),
                meetingRoom.getIsOccapy(),meetingRoom.getMaxperson());
    }

    /**
     * @MethodName deleteMeetingRoom
     * @param MeetingRoomId 会议室Id
     * @Description 删除会议室
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    @DeleteMapping("/deleteMeetingRoom")
    @ApiOperation("删除会议室")
    public BackFrontMessage deleteMeetingRoom(@Validated  @NotNull(message = "会议室id不能为空") Integer MeetingRoomId){
        return meetingRoomService.deleteMeetingRoom(MeetingRoomId);
    }

    /**
     * @MethodName getMeetingRoomById
     * @param MeetingRoomId 会议室Id
     * @Description 通过会议室Id获取会议室信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    @GetMapping("/getMeetingRoomById")
    @ApiOperation("通过Id获取会议室")
    public BackFrontMessage getMeetingRoomById(@Validated @NotNull(message = "会议室id不能为空") Integer MeetingRoomId){
        return meetingRoomService.getMeetingRoomById(MeetingRoomId);
    }
    /**
     * @MethodName getAllMeetingRoomByStorey
     * @param
     * @Description 按楼层获取会议室信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    @ApiOperation("按楼层获取会议室信息")
    @GetMapping("/getAllMeetingRoomByStorey")
    public BackFrontMessage getAllMeetingRoomByStorey(){
        return meetingRoomService.getAllMeetingRoomByStorey();
    }
}

