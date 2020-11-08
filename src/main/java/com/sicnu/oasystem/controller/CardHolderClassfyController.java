package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.CardHolderClassfyService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName CardHolderClassfyController
 * @Description 名片夹分类控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/6 16:58
 * @Version v1.0
 */

@Api(tags = "名片夹分类管理")
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
    @ApiOperation(value = "获取职工所拥有的名片夹分类")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @GetMapping("/CardHolderClassfy")
    public BackFrontMessage findCardHolderClassfysByEmployeeId(){
        return cardHolderClassfyService.findCardHolderClassfyByEmployeeId();
    }

    /**
     * @MethodName deleteCardHolderClassfyByCardHolderClassfyId
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 通过名片夹分类id删除名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/7
     */
    @ApiOperation(value = "通过名片夹分类id删除名片夹分类")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @DeleteMapping("/CardHolderClassfy")
    public BackFrontMessage deleteCardHolderClassfyByCardHolderClassfyId(@RequestParam int cardHolderClassfyId){
        return cardHolderClassfyService.deleteCardHolderClassfyByCardHolderClassfyId(cardHolderClassfyId);
    }

    /**
     * @MethodName insertCardHolderClassfy
     * @param name  名片夹名称
     * @Description 新增名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "增加名片夹分类")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @PostMapping("/CardHolderClassfy")
    public BackFrontMessage insertCardHolderClassfy(@RequestParam String name){
        return cardHolderClassfyService.insertCardHolderClassfy(name);
    }

    /**
     * @MethodName updateCardHolderClassfyName
     * @param cardHolderClassfyId  名片夹分类id
     * @param name 修改名片夹分类名称
     * @Description
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "修改名片夹分类名称")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @PutMapping("/CardHolderClassfy")
    public BackFrontMessage updateCardHolderClassfyName(@RequestParam int cardHolderClassfyId, @RequestParam String name){
        return cardHolderClassfyService.updateCardHolderClassfyName(cardHolderClassfyId, name);
    }
}
