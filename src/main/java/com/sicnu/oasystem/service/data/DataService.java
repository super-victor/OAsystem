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

    BackFrontMessage getSystemData();

    BackFrontMessage getSelfData();
}
