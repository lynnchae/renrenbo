package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IWemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统操作controller
 * @author zhaoym
 * @date 2015/10/09
 */
@Controller
@RequestMapping(value="/weme")
public class WemeController {

    @Autowired
    private IWemeService wemeService;

    /**
     * 返货微密用户密点数首次请求
     * @return
     */
    @RequestMapping(value = "/wemeCoinsIndex")
    public ModelAndView sendGoldIndex() {
        return new ModelAndView("weme/wemeCoins");
    }


    /**
     * 返回密点数活动getDenseI
     * @param rows
     * @param page
     * @param denseStatus 是否已经给用户返回密点数
     * @param mobile 手机号
     * @return
     */
    @RequestMapping("/getWemeInfoList")
    @ResponseBody
    public String getWemeInfoList(HttpServletRequest request, HttpServletResponse response,@RequestParam String rows, @RequestParam String page,
                                   @RequestParam(value = "denseStatus", required = false) String denseStatus,@RequestParam(value = "mobile", required = false) String mobile) {
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
            List<Map<String, Object>> data = wemeService.getWemeInfoList(pageList, mobile, denseStatus);
            if(data != null && data.size() >0 ){
                for(int i=0 ; i<data.size() ; i++){
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id",data.get(i).get("id"));
                    prizeMap.put("mobile",data.get(i).get("mobile"));
                    prizeMap.put("voiceNum",data.get(i).get("voiceNum"));
                    prizeMap.put("denseStatus",data.get(i).get("denseStatus"));
                    prizeMap.put("createTime",data.get(i).get("createTime"));
                    prizeMap.put("reason",data.get(i).get("reason"));
                }
            }
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setRows(data);
            str = JsonMapper.toJson(pageList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 更新返回微密用户密点数的状态
     * @param id
     * @param denseStatus 返回微密用户密点数状态
     * @param reason 备注信息
     * @return
     */
    @RequestMapping("/updateWemeStatus")
    @ResponseBody
    public String updateWemeStatus(@RequestParam int id,@RequestParam int denseStatus,@RequestParam String reason){
        boolean flag = false;//默认修改状态失败
        try {
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("id",id);
            params.put("denseStatus", denseStatus);
            params.put("reason", reason);
            flag = wemeService.updateWemeStatus(id, denseStatus, reason);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

}
