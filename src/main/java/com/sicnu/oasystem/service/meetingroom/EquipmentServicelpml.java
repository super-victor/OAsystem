package com.sicnu.oasystem.service.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EquipmentClassifyMapper;
import com.sicnu.oasystem.mapper.EquipmentMapper;
import com.sicnu.oasystem.mapper.MeetingRoomMapper;
import com.sicnu.oasystem.pojo.Equipment;
import com.sicnu.oasystem.pojo.EquipmentClassify;
import com.sicnu.oasystem.pojo.MeetingRoom;
import com.sicnu.oasystem.service.meetingroom.EquipmentService;
import com.sicnu.oasystem.util.LogUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EquipmentServicelpml
 * @Description  设备服务的实现类
 * @Author pan
 * @LastChangeDate 2020/11/16 23:37
 * @Version v1.0
 */
@Service
public class EquipmentServicelpml implements EquipmentService {


    @Resource
    EquipmentMapper equipmentMapper;

    @Resource
    EquipmentClassifyMapper equipmentClassifyMapper;

    @Resource
    MeetingRoomMapper meetingRoomMapper;

    @Resource
    LogUtil logUtil;

    @Override
    public BackFrontMessage addEquipment(Integer equipmentclassifyId, Integer meetingroomId, String name, Integer ismaintain, String remark,int num) {
        if(equipmentclassifyId==null || equipmentclassifyId<=0 ||meetingroomId==null ||meetingroomId<=0 || name==null){
            return new BackFrontMessage(500,"添加设备失败",null);
        }
        EquipmentClassify equipmentClassify=equipmentClassifyMapper.getEquipmentClassifyById(equipmentclassifyId);
        MeetingRoom meetingRoom=meetingRoomMapper.getMeetingRoomById(meetingroomId);
        if(equipmentClassify !=null &&meetingRoom!=null){
            Equipment equipment=equipmentMapper.getEquipmentByclassifyAndRoomAndname(equipmentclassifyId,meetingroomId,name);
            if(equipment!=null){
                return new BackFrontMessage(500,"设备已存在",null);
            }
            int res=0;
            res=equipmentMapper.addEquipment(equipmentclassifyId,meetingroomId,name,ismaintain,remark,num);
            if(res==0){
                return new BackFrontMessage(500,"添加设备失败",null);
            }else{
                logUtil.insertInfo("添加设备"+"equipmentclassifyId:"+equipmentclassifyId+
                        ",meetingroomId:"+meetingroomId+",name:"+name+",ismaintain:"+ismaintain+
                        ",remark:"+remark+",num:"+num);
                return new BackFrontMessage(200,"添加设备成功",null);
            }
        }else{
            if(equipmentClassify==null){
                return new BackFrontMessage(500,"设备分类不存在",null);
            }
            if(meetingRoom==null){
                return new BackFrontMessage(500,"设备所在会议室不存在",null);
            }
            return new BackFrontMessage(500,"设备未添加",null);
        }
    }

    @Override
    public BackFrontMessage updateEquipment(Integer equipmentId, Integer equipmentclassifyId,
                                            Integer newmeetingroomId, String newname, Integer newismaintain, String remark,Integer newnum) {
        Equipment equipment=equipmentMapper.getEquipmentById(equipmentId);
        EquipmentClassify equipmentClassify=equipmentClassifyMapper.getEquipmentClassifyById(equipmentclassifyId);
        MeetingRoom meetingRoom=meetingRoomMapper.getMeetingRoomById(newmeetingroomId);
        if (equipment==null){
            return new BackFrontMessage(500,"更新的设备不存在",null);
        }else if((equipmentClassify==null&&equipmentclassifyId!=null) ||(meetingRoom==null&&newmeetingroomId!=null)){
            return new BackFrontMessage(500,"更新数据不正确",null);
        }
        else {
            int res=0;
            res=equipmentMapper.updateEquipment(equipmentId,equipmentclassifyId,newmeetingroomId,newname,newismaintain,remark,newnum);
            if(res==0){
                return new BackFrontMessage(500,"更新设备失败",null);
            }else {
                logUtil.updateInfo("修改设备成功"+equipment);
                return new BackFrontMessage(200,"更新设备成功",null);
            }
        }
    }

    @Override
    public BackFrontMessage getEquipmentById(Integer equipmentId) {
        Equipment equipment=equipmentMapper.getEquipmentById(equipmentId);
        if(equipment==null){
            return new BackFrontMessage(500,"设备不存在",null);
        }else {
            return new BackFrontMessage(200,"设备信息获取成功",equipment);
        }
    }

    @Override
    public BackFrontMessage getEquipmentByCondition(Integer meetingroomid, Integer equipmentclassifyId, String name) {
        List<Equipment> equipments=equipmentMapper.getEquipmentByCondition(meetingroomid,equipmentclassifyId,name);
        if (equipments==null){
            return new BackFrontMessage(500,"按条件查找设备失败",null);
        }else if(equipments.size()==0){
            return new BackFrontMessage(500,"按条件未查找到设备",null);
        }
        else {
            return new BackFrontMessage(200,"按条件查找设备成功",equipments);
        }
    }


    @Override
    public BackFrontMessage deleteEuipment(Integer equipmentId) {
        Equipment equipment=equipmentMapper.getEquipmentById(equipmentId);
        if (equipment==null){
            return new BackFrontMessage(500,"删除设备不存在",null);
        }else {
            int res=0;
            res=equipmentMapper.deleteEuipment(equipmentId);
            if(res==0){
                return new BackFrontMessage(500,"删除设备失败",null);
            }else{
                logUtil.deleteInfo("删除设备"+equipment);
                return new BackFrontMessage(200,"删除设备成功",null);
            }
        }
    }

    @Override
    public BackFrontMessage getallEquipment() {
        List<Equipment>equipmentList=equipmentMapper.getallEquipment();
        if(equipmentList.size()==0||equipmentList==null){
            return new BackFrontMessage(500,"获取所有设备失败",null);
        }else {
            return new BackFrontMessage(200,"获取所有设备成功",equipmentList);
        }
    }
}

