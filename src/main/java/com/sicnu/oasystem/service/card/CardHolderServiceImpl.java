package com.sicnu.oasystem.service.card;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderMapper;
import com.sicnu.oasystem.mapper.CardMapper;
import com.sicnu.oasystem.pojo.Card;
import com.sicnu.oasystem.pojo.CardHolder;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CardHolderServiceImpl
 * @Description 名片夹服务实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/6 17:00
 * @Version v1.0
 */

@Service
@Slf4j
public class CardHolderServiceImpl implements CardHolderService {

    @Resource
    CardHolderMapper cardHolderMapper;

    @Resource
    CardMapper cardMapper;

    @Override
    public BackFrontMessage findCardHolderByEmployeeId(){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        List<com.sicnu.oasystem.pojo.CardHolder> list = cardHolderMapper.findCardHolderByEmployeeId(currentEmployee.getEmployeeId());
        //将默认名片夹放在第一位
        int location = 0;
        for (com.sicnu.oasystem.pojo.CardHolder cardHolder : list) {
            if ("默认名片夹".equals(cardHolder.getName())){
                break;
            }
            location++;
        }
        com.sicnu.oasystem.pojo.CardHolder defaultCardHoler = list.get(location);
        com.sicnu.oasystem.pojo.CardHolder firstCardHolder = list.get(0);
        list.set(0, defaultCardHoler);
        list.set(location, firstCardHolder);
        return new BackFrontMessage(200,"获取成功",list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BackFrontMessage deleteCardHolderByCardHolderId(int cardHolderId) {
        if (isDefaultCardHolder(cardHolderId)){
            return new BackFrontMessage(500,"默认名片夹不可删除",null);
        }
        //获取“默认名片夹”的名片夹id
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        com.sicnu.oasystem.pojo.CardHolder defaultCardHolder = cardHolderMapper
                .findCardHolderByName("默认名片夹", currentEmployee.getEmployeeId());
        //转移前判断是否需要转移
        List<Card> hasCardHolder = cardMapper.findCardByCardHolderId(cardHolderId);
        boolean updateCard = false;   //默认不需要转移
        if ( hasCardHolder.size() != 0 ) {  //有需要转移的名片夹
            updateCard = true;
        }
        try{
            //删除前先将此名片夹下的名片转移到“默认名片夹”下
            int transferCount = cardMapper.updateOldCardHolderIdByNewCardHolderId(
                    cardHolderId, defaultCardHolder.getCardHolderId());
            if (transferCount > 0 || !updateCard){  //转移行数大于0，或者不需要转移
                int result = cardHolderMapper
                        .deleteCardHolderByCardHolderId(cardHolderId);
                if (result > 0){
                    return new BackFrontMessage(200,"删除成功,该名片夹下的名片已转移至‘默认名片夹‘下",null);
                } else {
                    throw new Exception("删除名片夹失败");
                }
            } else {
                throw new Exception("删除名片夹失败，名片转移失败");
            }
        } catch (Exception e){
            //事务未完成，则回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new BackFrontMessage(500,"删除名片夹失败",null);
        }
    }

    @Override
    public BackFrontMessage insertCardHolder(String name){
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        //查看是否有重名的文件夹分类
        if (hasSameName(currentEmployee, name)){
            return new BackFrontMessage(500,"添加失败，您已经此名字的名片夹分类!",null);
        }
        com.sicnu.oasystem.pojo.CardHolder cardHolder = new com.sicnu.oasystem.pojo.CardHolder();
        cardHolder.setName(name);
        cardHolder.setOwnerId(currentEmployee.getEmployeeId());
        int counter = cardHolderMapper
                .insertCardHolderByCardHolderId(cardHolder);
        if (counter > 0){
            return new BackFrontMessage(200,"添加成功", cardHolder.getCardHolderId());
        } else {
            return new BackFrontMessage(500,"添加失败",null);
        }
    }

    @Override
    public BackFrontMessage updateCardHolderName(int cardHolderId, String name){
        if (isDefaultCardHolder(cardHolderId)) {
            return new BackFrontMessage(500,"默认名片夹不可修改!",null);
        }
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        if(hasSameName(currentEmployee, name)){
            return new BackFrontMessage(500,"修改失败，您已经此名字的名片夹分类!",null);
        }
        int result = cardHolderMapper.updateCardHolderNameByCardHolderId(cardHolderId, name);
        if (result == 1){
            return new BackFrontMessage(200,"修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }

    /**
     * @MethodName hasSameName
     * @param name 名片夹分类名称
     * @Description 判断某一职工是否拥有此名字的名片夹分类
     * @Author Waynejwei
     * @Return boolean 拥有则返回true
     * @LastChangeDate 2020/11/8
     */
    private boolean hasSameName(Employee currentEmployee, String name){
        com.sicnu.oasystem.pojo.CardHolder cardHolderByName = cardHolderMapper.findCardHolderByName(name, currentEmployee.getEmployeeId());
        return cardHolderByName != null;
    }

    /**
     * @MethodName isDefaultCardHolder
     * @param cardHolderId 名片夹id
     * @Description 是否是默认名片夹
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/25
     */
    private boolean isDefaultCardHolder(int cardHolderId){
        CardHolder cardHolder = cardHolderMapper.findCardHolderByCardHolderId(cardHolderId);
        return "默认名片夹".equals(cardHolder.getName());
    }
}
