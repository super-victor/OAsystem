package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EquipmentClassifyMapper;
import com.sicnu.oasystem.pojo.EquipmentClassify;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EquipmentClassifyServicelmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/16 11:07
 * @Version v1.0
 */
@Service
public class EquipmentClassifyServicelmpl implements EquipmentClassifyService{

    @Resource
    EquipmentClassifyMapper equipmentClassifyMapper;


    @Override
    public BackFrontMessage addEquipmentClassify(String classifyName) {
        Integer res=0;
        res=equipmentClassifyMapper.EquipmentClassifyIfExitByName(classifyName);
        if(res==1){
            return new BackFrontMessage(500,"设备分类已存在",null);
        }else {
            res=equipmentClassifyMapper.addEquipmentClassify(classifyName);
            if(res==1){
                return new BackFrontMessage(200,"添加设备分类成功",null);
            }else {
                return new BackFrontMessage(500,"添加设备分类失败",null);
            }
        }
    }

    @Override
    public BackFrontMessage updateEquipmentClassify(Integer classifyId, String newClassifyName) {
        EquipmentClassify equipmentClassify=equipmentClassifyMapper.getEquipmentClassifyById(classifyId);
        if(equipmentClassify==null){
            return new BackFrontMessage(500,"更新的设备分类不存在",null);
        }else{
            int res=0;
            res=equipmentClassifyMapper.updateEquipmentClassify(classifyId,newClassifyName);
            if(res==0){
                return new BackFrontMessage(500,"更新设备分类失败",null);
            }else{
                return new BackFrontMessage(200,"更新设备分类成功",null);
            }
        }
    }


    @Override
    public BackFrontMessage deleteEquipmentClassify(Integer ClassifyId) {
        EquipmentClassify equipmentClassify=equipmentClassifyMapper.getEquipmentClassifyById(ClassifyId);
        if(equipmentClassify==null){
            return new BackFrontMessage(500,"删除的设备分类不存在",null);
        }else{
            int res=0;
            res=equipmentClassifyMapper.deleteEquipmentClassify(ClassifyId);
            if(res==0){
                return new BackFrontMessage(500,"删除设备分类失败",null);
            }else {
                return new BackFrontMessage(200,"删除设备分类成功",null);
            }
        }
    }

    @Override
    public BackFrontMessage getAllEquipmentClassify() {
        List<EquipmentClassify>equipmentClassifies=equipmentClassifyMapper.getAllEquipmentClassify();
        if(equipmentClassifies==null ||equipmentClassifies.size()==0){
            return new BackFrontMessage(500,"获取全部设备分类失败",null);
        }else{
            return new BackFrontMessage(200,"获取全部设备分类成功",equipmentClassifies);
        }
    }
}

