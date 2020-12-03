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
     * @param meetingId
     * @Description 按会议Id获取会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage getMeetingById(Integer meetingId);

    /**
     * @MethodName deleteMeeting
     * @param meetingId
     * @Description 删除会议
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    BackFrontMessage deleteMeeting(Integer meetingId);

    /**
     * @MethodName addOrderMeeting
     * @param meetingid
     * @param status
     * @param employeeid
     * @param name
     * @param startTime
     * @param endtime
     * @param peoplenum
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
     * @param meetingid
     * @param appoinmentstatus
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

    BackFrontMessage getCurrentAllMeeting();

}
