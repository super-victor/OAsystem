package com.sicnu.oasystem;

import com.sicnu.oasystem.mapper.*;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.Equipment;
import com.sicnu.oasystem.pojo.EquipmentClassify;
import com.sicnu.oasystem.pojo.MeetingRoom;
//import com.sicnu.oasystem.service.MeetingRoomService;
//import com.sicnu.oasystem.service.MeetingServicelmpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class OasystemApplicationTests {

//    @Resource
//    AddrListMapper addrListMapper;

    @Resource
    MeetingRoomMapper meetingRoomMapper;

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    EquipmentClassifyMapper equipmentClassifyMapper;

    @Resource
    EquipmentMapper equipmentMapper;

    @Resource
    MeetingMapper meetingMapper;

    @Test
    void contextLoads() {
        List<Employee>employees=employeeMapper.getAllEmployee();
//        List<Employee>employees=employeeMapper.getEmployeeBypage(1,1);
//        List<Employee>employees=employeeMapper.getEmployeeByCondition(null,"f");
        for (Employee e:employees){
            System.out.println(e);
        }
    }

    @Test
    void Meeting(){
//        for(MeetingRoom m:meetingRoomMapper.getAllMeetingRoom()){
//            System.out.println(m);
//        }
    }

    @Test
    void Equimpmentclassify(){
//        int res=equipmentClassifyMapper.EquipmentClassifyIfExitByName("显示器");
//        System.out.println(res);
        List<EquipmentClassify>equipmentClassifies=equipmentClassifyMapper.getAllEquipmentClassify();
        for(EquipmentClassify e:equipmentClassifies){
            System.out.println(e);
        }
    }


    @Test
    void Equipment(){
//        int res=equipmentMapper.addEquipment(1,1,1,"显示器1",0,10);
//        System.out.println(res);
//        int res=equipmentMapper.updateEquipment(1,1,null,"机器",null,-20);
//        System.out.println(res);

//        List<Equipment>equipments=equipmentMapper.getallEquipment();
//        for(Equipment e:equipments){
//            System.out.println(e);
//        }
          Equipment e=equipmentMapper.getEquipmentById(1);
          System.out.println(e);
    }

    @Test
    void meetingRoom(){
        MeetingRoom meetingRoom=meetingRoomMapper.getMeetingRoomByNameAndPlace(null,"");
        System.out.println(meetingRoom);
    }


    @Test
    void meetingtest(){
        //todo:主键，creatine，updatetime怎么隐藏
        System.out.println(meetingMapper.getAllMeetings());
    }

}
