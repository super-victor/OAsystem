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
     * @param place
     * @param isOccapy
     * @param MeetingRoomName
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
    public Integer uodateMeetingRoomNotIsOccapy();


    public BackFrontMessage getMeetingRoomById(Integer meetingRoomId);

    public BackFrontMessage updateMeetingRoom(Integer MeetingRoomId,String place, Integer isOccapy, String MeetingRoomName,int maxpserson);

    public BackFrontMessage deleteMeetingRoom(Integer MeetingRoomId);

    public BackFrontMessage addMeetingRoom(String name,String place,Integer isOccapy,Integer maxperson);

    BackFrontMessage getAllMeetingRoomByStorey();
}
