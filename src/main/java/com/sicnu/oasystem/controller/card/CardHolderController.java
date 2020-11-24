package com.sicnu.oasystem.controller.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.CardHolder;
import com.sicnu.oasystem.service.card.CardHolderService;
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

/**
 * TODO:1.修改名片夹表，添加名片夹拥有者，分类。2.删除职工名片夹对应表。3.分享名片夹即复制一条数据(分类不能对应)
 */
@Api(tags = "名片管理")
@RestController
public class CardHolderController {

    @Resource
    CardHolderService cardHolderService;

    /**
     * @MethodName insertCardHolder
     * @param cardHolder 名片夹
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 直接手动添加名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "直接手动添加名片夹")
    @PostMapping("/CardHolder")
    public BackFrontMessage insertCardHolder(@RequestBody CardHolder cardHolder, @RequestParam Integer cardHolderClassfyId){
        return cardHolderService.insertCardHolder(cardHolder, cardHolderClassfyId);
    }

    /**
     * @MethodName updateCardHolderAboutCardHolderClassfy
     * @param cardHolder 名片夹
     * @param cardHolderId 名片夹id123
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


}
