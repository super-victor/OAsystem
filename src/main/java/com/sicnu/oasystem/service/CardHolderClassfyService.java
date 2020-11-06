package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderClassfyMapper;
import com.sicnu.oasystem.pojo.CardHolderClassfy;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CardHolderClassfyService
 * @Description 名片夹分类服务
 * @Author Waynejwei
 * @LastChangeDate 2020/11/6 17:00
 * @Version v1.0
 */

@Service
public class CardHolderClassfyService {

    @Resource
    CardHolderClassfyMapper cardHolderClassfyMapper;

    /**
     * @MethodName findCardHolderClassfyServiceByEmployeeId
     * @param
     * @Description 获取某一职工的所用名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    public BackFrontMessage findCardHolderClassfyServiceByEmployeeId(){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        return new BackFrontMessage(200,"获取成功",cardHolderClassfyMapper.findCardHolderClassfysByEmployeeId(currentEmployee.getEmployeeId()));
    }
}
