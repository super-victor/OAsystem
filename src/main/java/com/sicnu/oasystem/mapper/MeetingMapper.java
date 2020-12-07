package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Meeting;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @InterfaceName MeetingMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/24 23:06
 * @Version v1.0
 */
@Mapper
public interface MeetingMapper {

    /**
     * @MethodName getAllMeetings
     * @param
     * @Description 获取审批所有通过且的会议信息
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Meeting>
     * @LastChangeDate 2020/11/28
     */
    List<Meeting>getAllMeetings();

    Integer completeMeetings();

    List<Meeting>getCurrentAllMeeting();

    /**
     * @MethodName getMeetingById
     * @param meetingId
     * @Description 添加描述
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.Meeting
     * @LastChangeDate 2020/11/28
     */
    Meeting getMeetingById(Integer meetingId);

    /**
     * @MethodName deleteMeeting
     * @param meetingId
     * @Description 通过删除会议
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/28
     */
    Integer deleteMeeting(Integer meetingId);

    /**
     * @MethodName addOrderMeeting
     * @param meetingroomid
     * @param status
     * @param employeeid
     * @param name
     * @param startTime
     * @param endtime
     * @param peoplenum
     * @param remark
     * @Description 预约会议
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/28
     */
    Integer addOrderMeeting(Integer meetingroomid, Integer employeeid, String name, Date startTime,Date endtime,Integer peoplenum,String remark);

    /**
     * @MethodName approveMeeting
     * @param meeting
     * @param appoinmentstatus
     * @Description 审批会议
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/28
     */
    Integer approveMeeting(Integer meeting,Integer appoinmentstatus);

    /**
     * @MethodName updateOrderMeeting
     * @param meetingId
     * @param status
     * @param name
     * @param startTime
     * @param endtime
     * @param peoplenum
     * @Description 更新会议信息
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/28
     */
    Integer updateOrderMeeting(Integer meetingId,Integer status,String name, Date startTime,Date endtime,Integer peoplenum);

    /**
     * @MethodName waitingApproveMeeting
     * @param
     * @Description 获取所有待审核的预约会议
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Meeting>
     * @LastChangeDate 2020/11/28
     */
    List<Meeting> waitingApproveMeeting();

    /**
     * @MethodName judgeIsIsOccupy
     * @param meetingroomId
     * @param startTime
     * @param endTime
     * @Description 判断当前时间是否已经占用
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Meeting>
     * @LastChangeDate 2020/11/28
     */
    List<Meeting> judgeIsIsOccupy(Integer meetingroomId,Date startTime,Date endTime);


    List<Meeting> getAllMeetingTimeByRoomAndTime(Integer meetingrommid,Date stratTime,Date endTime);
}
