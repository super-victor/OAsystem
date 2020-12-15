package com.sicnu.oasystem.controller.message;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.message.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MessageController
 * @Description 消息控制器
 * @Author JohnTang
 * @LastChangeDate 2020/12/7 23:12
 * @Version v1.0
 */

@RestController
@Api(tags = "消息")
public class MessageController {
    @Resource
    MessageService messageService;

    /**
     * @MethodName getSelfAllMessage
     * @param
     * @Description 获取自己的所有消息
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation("获取自己的所有消息")
    @GetMapping("/message")
    public BackFrontMessage getSelfAllMessage(){
        return messageService.getSelfAllMessage();
    }

    /**
     * @MethodName getSelfAllUnReadMessage
     * @param
     * @Description 获取自己的所有未读消息
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation("获取自己的所有未读消息")
    @GetMapping("/messageNotRead")
    public BackFrontMessage getSelfAllUnReadMessage(){
        return messageService.getSelfAllUnReadMessage();
    }

    /**
     * @MethodName readMessage
     * @param messageId
     * @Description 读消息
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation("读消息")
    @PutMapping("/readMessage")
    public BackFrontMessage readMessage(@RequestParam int messageId){
        return messageService.setSelfMessageRead(messageId);
    }

}
