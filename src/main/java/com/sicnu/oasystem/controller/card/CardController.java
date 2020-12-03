package com.sicnu.oasystem.controller.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Card;
import com.sicnu.oasystem.service.card.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName CardController
 * @Description 名片控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:56
 * @Version v1.0
 */

@Api(tags = "名片管理")
@RestController
public class CardController {

    @Resource
    CardService cardService;

    /**
     * @MethodName insertCard
     * @param card 名片
     * @Description 直接手动添加名片
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "直接手动添加名片")
    @PostMapping("/Card")
    public BackFrontMessage insertCard(@Validated({Card.Add.class}) Card card){
        return cardService.insertCard(card, card.getCardHolderId());
    }

    /**
     * @MethodName updateCard
     * @param card 名片
     * @Description 修改名片相关内容
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "修改名片相关内容")
    @PutMapping("/Card")
    public BackFrontMessage updateCard(@Validated({Card.Update.class}) Card card){
        if (card.getCardId() == null)  return new BackFrontMessage(500,"名片id不能为空",null);
        return cardService.updateCard(card, card.getCardId());
    }

    /**
     * @MethodName deleteCard
     * @param cardId 名片id
     * @Description 通过名片id删除名片
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "通过名片id删除名片")
    @DeleteMapping("/Card")
    public BackFrontMessage deleteCard(@RequestParam int cardId){
        return cardService.deleteCard(cardId);
    }

    /**
     * @MethodName shareCard
     * @param cardId 名片id
     * @param cardHolderId 名片夹id
     * @Description 分享名片
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "分享名片")
    @PostMapping("/shareCard")
    public BackFrontMessage shareCard(@RequestParam int cardId, @RequestParam int cardHolderId){
        return cardService.shareCard(cardId, cardHolderId);
    }

    /**
     * @MethodName findCardByCardHolderId
     * @param cardHolderId 名片夹id
     * @Description 通过名片夹id查找名片
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "通过名片夹id查找名片")
    @GetMapping("/Card")
    public BackFrontMessage findCardByCardHolderId(@RequestParam int cardHolderId){
        return cardService.findCardByCardHolderId(cardHolderId);
    }


}
