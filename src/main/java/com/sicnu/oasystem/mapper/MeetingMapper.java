package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Meeting;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName MeetingMapper
 * @Description 会议的Mapper
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

    /**
     * @MethodName completeMeetings
     * @param
     * @Description 将完成的会议更改状态
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer completeMeetings();

    /**
     * @MethodName getCurrentAllMeeting
     * @param
     * @Description 获取当前所以会议
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Meeting>
     * @LastChangeDate 2020/12/10
     */
    List<Meeting>getCurrentAllMeeting();

    /**
     * @MethodName getMeetingById
     * @param meetingId 会议Id
     * @Description 通过会议Id获得会议信息
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.Meeting
     * @LastChangeDate 2020/11/28
     */
    Meeting getMeetingById(Integer meetingId);

    /**
     * @MethodName deleteMeeting
     * @param meetingId 会议ID
     * @Description 通过删除会议
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/28
     */
    Integer deleteMeeting(Integer meetingId);

    /**
     * @MethodName addOrderMeeting
     * @param meetingroomid 会议室Id
     * @param employeeid 员工Id
     * @param name 会议名称
     * @param startTime 开始时间
     * @param endtime 结束时间
     * @param peoplenum 会议人数
     * @param remark 备注
     * @Description 预约会议
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/28
     */
    Integer addOrderMeeting(Integer meetingroomid, Integer employeeid, String name, Date startTime,Date endtime,Integer peoplenum,String remark);

    /**
     * @MethodName approveMeeting
     * @param meeting 会议Id
     * @param appoinmentstatus 会议预约状态
     * @Description 审批会议
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/28
     */
    Integer approveMeeting(Integer meeting,Integer appoinmentstatus);

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
     * @Description 更新会议信息
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer updateOrderMeeting(Integer meetingid,Integer meetingroomid, Integer employeeid,
                               String name, Date startTime,Date endtime,
                               Integer peoplenum,String remark);

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
     * @param meetingroomId 会议室Id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @Description 判断当前时间是否已经占用
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Meeting>
     * @LastChangeDate 2020/11/28
     */
    List<Meeting> judgeIsIsOccupy(Integer meetingroomId,Date startTime,Date endTime);

    /**
     * @MethodName getAllMeetingTimeByRoomAndTime
     * @param meetingrommid 会议室Id
     * @param stratTime 开始时间
     * @param endTime  结束时间
     * @Description 通过会议室和时间查找会议
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Meeting>
     * @LastChangeDate 2020/12/10
     */
    List<Meeting> getAllMeetingTimeByRoomAndTime(Integer meetingrommid,Date stratTime,Date endTime);

    /**
     * @MethodName cancleApproveMeeting
     * @param meetingid 会议Id
     * @param employeeid 员工Id
     * @Description 取消已经预约但未审批的会议
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer cancleApproveMeeting(Integer meetingid,Integer employeeid);



    List<Map<String,Object>>getMeetingTrend(String departmentName);

    Integer getMeetingTrendnums(String departmentName);
}
