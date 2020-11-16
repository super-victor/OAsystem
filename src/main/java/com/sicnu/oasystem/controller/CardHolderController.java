package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.CardHolder;
import com.sicnu.oasystem.service.CardHolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName CardHolderController
 * @Description 名片夹控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:56
 * @Version v1.0
 */

@Api(tags = "名片夹管理")
@RestController
public class CardHolderController {

    @Resource
    CardHolderService cardHolderService;

    /**
     * @MethodName insertCardHolder
     * @param cardHolder 名片夹
     * @Description 新增名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "添加名片夹")
//    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", dataTypeClass = String.class, name = "token", value = "token标记", required = true) })
    @PostMapping("/CardHolder")
    public BackFrontMessage insertCardHolder(@RequestBody CardHolder cardHolder){
        return cardHolderService.insertCardHolder(cardHolder);
    }

    /**
     * @MethodName updateCardHolderAboutCardHolderClassfy
     * @param cardHolder 名片夹
     * @param cardHolderId 名片夹id
     * @Description 修改名片夹所在分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "修改名片夹相关内容")
    @PutMapping("/CardHolder")
    public BackFrontMessage updateCardHolderAboutClassfy(@RequestBody CardHolder cardHolder, @RequestParam int cardHolderId){
        return cardHolderService.updateCardHolder(cardHolder, cardHolderId);
    }

    /**
     * @MethodName deleteCardHolder
     * @param cardHolderId 名片夹id
     * @Description 删除名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "删除名片夹")
    @DeleteMapping("/CardHolder")
    public BackFrontMessage deleteCardHolder(@RequestParam int cardHolderId){
        return cardHolderService.deleteCardHolder(cardHolderId);
    }

    /**
     * @MethodName findCardHolderByCardHolderClassfyId
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 获取某一分类下的所有名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "获取某一分类下的所有名片夹")
    @GetMapping("/CardHolderByCardHolderClassfyId")
    public BackFrontMessage findCardHolderByCardHolderClassfyId(@RequestParam int cardHolderClassfyId){
        return cardHolderService.findCardHolderByCardHolderClassfyId(cardHolderClassfyId);
    }

}
