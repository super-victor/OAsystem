package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.CardHolderClassfyService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName CardHolderClassfyController
 * @Description 名片夹分类控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/6 16:58
 * @Version v1.0
 */

@RestController
public class CardHolderClassfyController {

    @Resource
    CardHolderClassfyService cardHolderClassfyService;

    /**
     * @MethodName findCardHolderClassfysByEmployeeId
     * @param
     * @Description 获取职工所拥有的名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    @GetMapping("/getCardHolderClassfys")
    public BackFrontMessage findCardHolderClassfysByEmployeeId(){
        return cardHolderClassfyService.findCardHolderClassfyServiceByEmployeeId();
    }
}
