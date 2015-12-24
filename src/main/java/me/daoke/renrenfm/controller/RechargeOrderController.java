package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.common.util.JsonMapper;

import me.daoke.renrenfm.entity.RechargeOrder;
import me.daoke.renrenfm.service.IRechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户controller
 * Created by liyanqin on 2015/8/18.
 */
@Controller
@RequestMapping(value="/recharge")
public class RechargeOrderController {

    @Autowired
    private IRechargeOrderService rechargeOrderService;

    /**
     * 首次请求充值记录
     *
     * @return
     */
    @RequestMapping(value = "/rechargeIndex")
    public ModelAndView rechargeIndex() {
        return new ModelAndView("statistics/rechargeList");
    }

    @ResponseBody
    @RequestMapping(value = "/getAllRechargeOrder")
    public String getAllRechargeOrder(@RequestParam String rows, @RequestParam String page, @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
               @RequestParam(value = "listenerName", required = false) String listenerName,@RequestParam(value = "rechargeNum", required = false) String rechargeNum,
               @RequestParam(value = "mobile", required = false) String mobile) {
        String str = "";
        try {
            JqgridPageList<Map<String, Object>> pageList = new JqgridPageList<Map<String, Object>>();
            if (page == null || "".equals(page)) {
                page = "1";
            }
            if (rows == null || "".equals(rows)) {
                rows = "10";
            }
            pageList.setPage(Integer.parseInt(page));
            pageList.setRecords(Integer.parseInt(rows));
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            if(orderStatus == null){
                orderStatus = -1;
            }
            List<Map<String,Object>> data = rechargeOrderService.getAllRechargeOrder(pageList, orderStatus, listenerName, rechargeNum, mobile);

            if (data != null && data.size() > 0) {
                for (Map<String,Object> order : data) {
                    Map<String, Object> rechargeOrderMap = new HashMap<String, Object>();
                    rechargeOrderMap.put("id", order.get("id"));
                    rechargeOrderMap.put("accountID", order.get("accountID"));
                    rechargeOrderMap.put("nickName", order.get("nickName"));
                    rechargeOrderMap.put("rechargeNum", order.get("rechargeNum"));
                    rechargeOrderMap.put("orderNo", order.get("orderNo"));
                    rechargeOrderMap.put("mobile", order.get("mobile"));
                    rechargeOrderMap.put("denseNum", order.get("denseNum"));
                    rechargeOrderMap.put("totalMoney", order.get("totalMoney"));
                    rechargeOrderMap.put("orderStatus", order.get("orderStatus"));
                    rechargeOrderMap.put("type", order.get("type"));
                    rechargeOrderMap.put("createTime", order.get("createTime"));
                    resultList.add(rechargeOrderMap);
                }
            }
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setRows(resultList);
            str = JsonMapper.toJson(pageList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 首次请求充值回调
     *
     * @return
     */
    @RequestMapping(value = "/payCallBackIndex")
    public ModelAndView payCallBackIndex() {
        return new ModelAndView("statistics/payBackRecord");
    }

    @ResponseBody
    @RequestMapping(value = "/getAllPayBackRecord")
    public String getAllPayBackRecord(@RequestParam String rows, @RequestParam String page, @RequestParam(value = "orderNo", required = false) String orderNo,
                                      @RequestParam(value = "tradeStatus", required = false) String tradeStatus) {
        String str = "";
        try {
            JqgridPageList<Map<String, Object>> pageList = new JqgridPageList<Map<String, Object>>();
            if (page == null || "".equals(page)) {
                page = "1";
            }
            if (rows == null || "".equals(rows)) {
                rows = "10";
            }
            pageList.setPage(Integer.parseInt(page));
            pageList.setRecords(Integer.parseInt(rows));
            List<Map<String,Object>> data = rechargeOrderService.getAllPayBackRecord(pageList, orderNo, tradeStatus);
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setRows(data);
            str = JsonMapper.toJson(pageList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
