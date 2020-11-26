package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.MeetingRoomMapper;
import com.sicnu.oasystem.pojo.MeetingRoom;
import org.springframework.security.core.parameters.P;
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
    public BackFrontMessage getMeetRoomInfo(String place, Integer isOccapy, String MeetingRoomName) {
        this.updateMeetingRoomIsOccapy();
        this.uodateMeetingRoomNotIsOccapy();
        List<MeetingRoom>meetingRooms=meetingRoomMapper.getAllMeetingRoom(place,isOccapy,MeetingRoomName);
        if(meetingRooms==null){
            return new BackFrontMessage(500,"获取所有会议室信息失败",null);
        }else {
            return new BackFrontMessage(200,"获取所有会议室信息成功",meetingRooms);
        }
    }

    @Override
    public Integer updateMeetingRoomIsOccapy() {
        return meetingRoomMapper.updateMeetingRoomIsOccapy();
    }

    @Override
    public Integer uodateMeetingRoomNotIsOccapy() {
        return meetingRoomMapper.updateMettingRoomNotIsOccapy();
    }

    @Override
    public BackFrontMessage getMeetingRoomById(Integer meetingRoomId) {
        MeetingRoom meetingRoom=meetingRoomMapper.getMeetingRoomById(meetingRoomId);
        if (meetingRoom==null){
            return new BackFrontMessage(500,"获取会议室失败",null);
        }else {
            return new BackFrontMessage(200,"获取会议室成功",meetingRoom);
        }
    }

    @Override
    public BackFrontMessage updateMeetingRoom(Integer MeetingRoomId, String place, Integer isOccapy, String MeetingRoomName, int maxpserson) {
        MeetingRoom meetingRoom=meetingRoomMapper.getMeetingRoomById(MeetingRoomId);
        if(meetingRoom==null){
            return new BackFrontMessage(500,"更新的会议室不存在",null);
        }else {
            int res=0;
            res=meetingRoomMapper.updateMeetingRoom(MeetingRoomId,place,isOccapy,MeetingRoomName,maxpserson);
            if(res==0){
                return new BackFrontMessage(500,"更新会议室失败",null);
            }else {
                return new BackFrontMessage(200,"更新会议室成功",null);
            }
        }
    }

    @Override
    public BackFrontMessage deleteMeetingRoom(Integer MeetingRoomId) {
        MeetingRoom meetingRoom=meetingRoomMapper.getMeetingRoomById(MeetingRoomId);
        if(meetingRoom==null){
            return new BackFrontMessage(500,"删除的会议室不存在",null);
        }else {
            int res=0;
            res=meetingRoomMapper.deleteMeetingRoom(MeetingRoomId);
            if (res==0){
                return new BackFrontMessage(500,"删除会议室失败",null);
            }else {
                return new BackFrontMessage(200,"删除会议室成功",null);
            }
        }
    }

    @Override
    public BackFrontMessage addMeetingRoom(String name, String place, Integer isOccapy, Integer maxperson) {
        if(place==null){
            return new BackFrontMessage(500,"添加会议室条件不足",null);
        }
        MeetingRoom meetingRoom=meetingRoomMapper.getMeetingRoomByNameAndPlace(name,place);
        if(meetingRoom!=null){
            return new BackFrontMessage(500,"会议室已存在",null);
        }else {
            int res=0;
            res=meetingRoomMapper.addMeetingRoom(name,place,isOccapy,maxperson);
            if(res==0){
                return new BackFrontMessage(500,"添加会议室失败",null);
            }else {
                return new BackFrontMessage(200,"添加会议室成功",null);
            }
        }
    }
}

