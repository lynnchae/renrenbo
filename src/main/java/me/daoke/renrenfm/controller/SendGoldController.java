package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IAccountInfoService;
import me.daoke.renrenfm.service.IRequestRecordService;
import me.daoke.renrenfm.service.ISendGoldService;
import me.daoke.renrenfm.vo.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 赠送金币活动
 * Created by liyanqin on 2015/8/19.
 */
@Controller
@RequestMapping("gold")
public class SendGoldController {

    @Autowired
    private ISendGoldService sendGoldService;

    /**
     * 用户数据service接口
     */
    @Autowired
    private IAccountInfoService accountInfoService;

    /**
     * 首次请求
     *
     * @return
     */
    @RequestMapping(value = "/sendGoldIndex")
    public ModelAndView sendGoldIndex() {
        return new ModelAndView("statistics/sendGoldList");
    }


    @RequestMapping("/getSendGoldList")
    @ResponseBody
    public String getSendGoldList(@RequestParam String rows, @RequestParam String page, @RequestParam(value = "isCollect", required = false) Integer isCollect
            , @RequestParam(value = "mobile", required = false) String mobile) {
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
            if (isCollect == null) {
                isCollect = -1;
            }
            List<Map<String, Object>> data = sendGoldService.sendGoldList(pageList, mobile, isCollect);
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setRows(data);
            str = JsonMapper.toJson(pageList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
