package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName MessageMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/25 21:42
 * @Version v1.0
 */
@Mapper
public interface MessageMapper {

    int insertMessage(Message message);
}
