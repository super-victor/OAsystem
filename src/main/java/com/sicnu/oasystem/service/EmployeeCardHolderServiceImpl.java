package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderClassfyMapper;
import com.sicnu.oasystem.mapper.CardHolderMapper;
import com.sicnu.oasystem.mapper.EmployeeCardHolderMapper;
import com.sicnu.oasystem.pojo.CardHolder;
import com.sicnu.oasystem.pojo.CardHolderClassfy;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.EmployeeCardHolder;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EmployeeCardHolderServiceImpl
 * @Description 职工名片夹对应控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/14 16:16
 * @Version v1.0
 */

@Service
public class EmployeeCardHolderServiceImpl implements EmployeeCardHolderService{

    @Resource
    EmployeeCardHolderMapper employeeCardHolderMapper;

    @Resource
    CardHolderMapper cardHolderMapper;

    @Resource
    CardHolderClassfyMapper cardHolderClassfyMapper;

    @Override
    public BackFrontMessage insertEmployeeCardHolder(int cardHolderId, int cardHolderClassfyId) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        //添加之前检查是否已经拥有,如果拥有则返回该名片夹所在的分类名称
        EmployeeCardHolder employeeCardHolder = employeeCardHolderMapper
                .findEmployeeCardHolderByEmployeeIdAndCardHolderId(currentEmployee.getEmployeeId(), cardHolderId);
        if (employeeCardHolder != null){
            CardHolderClassfy cardHolderClassfy = cardHolderClassfyMapper.findCardHolderClassfyByCardHolderClassfyId(employeeCardHolder.getCardHolderClassfyId());
            return new BackFrontMessage(500,"您已拥有该名片夹,该名片夹所在分类为：",cardHolderClassfy.getName());
        }
        if (hasCardHolderClassfy(cardHolderClassfyId)){
            return new BackFrontMessage(500,"您没有此名片夹，不能添加！",null);
        }
        EmployeeCardHolder employeeCardHolder1 = new EmployeeCardHolder();
        employeeCardHolder1.setCardHolderId(cardHolderId);
        employeeCardHolder1.setEmployeeId(currentEmployee.getEmployeeId());
        employeeCardHolder1.setCardHolderClassfyId(cardHolderClassfyId);
        int result = employeeCardHolderMapper
                .insertEmployeeCardHolder(employeeCardHolder1);
        if (result <= 0){
            return new BackFrontMessage(500,"分享名片夹失败",null);
        }else{
            return new BackFrontMessage(200,"分享名片夹成功",employeeCardHolder1.getEmployeeCardHolderId());
        }
    }

    @Override
    public BackFrontMessage deleteEmployeeCardHolderByEmployeeCardHolderId(int employeeCardHolderId) {
        //先获取此职工名片夹对应的名片夹id
        EmployeeCardHolder employeeCardHolder = employeeCardHolderMapper
                .findEmployeeCardHolderByEmployeeCardHolderId(employeeCardHolderId);
        //删除职工名片夹表，删除时判断是否为本人
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        int result = employeeCardHolderMapper.
                deleteEmployeeCardHolderByEmployeeCardHolderIdAndEmployeeId(employeeCardHolderId, currentEmployee.getEmployeeId());
        if (result <= 0){
            return new BackFrontMessage(500,"该名片夹不是您本人的，无权删除！",null);
        }else{
            //判断此名片夹是否还有拥有者，没有则删除
            if (employeeCardHolder.getCardHolderId() != null){
                List<EmployeeCardHolder> list = employeeCardHolderMapper
                        .findEmployeeCardHolderByCardHolderId(employeeCardHolder.getCardHolderId());
                if (list.size() == 0){  //已无人拥有
                    int deleteCount = cardHolderMapper
                            .deleteCardHolderByCardHolderId(employeeCardHolder.getCardHolderId());
                    if (deleteCount <= 0){
                        return new BackFrontMessage(500,"已经无人拥有此名片夹，但是名片夹删除失败！",null);
                    }else{
                        return new BackFrontMessage(200,"删除成功",null);
                    }
                }else{  //还有人拥有
                    return new BackFrontMessage(200,"删除成功",null);
                }
            }else{
                return new BackFrontMessage(500,"名片夹id不存在！",null);
            }
        }
    }

    @Override
    public BackFrontMessage findEmployeeCardHolderByEmployeeId() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<EmployeeCardHolder> list = employeeCardHolderMapper
                .findEmployeeCardHolderByEmployeeId(currentEmployee.getEmployeeId());
        return new BackFrontMessage(200,"查找该用户所拥有名片夹成功",getCardHolderMapList(list));
    }

    @Override
    public BackFrontMessage findEmployeeCardHolderByCardHolderClassfyId(int cardHolderClassfyId) {
        List<EmployeeCardHolder> list = employeeCardHolderMapper
                .findEmployeeCardHolderByCardHolderClassfyId(cardHolderClassfyId);
        return new BackFrontMessage(200,"查找成功",getCardHolderMapList(list));
    }

    /**
     * @MethodName getCardHolderMapList
     * @param list
     * @Description 获取名片夹的封装list
     * @Author Waynejwei
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @LastChangeDate 2020/11/17
     */
    private List<Map<String, Object>> getCardHolderMapList(List<EmployeeCardHolder> list) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (EmployeeCardHolder employeeCardHolder : list) {
            Map<String,Object> map = new HashMap<>(16);
            CardHolder cardHolder = cardHolderMapper.findCardHolderByCardHolderId(employeeCardHolder.getCardHolderId());
            map.put("cardHolderId",cardHolder.getCardHolderId());
            map.put("cardHolder",cardHolder);
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public BackFrontMessage updateCardHolderClassfyIdBy(int employeeCardHolderId, int newCardHolderClassfyId) {
        if (hasCardHolderClassfy(newCardHolderClassfyId)){
            return new BackFrontMessage(500,"您没有此名片夹，不能添加！",null);
        }
        int result = employeeCardHolderMapper
                .updateEmployeeCardHolderClassfyByEmployeeCardHolderId(employeeCardHolderId, newCardHolderClassfyId);
        if (result <= 0){
            return new BackFrontMessage(500,"修改失败",null);
        }else{
            return new BackFrontMessage(200,"修改成功",null);
        }
    }

    /**
     * @MethodName hasCardHolderClassfy
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 判断职工是否拥有名片夹分类，若没有则返回true
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/16
     */
    private boolean hasCardHolderClassfy(Integer cardHolderClassfyId) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        CardHolderClassfy cardHolderClassfy = cardHolderClassfyMapper.findCardHolderClassfyByCardHolderClassfyIdAndEmployeeId(
                cardHolderClassfyId, currentEmployee.getEmployeeId());
        return cardHolderClassfy == null;
    }
}
