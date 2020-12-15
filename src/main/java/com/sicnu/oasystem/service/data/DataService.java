package com.sicnu.oasystem.service.data;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName DataService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/12/14 23:54
 * @Version v1.0
 */

public interface DataService {

    /**
     * @MethodName getSystemData
     * @param
     * @Description 获取系统可视化数据
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/15
     */
    BackFrontMessage getSystemData();

    /**
     * @MethodName getSelfData
     * @param
     * @Description 获取个人可视化数据
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/15
     */
    BackFrontMessage getSelfData();
}
