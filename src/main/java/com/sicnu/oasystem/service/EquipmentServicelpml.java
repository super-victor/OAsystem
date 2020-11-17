package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EquipmentMapper;
import com.sicnu.oasystem.pojo.Equipment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EquipmentServicelpml
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/16 23:37
 * @Version v1.0
 */
@Service
public class EquipmentServicelpml implements EquipmentService{

    @Resource
    EquipmentMapper equipmentMapper;

    @Override
    public BackFrontMessage addEquipment(Integer equipmentId, Integer equipmentclassifyId,
                                         Integer meetingroomId, String name, Integer ismaintain, int num) {
        Equipment equipment=equipmentMapper.getEquipmentById(equipmentId);
        if(equipment==null){
            int res=0;
            res=equipmentMapper.addEquipment(equipmentId,equipmentclassifyId,meetingroomId,name,ismaintain,num);
            if(res==0){
                return new BackFrontMessage(500,"添加设备失败",null);
            }else{
                return new BackFrontMessage(200,"添加设备成功",null);
            }
        }else{
            return new BackFrontMessage(500,"设备已存在",null);
        }
    }

    @Override
    public BackFrontMessage updateEquipment(Integer equipmentId, Integer equipmentclassifyId,
                                            Integer newmeetingroomId, String newname, Integer newismaintain, int newnum) {
        Equipment equipment=equipmentMapper.getEquipmentById(equipmentId);
        if (equipment==null){
            return new BackFrontMessage(500,"更新的设备不存在",null);
        }else {
            int res=0;
            res=equipmentMapper.updateEquipment(equipmentId,equipmentclassifyId,newmeetingroomId,newname,newismaintain,newnum);
            if(res==0){
                return new BackFrontMessage(500,"更新设备失败",null);
            }else {
                return new BackFrontMessage(200,"更新设备成功",null);
            }
        }
    }

    @Override
    public BackFrontMessage deleteEuipment(Integer equipmentId) {
        Equipment equipment=equipmentMapper.getEquipmentById(equipmentId);
        if (equipment==null){
            return new BackFrontMessage(500,"删除设备失败",null);
        }else {
            return new BackFrontMessage(200,"获取设备成功",null);
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

