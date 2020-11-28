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

    List<Meeting>getAllMeetings();

    Meeting getMeetingById(Integer meetingId);

    Integer deleteMeeting(Integer meetingId);

    Integer addOrderMeeting(Integer meetingroomid, Integer status, Integer employeeid, String name, Date startTime,Date endtime,Integer peoplenum);

    Integer approveMeeting(Integer meeting,Integer appoinmentstatus);

    Integer updateOrderMeeting(Integer meetingId,Integer status,String name, Date startTime,Date endtime,Integer peoplenum);
}
