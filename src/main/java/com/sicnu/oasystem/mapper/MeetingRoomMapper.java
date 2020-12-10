package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.MeetingRoom;
import io.swagger.models.auth.In;
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

    /**
     * @MethodName getAllMeetingRoom
     * @param place 地点
     * @param isOccapy 是否被占用
     * @param MeetingRoomName 会议室名称
     * @Description 获取所有的会议室信息
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.MeetingRoom>
     * @LastChangeDate 2020/12/10
     */
    List<MeetingRoom> getAllMeetingRoom(String place, Integer isOccapy, String MeetingRoomName);

    /**
     * @MethodName getMeetingRoomNum
     * @param
     * @Description 获取会议室总数
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer getMeetingRoomNum();

    /**
     * @MethodName updateMeetingRoomIsOccapy
     * @param
     * @Description 修改会议室占用情况
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer updateMeetingRoomIsOccapy();

    /**
     * @MethodName updateMettingRoomNotIsOccapy
     * @param
     * @Description 修改会议室占用情况
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer updateMettingRoomNotIsOccapy();

    /**
     * @MethodName getMeetingRoomById
     * @param MeetingRoomId 会议室Id
     * @Description 通过会议室Id获取会议室信息
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.MeetingRoom
     * @LastChangeDate 2020/12/10
     */
    MeetingRoom getMeetingRoomById(Integer MeetingRoomId);

    /**
     * @MethodName addMeetingRoom
     * @param name 会议室民成功
     * @param place 地点
     * @param isOccapy 是否被占用
     * @param maxperson 最大容纳人数
     * @Description 添加会议室
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer addMeetingRoom(String name,String place,Integer isOccapy,Integer maxperson);

    /**
     * @MethodName updateMeetingRoom
     * @param MeetingRoomId 会议室Id
     * @param place 地点
     * @param isOccapy 是否被占用
     * @param MeetingRoomName 会议室名称
     * @param maxpserson 最大容纳人数
     * @Description 修改会议室信息
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer updateMeetingRoom(Integer MeetingRoomId,String place, Integer isOccapy, String MeetingRoomName,int maxpserson);

    /**
     * @MethodName deleteMeetingRoom
     * @param MeetingRoomId
     * @Description 添加描述
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    Integer deleteMeetingRoom(Integer MeetingRoomId);

    /**
     * @MethodName getMeetingRoomByNameAndPlace
     * @param name
     * @param place
     * @Description 添加描述
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.MeetingRoom
     * @LastChangeDate 2020/12/10
     */
    MeetingRoom getMeetingRoomByNameAndPlace(String name,String place);

    /**
     * @MethodName getAllStorey
     * @param
     * @Description 添加描述
     * @Author pan
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/12/10
     */
    List<String>getAllStorey();

    /**
     * @MethodName getMeetinRoomByStorey
     * @param place
     * @Description 添加描述
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.MeetingRoom>
     * @LastChangeDate 2020/12/10
     */
    List<MeetingRoom>getMeetinRoomByStorey(String place);
}
