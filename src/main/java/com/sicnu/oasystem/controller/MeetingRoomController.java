package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.MeetingRoomService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @LastChangeDate 2020/11/13
     */
    @GetMapping("/MeetingRoomInfo")
    public BackFrontMessage getMeetingRoomInfo(String place,Integer isOccapy,String MeetingRoomName){
        return new  BackFrontMessage(200,"获取成功",meetingRoomService.getMeetRoomInfo(place,isOccapy,MeetingRoomName));
    }
}

