package com.sicnu.oasystem.service;

import com.sicnu.oasystem.mapper.MeetingRoomMapper;
import com.sicnu.oasystem.pojo.MeetingRoom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MeetingRoomServicelmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/13 14:44
 * @Version v1.0
 */
@Service
public class MeetingRoomServicelmpl implements MeetingRoomService{

    @Resource
    MeetingRoomMapper meetingRoomMapper;


    @Override
    public List<MeetingRoom> getMeetRoomInfo(String place, Integer isOccapy, String MeetingRoomName) {
        int m= this.updateMeetingRoomIsOccapy();
        System.out.println(m);
        this.uodateMeetingRoomNotIsOccapy();
        return meetingRoomMapper.getAllMeetingRoom(place,isOccapy,MeetingRoomName);
    }

    @Override
    public Integer updateMeetingRoomIsOccapy() {
        return meetingRoomMapper.updateMeetingRoomIsOccapy();
    }

    @Override
    public Integer uodateMeetingRoomNotIsOccapy() {
        return meetingRoomMapper.updateMettingRoomNotIsOccapy();
    }
}

