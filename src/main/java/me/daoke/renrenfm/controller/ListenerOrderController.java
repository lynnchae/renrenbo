package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IListenerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开箱记录
 * Created by liyanqin on 2015/8/19.
 */
@Controller
@RequestMapping("/listenerOrder")
public class ListenerOrderController {

    /**打赏记录**/
    @Autowired
    private IListenerOrderService listenerOrderService;

    /**
     * 首次请求
     * @return
     */
    @RequestMapping(value = "/listenerOrderIndex")
    public ModelAndView listenerOrderIndex() {
        return new ModelAndView("statistics/listenerOrderList");
    }

    @RequestMapping("/getListenerOrderList")
    @ResponseBody
    public String getListenerOrderList(@RequestParam String rows, @RequestParam String page,@RequestParam(value = "boxName", required = false) String boxName,
                                   @RequestParam(value = "prizeName", required = false) String prizeName,@RequestParam(value = "status", required = false) Integer status,@RequestParam(value = "cancelType", required = false) Integer cancelType){
        String str = "";
        try {
            JqgridPageList<Map<String, Object>> pageList = new JqgridPageList<Map<String, Object>>();
            if (page == null || "".equals(page)) {
                page = "1";
            }
            if (rows == null || "".equals(rows)) {
                rows = "10";
            }
            if(status == null){
                status = -1;
            }
            if(cancelType == null){
                cancelType = -1;
            }
            pageList.setPage(Integer.parseInt(page));
            pageList.setRecords(Integer.parseInt(rows));
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Map<String,Object>> data = listenerOrderService.getListenerOrderList(pageList,boxName,prizeName,status,cancelType);
            if (data != null && data.size() > 0) {
                for (Map<String,Object> order : data) {
                    Map<String, Object> listenerOrderMap = new HashMap<String, Object>();
                    listenerOrderMap.put("id",order.get("id"));
                    listenerOrderMap.put("listenerName",order.get("listenerName"));
                    listenerOrderMap.put("anchorName",order.get("anchorName"));
                    listenerOrderMap.put("boxName",order.get("boxName"));
                    listenerOrderMap.put("prizeName",order.get("prizeName"));
                    listenerOrderMap.put("cancelType",order.get("cancelType"));
                    listenerOrderMap.put("status",order.get("status"));
                    listenerOrderMap.put("createTime",order.get("createTime"));
                    listenerOrderMap.put("shoppingAddressID",order.get("shoppingAddressID"));
                    resultList.add(listenerOrderMap);
                }
            }
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setRows(resultList);
            str = JsonMapper.toJson(pageList, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 查询订单详情
     * @return
     */
    @RequestMapping(value="/shoppingDetail")
    public ModelAndView editPrizeInBox(@RequestParam int orderId){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            result = listenerOrderService.queryOrderDetail(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("statistics/shoppingDetail","result",result);
    }

    @RequestMapping("/saveSendMessage")
    @ResponseBody
    public String saveSendMessage(@RequestParam int orderId,@RequestParam String sendCompany,@RequestParam String sendNumber){
        boolean flag = listenerOrderService.saveSendMessage(orderId,sendCompany,sendNumber);
        return String.valueOf(flag);
    }
}
