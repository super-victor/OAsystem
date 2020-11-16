package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    DocumentService documentService;

    @ApiOperation("创建文档")
    @PostMapping("/document")
    public BackFrontMessage createDocument(@RequestParam Integer type, @RequestParam String title, @RequestParam String content, @RequestParam Integer sendPersonNum, @RequestParam String remark, @RequestParam int censorId, @RequestParam int isUrgent, @RequestParam MultipartFile file, @RequestParam List<Integer> receiveIdList) {
        return documentService.createDocument(type, title, content, sendPersonNum, remark, censorId, isUrgent, file, receiveIdList);
    }




}
