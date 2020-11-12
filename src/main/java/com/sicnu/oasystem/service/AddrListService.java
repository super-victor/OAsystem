package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @ClassName AddrListServer
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/8 17:24
 * @Version v1.0
 */

public interface AddrListService {

    BackFrontMessage getAllEmplyeAddr();

    BackFrontMessage getDeparmentName();

//    BackFrontMessage getDeparmentname();

    BackFrontMessage getEmployeeBypage(Integer currentPageNum,Integer pageSize);

    BackFrontMessage getEmployeeByCondition(String name,String sex);
}

