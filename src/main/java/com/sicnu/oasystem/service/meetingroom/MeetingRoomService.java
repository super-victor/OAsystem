package com.sicnu.oasystem.service.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.MeetingRoom;

import java.util.List;

/**
 * @InterfaceName MeetingRoomService
 * @Description 会议室的Service
 * @Author pan
 * @LastChangeDate 2020/11/13 14:43
 * @Version v1.0
 */

public interface MeetingRoomService {

    /**
     * @MethodName getMeetRoomInfo
     * @param place 地点
     * @param isOccapy 是否占用
     * @param MeetingRoomName 会议室名称
     * @Description 按条件获取或直接获取会议室的信息
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.MeetingRoom>
     * @LastChangeDate 2020/11/13
     */
    public BackFrontMessage getMeetRoomInfo(String place, Integer isOccapy, String MeetingRoomName);

    /**
     * @MethodName updateMeetingRoomIsOccapy
     * @param
     * @Description 修改当前时间有会议的会议室占用信息
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/13
     */
    public Integer updateMeetingRoomIsOccapy();

    /**
     * @MethodName uodateMeetingRoomNotIsOccapy
     * @param
     * @Description 修改当前时间没有会议的会议室的占用信息
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/13
     */
    public Integer updateMeetingRoomNotIsOccapy();


    /**
     * @MethodName getMeetingRoomById
     * @param meetingRoomId  会议室Id
     * @Description 按会议室Id获取会议室信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    public BackFrontMessage getMeetingRoomById(Integer meetingRoomId);

    /**
     * @MethodName updateMeetingRoom
     * @param MeetingRoomId 会议室Id
     * @param place 地点
     * @param isOccapy 是否占用
     * @param MeetingRoomName 会议室名称
     * @param maxpserson 最大容纳人数
     * @Description 跟新会议室
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    public BackFrontMessage updateMeetingRoom(Integer MeetingRoomId,String place, Integer isOccapy, String MeetingRoomName,int maxpserson);

    /**
     * @MethodName deleteMeetingRoom
     * @param MeetingRoomId 会议室Id
     * @Description 删除会议室Id
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    public BackFrontMessage deleteMeetingRoom(Integer MeetingRoomId);

    /**
     * @MethodName addMeetingRoom
     * @param name 会议室名称
     * @param place 地点
     * @param isOccapy 是否被占用
     * @param maxperson 最大容纳人数
     * @Description 添加会议室
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    public BackFrontMessage addMeetingRoom(String name,String place,Integer isOccapy,Integer maxperson);

    /**
     * @MethodName getAllMeetingRoomByStorey
     * @param
     * @Description  按楼层获取会议室
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage getAllMeetingRoomByStorey();
}
