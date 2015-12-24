package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.entity.SysNotice;
import me.daoke.renrenfm.entity.WithdrawCash;
import me.daoke.renrenfm.service.IFinanceService;
import me.daoke.renrenfm.vo.PresentUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 财务controller
 * Created by zhaoym on 2015/9/9.
 */
@Controller
@RequestMapping(value="/finance")
public class FinanceController {

    /***钻和人民币的换算比例*/
    @Value("#{apiConfig[diamondsRMBRate]}")
    private String diamondsRMBRate;

    @Autowired
    private IFinanceService financeSrevice;

    /**
     * 提现查询首次请求
     * @return
     */
    @RequestMapping(value = "/withdrawalsQueryIndex")
    public ModelAndView withdrawalsQueryIndex() {
        return new ModelAndView("finance/withdrawalsQuery");
    }

    /**
     * 提现操作首次请求
     * @return
     */
    @RequestMapping(value = "/withdrawalsOperationIndex")
    public ModelAndView withdrawalsOperationIndex() {
        return new ModelAndView("finance/withdrawalsOperation");
    }

    /**
     * 返回密点数首次请求
     * @return
     */
    @RequestMapping(value = "/denseCoinsIndex")
    public ModelAndView denseCoinsIndex() {
        return new ModelAndView("coins/d");
    }



    /**
     * 查询提现记录信息
     * @param request
     * @param response
     * @param rows
     * @param page
     * @param userName 用户名
     * @param receiptNumber 回执单号
     * @param alipayAccount 支付宝账号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWithdrawCashList")
    public String getWithdrawCashList(HttpServletRequest request, HttpServletResponse response, @RequestParam int rows, @RequestParam int page,
            @RequestParam(value = "userName", required = false) String userName,@RequestParam(value = "receiptNumber", required = false) String receiptNumber,
            @RequestParam(value = "alipayAccount", required = false) String alipayAccount) {
        String str = "";
        try {
            JqgridPageList<Map<String, Object>> pageList = new JqgridPageList<Map<String, Object>>();
            if (page+"" == null || page==0 ) {
                page = 1;
            }
            if (rows+"" == null || rows==0 ) {
                rows = 10;
            }
            pageList.setPage(page);
            pageList.setRecords(rows);
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<WithdrawCash>  data = financeSrevice.getWithdrawCashList(pageList,userName,receiptNumber,alipayAccount);
            if (data != null && data.size() > 0) {
                for (WithdrawCash withdrawCash : data) {
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id", withdrawCash.getId());
                    prizeMap.put("nickName", withdrawCash.getNickName());
                    prizeMap.put("receiptNumber", withdrawCash.getReceiptNumber());
                    prizeMap.put("alipayAccount", withdrawCash.getAlipayAccount());
                    prizeMap.put("type", withdrawCash.getType());
                    prizeMap.put("cashNum", withdrawCash.getCashNum());
                    prizeMap.put("reason",withdrawCash.getReason());
                    prizeMap.put("status", withdrawCash.getStatus());
                    if(withdrawCash.getCreateTime() != null){
                        prizeMap.put("createTime", format.format(withdrawCash.getCreateTime()));
                    }
                    resultList.add(prizeMap);
                }
            }
            pageList.setTotal(pageList.getRecords(), rows);
            pageList.setRows(resultList);
            str = JsonMapper.toJson(pageList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 查询提现用户信息
     * @param request
     * @param response
     * @param rows
     * @param page
     * @param nickName 昵称
     * @param realName 真实姓名
     * @param mobile 手机号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPresentUserList")
    public String getPresentUserList(HttpServletRequest request, HttpServletResponse response, @RequestParam int rows, @RequestParam int page,
                                      @RequestParam(value = "nickName", required = false) String nickName,@RequestParam(value = "realName", required = false) String realName,
                                      @RequestParam(value = "mobile", required = false) String mobile) {
        String str = "";
        try {
            JqgridPageList<Map<String, Object>> pageList = new JqgridPageList<Map<String, Object>>();
            if (page+"" == null || page==0 ) {
                page = 1;
            }
            if (rows+"" == null || rows==0 ) {
                rows = 10;
            }
            pageList.setPage(page);
            pageList.setRecords(rows);
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<PresentUserInfo>  data = financeSrevice.getPresentUserList(pageList, nickName, realName, mobile);
            if (data != null && data.size() > 0) {
                for (PresentUserInfo presentUserInfo : data) {
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id", presentUserInfo.getId());
                    prizeMap.put("nickName", presentUserInfo.getNickName());
                    prizeMap.put("realName", presentUserInfo.getRealName());
                    prizeMap.put("mobile", presentUserInfo.getMobile());
                    prizeMap.put("diamond", presentUserInfo.getDiamond());
                    prizeMap.put("wealthVal", presentUserInfo.getWealthVal());
                    prizeMap.put("waitMoney", presentUserInfo.getWaitMoney());
                    prizeMap.put("selectedMoney","0");//默认已选金额为0
                    resultList.add(prizeMap);
                }
            }
            pageList.setTotal(pageList.getRecords(), rows);
            pageList.setRows(resultList);
            str = JsonMapper.toJson(pageList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 根据用户ID查询用户提现详细信息
     * @param request
     * @param response
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPresentDetails")
    public String getPresentDetails(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = false) String id){
        String str = "";
        try {
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //根据用户表的主键获取用户的ID
            String accountID = financeSrevice.getAccountInfoOfAccountID(id);
            List<WithdrawCash> data = null;
            if(accountID != null && !"".equals(accountID)){
                data = financeSrevice.getPresentDetails(accountID);
            }
            if (data != null && data.size() > 0) {
                for (WithdrawCash withdrawCash : data) {
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("rowID", id);//父菜单中行ID
                    prizeMap.put("id", withdrawCash.getId());
                    prizeMap.put("alipayAccount", withdrawCash.getAlipayAccount());
                    prizeMap.put("receiptNumber", withdrawCash.getReceiptNumber());
                    prizeMap.put("type", withdrawCash.getType());
                    prizeMap.put("cashNum", withdrawCash.getCashNum());
                    prizeMap.put("status", withdrawCash.getStatus());
                    prizeMap.put("createTime",withdrawCash.getCreateTime());
                    prizeMap.put("reason",withdrawCash.getReason());
                    resultList.add(prizeMap);
                }
            }
            str = JsonMapper.toJson(resultList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

//    @ResponseBody
//    @RequestMapping("/updatWithdrawCashOfStatus")
//    public String updatWithdrawCashOfStatus(@RequestParam int id,@RequestParam int status,@RequestParam String receiptNumber,@RequestParam String reason){
//        boolean flag = false;
//        try {
//                Map<String,Object> params = new HashMap<String, Object>();
//                params.put("id",id);
//                params.put("status", status);
//                params.put("reason", reason);
//                params.put("receiptNumber",receiptNumber);
//                flag = financeSrevice.updatWithdrawCashOfStatus(params,diamondsRMBRate);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return String.valueOf(flag);
//    }

    @ResponseBody
    @RequestMapping("/updatWithdrawCashOfStatus")
    public String updatWithdrawCashOfStatus(@RequestParam int id,@RequestParam String ids,@RequestParam int status,@RequestParam String receiptNumber,@RequestParam String reason){
        boolean flag = false;
        String[] idsStr = ids.split(",");
        try {
            for(int i=0 ; i<idsStr.length ; i++){
                String idStr = idsStr[i];
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("id",idStr);
                params.put("status", status);
                params.put("reason", reason);
                params.put("receiptNumber",receiptNumber);
                flag = financeSrevice.updatWithdrawCashOfStatus(params,diamondsRMBRate);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

}
