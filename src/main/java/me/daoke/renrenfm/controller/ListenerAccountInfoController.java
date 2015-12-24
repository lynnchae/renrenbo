package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.entity.ListenerAccountInfo;
import me.daoke.renrenfm.service.IListenerAccountInfoService;
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
 * 听众信息
 * Created by liyanqin on 2015/8/20.
 */
@Controller
@RequestMapping("/listener")
public class ListenerAccountInfoController {

    @Autowired
    private IListenerAccountInfoService listenerAccountInfoService;
    /**
     * 首次请求
     * @return
     */
    @RequestMapping(value = "/listenerAccountIndex")
    public ModelAndView listenerOrderIndex() {
        return new ModelAndView("listener/listenerList");
    }

    @RequestMapping("/getAllListener")
    @ResponseBody
    public String getAllListener(@RequestParam String rows, @RequestParam String page,@RequestParam(value = "name", required = false) String name){
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
            List<ListenerAccountInfo> data = listenerAccountInfoService.getAllListener(pageList,name);
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (data != null && data.size() > 0) {
                for(ListenerAccountInfo listener : data){
                    Map<String, Object> listenerOrderMap = new HashMap<String, Object>();
                    listenerOrderMap.put("id",listener.getId());
                    listenerOrderMap.put("accountID",listener.getAccountID());
                    listenerOrderMap.put("nickName",listener.getNickName());
                    listenerOrderMap.put("headPic",listener.getHeadPic());
                    listenerOrderMap.put("denseNum",listener.getDenseNum());
                    listenerOrderMap.put("createTime", format.format(listener.getCreateTime()));
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
}
