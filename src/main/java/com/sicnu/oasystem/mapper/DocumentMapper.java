package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.ReceiveFile;
import com.sicnu.oasystem.pojo.SendFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName DocumentMapper
 * @Description 收发文有关mapper
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 19:10
 * @Version v1.0
 */

@Mapper
public interface DocumentMapper {

    int insertSendFile(SendFile sendFile);

    int insertReceiveFile(ReceiveFile receiveFile);
}
