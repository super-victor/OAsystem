package com.sicnu.oasystem.service.message;


import com.sicnu.oasystem.mapper.MessageMapper;
import com.sicnu.oasystem.pojo.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName MessageServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/25 22:03
 * @Version v1.0
 */

@Service
public class MessageServiceImpl implements MessageService{

    @Resource
    MessageMapper messageMapper;

    @Override
    public int send(int employeeId, int type, String title, String content) {
        Message message = new Message();
        message.setEmployeeId(employeeId);
        message.setTitle(title);
        message.setType(type);
        message.setContent(content);
        return messageMapper.insertMessage(message);
    }
}
