package com.pgt.controller;

import com.chinaums.fsp.sdk.base.ResponseDataWrapper;
import com.pgt.PgtCollection.PgtCollection;
import com.pgt.config.PgtConfig;
import com.pgt.entity.DraftDealEntity;
import com.pgt.entity.DraftDealIdEntity;
import com.pgt.entity.OrganizationEntity;
import com.pgt.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: pgtsdk
 * @description: http调用票管通接口Controller
 * @author: LL
 * @create: 2018-03-22 14:04
 **/
@RestController
@RequestMapping(value = "/api/v1")
public class PgtController {
    @Autowired
    PgtCollection pgtCollection;
    @Autowired
    PgtConfig pgtConfig;
    /*
    * 机构注册
    * */
    @RequestMapping(value = "/pgt",method = RequestMethod.POST,consumes = "application/json")
    public ResultMessage register(@RequestBody OrganizationEntity organizationEntity){
        ResultMessage resultMessage = null;
        try {
            String url = pgtConfig.getUrl();
            System.out.println(url);
            ResponseDataWrapper rdw = pgtCollection.organizationRegRequest(organizationEntity);
            resultMessage = ResultMessage.sucess(rdw);
            System.out.println(resultMessage);
        } catch (Exception e) {
            resultMessage = ResultMessage.fail();
            e.printStackTrace();
        }
        return resultMessage;
    }

    /*
    * 机构注册状态查询
    * */
    @RequestMapping(value = "/pgt",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage organizationQuery(@RequestParam("uscc")String uscc,@RequestParam(value = "orgName",required = false,defaultValue = "")String orgName){
        ResultMessage resultMessage = null;
        try {
            ResponseDataWrapper rdw = pgtCollection.organizationQueryRequest(orgName,uscc);
            resultMessage = ResultMessage.sucess(rdw);
        } catch (Exception e) {
            resultMessage = ResultMessage.fail();
            e.printStackTrace();
        }
        return resultMessage;
    }

    /*
    * 票据交易
    * */
    @RequestMapping(value = "/pgt/{account_id}/transactions",method = RequestMethod.POST,consumes = "application/json")
    public ResultMessage draftDeal(@RequestBody DraftDealEntity draftDealEntity,@PathVariable("account_id")String account_id){
        ResultMessage resultMessage = null;
        try {
            ResponseDataWrapper rdw = pgtCollection.draftDealRequest(draftDealEntity);
            resultMessage = ResultMessage.sucess(rdw);
          /*  System.out.println(draftDealEntity);
            System.out.println(account_id);
            System.out.println("访问成功");*/
        } catch (Exception e) {
            resultMessage = ResultMessage.fail();
            e.printStackTrace();
        }
        return resultMessage;
    }

    /*
    * 汇票交易状态更新
    * idType 0：serverProcessId 1：clientTransId
    * orderStatus 0:待背书 1:待签收 2:待核验
    * */
    @RequestMapping(value = "/pgt/{account_id}/transactions/{trans_id}",method = RequestMethod.PUT,consumes = "application/json")
    public ResultMessage draftStatusModify(@RequestBody DraftDealIdEntity draftDealIdEntity,@PathVariable("account_id")String account_id,@PathVariable("trans_id")String trans_id){
        ResultMessage resultMessage = null;
        try {
            ResponseDataWrapper rdw = pgtCollection.draftStatusModify(trans_id,draftDealIdEntity.getOrderStatus());
            resultMessage = ResultMessage.sucess(rdw);
        } catch (Exception e) {
            resultMessage = ResultMessage.fail();
            e.printStackTrace();
        }
        return resultMessage;
    }

    /*
    * 汇票交易核验查询
    *
    * */
    @RequestMapping(value = "/pgt/{account_id}/transactions/{trans_id}",method = RequestMethod.GET)
    public ResultMessage draftEndorse(@PathVariable("account_id")String account_id,@PathVariable("trans_id")String trans_id){
        ResultMessage resultMessage = null;
        try {
            ResponseDataWrapper rdw = pgtCollection.draftEndorse(trans_id);
            resultMessage = ResultMessage.sucess(rdw);
        } catch (Exception e) {
            resultMessage = ResultMessage.fail();
            e.printStackTrace();
        }
        return resultMessage;
    }


    /*
    * 测试汇票订单关闭
    * idType 0：serverProcessId 1：clientTransId
    * */
    @RequestMapping(value = "/pgt/{account_id}/transactions/{trans_id}",method = RequestMethod.DELETE)
    public ResultMessage draftDealClose(@PathVariable("account_id")String account_id,@PathVariable("trans_id")String trans_id){
        ResultMessage resultMessage = null;
        try {
            ResponseDataWrapper rdw = pgtCollection.draftDealClose(trans_id);
            resultMessage = ResultMessage.sucess(rdw);
        } catch (Exception e) {
            resultMessage = ResultMessage.fail();
            e.printStackTrace();
        }
        return resultMessage;
    }

    /*
* 账户余额查询
*
* */
    @RequestMapping(value = "/pgt/account/{account_no}",method = RequestMethod.DELETE)
    public ResultMessage balanceQuery(@PathVariable("account_no")String account_no){
        ResultMessage resultMessage = null;
        try {
            ResponseDataWrapper rdw = pgtCollection.balanceQuery(account_no);
            resultMessage = ResultMessage.sucess(rdw);
        } catch (Exception e) {
            resultMessage = ResultMessage.fail();
            e.printStackTrace();
        }
        return resultMessage;
    }
}
