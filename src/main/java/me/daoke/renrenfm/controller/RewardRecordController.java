package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IRewardRecordService;
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
 * 打赏记录
 * Created by liyanqin on 2015/8/19.
 */
@Controller
@RequestMapping("/reward")
public class RewardRecordController {

    /**打赏记录**/
    @Autowired
    private IRewardRecordService rewardRecordService;

    /**
     * 首次请求
     * @return
     */
    @RequestMapping(value = "/rewardIndex")
    public ModelAndView rewardIndex() {
        return new ModelAndView("statistics/rewardList");
    }

    @RequestMapping("/getRewardRecoard")
    @ResponseBody
    public String getRewardRecord(@RequestParam String rows, @RequestParam String page,@RequestParam(value = "anchorName", required = false) String anchorName,
                                   @RequestParam(value = "listenerName", required = false) String listenerName,@RequestParam(value = "presentName", required = false) String presentName){
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
            List<Map<String,Object>> data = rewardRecordService.getRewardRecord(pageList,anchorName,listenerName,presentName);
            if (data != null && data.size() > 0) {
                for (Map<String,Object> reward : data) {
                    Map<String, Object> rewardRecordMap = new HashMap<String, Object>();
                    rewardRecordMap.put("id",reward.get("id"));
                    rewardRecordMap.put("anchorNickName",reward.get("anchorNickName"));
                    rewardRecordMap.put("anchorRealName",reward.get("realName"));
                    rewardRecordMap.put("listenerName",reward.get("listenerName"));
                    rewardRecordMap.put("presentName",reward.get("presentName"));
                    rewardRecordMap.put("denseNum",reward.get("denseNum"));
                    rewardRecordMap.put("createTime",reward.get("createTime"));
                    resultList.add(rewardRecordMap);
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
