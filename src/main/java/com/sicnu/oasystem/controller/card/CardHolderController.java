package com.sicnu.oasystem.controller.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.card.CardHolderService;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;


/**
 * @ClassName CardHolderController
 * @Description 名片夹控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/6 16:58
 * @Version v1.0
 */

@Api(tags = "名片夹管理")
@RestController
@Validated
public class CardHolderController {

    @Resource
    CardHolderService cardHolderService;

    /**
     * @MethodName findCardHolderByEmployeeId
     * @Description 获取职工所拥有的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    @ApiOperation(value = "获取职工所拥有的名片夹")
    @GetMapping("/CardHolder")
    public BackFrontMessage findCardHolderByEmployeeId(){
        return cardHolderService.findCardHolderByEmployeeId();
    }

    /**
     * @MethodName deleteCardHolderByCardHolderId
     * @param cardHolderId 名片夹id
     * @Description 通过名片夹id删除名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/7
     */
    @ApiOperation(value = "通过名片夹id删除名片夹")
    @DeleteMapping("/CardHolder")
    public BackFrontMessage deleteCardHolderByCardHolderId(
            @RequestParam int cardHolderId){
        return cardHolderService.deleteCardHolderByCardHolderId(cardHolderId);
    }

    /**
     * @MethodName insertCardHolder
     * @param name  名片夹名称
     * @Description 新增名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "增加名片夹")
    @PostMapping("/CardHolder")
    public BackFrontMessage insertCardHolder(@RequestParam @Validated @NotBlank(message = "名片夹名称不能为空") String name){
        return cardHolderService.insertCardHolder(name);
    }

    /**
     * @MethodName updateCardHolderName
     * @param cardHolderId  名片夹id
     * @param name 文件夹名称
     * @Description 修改名片夹名称
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "修改名片夹名称")
    @PutMapping("/CardHolder")
    public BackFrontMessage updateCardHolderName(@RequestParam int cardHolderId, @RequestParam @Validated @NotBlank(message = "名片夹名称不能为空") String name){
        return cardHolderService.updateCardHolderName(cardHolderId, name);
    }
}
