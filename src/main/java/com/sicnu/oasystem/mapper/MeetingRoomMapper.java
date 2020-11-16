package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.MeetingRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName MeetingRoomMapper
 * @Description 操作meetingroomdmapper
 * @Author pan
 * @LastChangeDate 2020/11/13 14:53
 * @Version v1.0
 */
@Mapper
public interface MeetingRoomMapper {

    List<MeetingRoom> getAllMeetingRoom(String place, Integer isOccapy, String MeetingRoomName);

    Integer getMeetingRoomNum();

    Integer updateMeetingRoomIsOccapy();

    Integer updateMettingRoomNotIsOccapy();
}
