package com.sicnu.oasystem.controller.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Card;
import com.sicnu.oasystem.service.card.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName CardController
 * @Description 名片控制类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/8 14:56
 * @Version v1.0
 */

/**
 * TODO:名片对应
 * 1.修改名片夹表，添加名片夹拥有者，分类。
 * 2.删除职工名片夹对应表。
 * 3.分享名片夹即复制一条数据(分类不能对应)。
 * 4.一个用户不能拥有另一个用户多个名片夹(也即是(employeeId和ownerId)不能重复)
 * 5.名片夹类名CardHolder、名片类名Card
 */
@Api(tags = "名片管理")
@RestController
public class CardController {

    @Resource
    CardService cardService;

    /**
     * @MethodName insertCard
     * @param card 名片
     * @param cardHolderId 名片夹id
     * @Description 直接手动添加名片
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "直接手动添加名片")
    @PostMapping("/Card")
    public BackFrontMessage insertCard(@RequestBody Card card, @RequestParam Integer cardHolderId){
        if (card.getOwnerId() == null || card.getName() == null ||
                card.getPhone() == null || card.getAddress() == null ||
                card.getCompany() == null){
            return new BackFrontMessage(500,"请将数据填写完整！",null);
        }
        return cardService.insertCard(card, cardHolderId);
    }

    /**
     * @MethodName updateCard
     * @param card 名片
     * @param cardId 名片id
     * @Description 修改名片相关内容
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "修改名片相关内容")
    @PutMapping("/Card")
    public BackFrontMessage updateCard(@RequestBody Card card, @RequestParam int cardId){
        return cardService.updateCard(card, cardId);
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
