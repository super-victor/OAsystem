package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName DocumentController
 * @Description 收发文控制器
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 11:01
 * @Version v1.0
 */

@Api(tags = "发文和收文")
@RestController
public class DocumentController {

    @ApiOperation("")
    @PostMapping("/document")
    public BackFrontMessage createDocument(@RequestParam String type,@RequestParam String title,@RequestParam String content,@RequestParam String sendPersonNum,@RequestParam String remark,@RequestParam int censorId,@RequestParam int isUrgent,@RequestParam MultipartFile file) {
        return null;
    }


}
