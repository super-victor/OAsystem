package com.sicnu.oasystem.service.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Meeting;

import java.util.Date;
import java.util.List;

/**
 * @InterfaceName MeetingService
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/24 23:26
 * @Version v1.0
 */

public interface MeetingService {

    /**
     * @MethodName getAllMeetings
     * @param
     * @Description 获取所有会议信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage getAllMeetings();

    /**
     * @MethodName getMeetingById
     * @param meetingId 会议Id
     * @Description 按会议Id获取会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage getMeetingById(Integer meetingId);

    /**
     * @MethodName deleteMeeting
     * @param meetingId 会议Id
     * @Description 删除会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage deleteMeeting(Integer meetingId);

    /**
     * @MethodName addOrderMeeting
     * @param meetingid 会议室Id
     * @param employeeid 员工Id
     * @param name 会议名称
     * @param startTime 开始时间
     * @param endtime 结束时间
     * @param peoplenum 会议人数
     * @Description 添加预约会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage addOrderMeeting(Integer meetingid, Integer employeeid,
                                     String name, Date startTime, Date endtime,
                                     Integer peoplenum,String remark);

    /**
     * @MethodName approveMeeting
     * @param meetingid 会议Id
     * @param appoinmentstatus 会议预约状态
     * @Description 审批会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage approveMeeting(Integer meetingid,Integer appoinmentstatus);

    /**
     * @MethodName getNotApprovedMeeting
     * @param
     * @Description 获取未审批的会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/28
     */
    BackFrontMessage getNotApprovedMeeting();

    /**
     * @MethodName getCurrentAllMeeting
     * @param
     * @Description获取当前所有的会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage getCurrentAllMeeting();

    /**
     * @MethodName getAllMeetingTimeByRoomAndTime
     * @param meetingroomid 会议室Id
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @Description 按会议室或开始结束时间获取会议信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage getAllMeetingTimeByRoomAndTime(Integer meetingroomid,Date starttime,Date endtime);

    /**
     * @MethodName cancleApproveMeeting
     * @param meetingid 会议Id
     * @param employeeid 员工Id
     * @Description 取消预约成功但是未审核的会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage cancleApproveMeeting(Integer meetingid,Integer employeeid);

    /**
     * @MethodName updateOrderMeeting
     * @param meetingid 会议Id
     * @param meetingroomid 会议室Id
     * @param employeeid 员工Id
     * @param name 会议名称
     * @param startTime 开始时间
     * @param endtime 结束时间
     * @param peoplenum 会议人数
     * @param remark 会议备注
     * @Description 修改预约成功但是未审批的会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage updateOrderMeeting(Integer meetingid,Integer meetingroomid, Integer employeeid,
                       String name, Date startTime,Date endtime,
                       Integer peoplenum,String remark);
}
