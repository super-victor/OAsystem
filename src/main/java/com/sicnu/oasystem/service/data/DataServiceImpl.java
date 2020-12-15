package com.sicnu.oasystem.service.data;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.department.DepartmentService;
import com.sicnu.oasystem.service.document.DocumentService;
import com.sicnu.oasystem.service.meetingroom.MeetingRoomService;
import com.sicnu.oasystem.service.meetingroom.MeetingRoomServicelmpl;
import com.sicnu.oasystem.service.meetingroom.MeetingService;
import com.sicnu.oasystem.service.schedule.ScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DataServiceImpl
 * @Description 数据服务
 * @Author JohnTang
 * @LastChangeDate 2020/12/14 23:55
 * @Version v1.0
 */

@Service
public class DataServiceImpl implements DataService{
    @Resource
    DocumentService documentService;

    @Resource
    ScheduleService scheduleService;

    @Resource
    MeetingService meetingService;

    @Resource
    DepartmentService departmentService;

    @Override
    public BackFrontMessage getSystemData() {
        Map<String,Object> map = new HashMap<>(16);
        map.put("document",documentService.getSystemDocumentSeeAbleData());
        map.put("schedule",scheduleService.findCompanyScheduleNumber());
        map.put("meeting",meetingService.MeetingTrendInfo());
        map.put("department",departmentService.getDepartmentSeeAbleData());
        return new BackFrontMessage(200, "获取成功", map);
    }

    @Override
    public BackFrontMessage getSelfData() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("document",documentService.getSelfDocumentSeeAbleData());
        return new BackFrontMessage(200, "获取成功",map);
    }
}
