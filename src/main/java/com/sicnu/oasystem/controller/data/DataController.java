package com.sicnu.oasystem.controller.data;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.util.DataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("系统总访问量和当天访问量")
    @GetMapping("/systemViews")
    public BackFrontMessage getSystemViews(){
        Map<String,Integer> map = new HashMap<>(2);
        map.put("totalViews", DataUtil.Data_Total_Views);
        map.put("dailyViews", DataUtil.Data_Daily_Views);
        return new BackFrontMessage(200,"获取成功", map);
    }


}
