package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName LogMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/3 23:53
 * @Version v1.0
 */
@Mapper
public interface LogMapper {
    List<Log>getAllLogs();
}
