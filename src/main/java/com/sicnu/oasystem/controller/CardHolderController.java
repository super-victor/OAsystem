package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.CardHolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
     * @param ownedId
     * @param cardHolderClassfyId
     * @Description 新增名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "添加名片夹")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", dataTypeClass = String.class, name = "token", value = "token标记", required = true) })
    @PostMapping("/CardHolder")
    public BackFrontMessage insertCardHolder(@RequestParam int ownedId, @RequestParam int cardHolderClassfyId){
        return cardHolderService.insertCardHolder(ownedId, cardHolderClassfyId);
    }

    /**
     * @MethodName updateCardHolderAboutCardHolderClassfy
     * @param cardHolderId
     * @param cardHolderClassfyId
     * @Description 修改名片夹所在分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "修改名片夹的分类")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", dataTypeClass = String.class, name = "token", value = "token标记", required = true) })
    @PutMapping("/CardHolder")
    public BackFrontMessage updateCardHolderAboutClassfy(@RequestParam int cardHolderId, @RequestParam int cardHolderClassfyId){
        return cardHolderService.updateCardHolderAboutClassfy(cardHolderId,cardHolderClassfyId);
    }

    /**
     * @MethodName deleteCardHolder
     * @param cardHolderId
     * @Description 删除名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "删除名片夹")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", dataTypeClass = String.class, name = "token", value = "token标记", required = true) })
    @DeleteMapping("/CardHolder")
    public BackFrontMessage deleteCardHolder(@RequestParam int cardHolderId){
        return cardHolderService.deleteCardHolder(cardHolderId);
    }

    /**
     * @MethodName findCardHolderByOwnerId
     * @param
     * @Description 获取职工他人所有的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "获取职工所有的名片夹")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @GetMapping("/CardHolderByOwnerId")
    public BackFrontMessage findCardHolderByOwnerId(){
        return cardHolderService.findCardHolderByOwnerId();
    }

    /**
     * @MethodName findCardHolderByOwnedId
     * @param ownedId
     * @Description 获取某一个名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "获取某一职工的名片夹")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @GetMapping("/CardHolderByOwnedId")
    public BackFrontMessage findCardHolderByOwnedId(@RequestParam int ownedId){
        return cardHolderService.findCardHolderByOwnedId(ownedId);
    }

    /**
     * @MethodName findCardHolderByCardHolderClassfyId
     * @param cardHolderClassfyId
     * @Description 获取某一分类下的所有名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "获取某一分类下的所有名片夹")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @GetMapping("/CardHolderByCardHolderClassfyId")
    public BackFrontMessage findCardHolderByCardHolderClassfyId(@RequestParam int cardHolderClassfyId){
        return cardHolderService.findCardHolderByCardHolderClassfyId(cardHolderClassfyId);
    }

    /**
     * @MethodName findSelfCardHolder
     * @param
     * @Description 获取职工个人的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "获取职工个人的名片夹")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @GetMapping("/SelfCardHolder")
    public BackFrontMessage findSelfCardHolder(){
        return cardHolderService.findSelfCardHolder();
    }
}
