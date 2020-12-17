package com.sicnu.oasystem.controller.data;

import com.sicnu.oasystem.aop.Role;
import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.data.DataService;
import com.sicnu.oasystem.service.department.DepartmentService;
import com.sicnu.oasystem.service.document.DocumentService;
import com.sicnu.oasystem.util.DataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DataController
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/12/14 10:04
 * @Version v1.0
 */

@Api(tags = "数据可视化")
@RestController
public class DataController {
    @Resource
    DataService dataService;

    @Resource
    DepartmentService departmentService;

    @ApiOperation("系统总访问量和当天访问量")
    @Role(id = 1)
    @GetMapping("/data/systemViews")
    public BackFrontMessage getSystemViews(){
        Map<String,Object> map = new HashMap<>(2);
        map.put("totalViews", DataUtil.Data_Total_Views);
        map.put("dailyViews", DataUtil.Data_Daily_Views);
        map.put("department", departmentService.getDepartmentSeeAbleData());
        return new BackFrontMessage(200,"获取成功", map);
    }


    @ApiOperation("系统总的数据统计")
    @Role(id = 1)
    @GetMapping("/data/system")
    public BackFrontMessage getSystemData(){
        return dataService.getSystemData();
    }

    @ApiOperation("个人的数据统计")
    @GetMapping("/data/person")
    public BackFrontMessage getPersonData(){
        return dataService.getSelfData();
    }

}
