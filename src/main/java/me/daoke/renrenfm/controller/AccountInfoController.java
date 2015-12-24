package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.entity.AccusationRecordInfo;
import me.daoke.renrenfm.entity.AppealInfo;
import me.daoke.renrenfm.service.IAccountInfoService;
import me.daoke.renrenfm.service.IRequestRecordService;
import me.daoke.renrenfm.vo.AccountInfo;
import me.daoke.renrenfm.vo.ConvertEntity;
import me.daoke.renrenfm.vo.KeyValue;
import me.daoke.renrenfm.vo.SingleAnchor;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户controller
 * Created by zhaoym on 2015/8/6.
 */
@Controller
@RequestMapping(value="/accountInfo")
public class AccountInfoController {

    @Autowired
    private IAccountInfoService accountInfoService;

    /**
     * 主播认证首次请求
     *
     * @return
     */
    @RequestMapping(value = "/accountIndex")
    public ModelAndView accountIndex() {
        return new ModelAndView("account/accountInfo");
    }

    /**
     * 举报管理首次请求
     *
     * @return
     */
    @RequestMapping(value = "/accusationIndex")
    public ModelAndView accusationIndex() {
        return new ModelAndView("account/accusationInfo");
    }

    /**
     * 申诉管理首次请求
     * @return
     */
    @RequestMapping(value = "/appealIndex")
    public ModelAndView appealIndex() {
        return new ModelAndView("account/appealInfo");
    }


    /**
     * 单个粉丝数据统计首次请求
     * @return
     */
    @RequestMapping(value = "/singleFansIndex")
    public ModelAndView singleFansIndex() {
        return new ModelAndView("account/singleFans");
    }

    @ResponseBody
    @RequestMapping(value = "/getAccountInfoList")
    public String getAccountInfoList(HttpServletRequest request, HttpServletResponse response, @RequestParam int rows, @RequestParam int page, @RequestParam(value = "anchorStatus", required = false) String anchorStatus,
                                     @RequestParam(value = "cityName", required = false) String cityName,@RequestParam(value = "mobile", required = false) String mobile) {
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
            List<AccountInfo> data = accountInfoService.getAccountInfoList(pageList, anchorStatus, cityName,mobile);

            if (data != null && data.size() > 0) {
                for (AccountInfo accountInfo : data) {
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id", accountInfo.getId());
                    prizeMap.put("accountID", accountInfo.getAccountID());
                    prizeMap.put("nickName", accountInfo.getNickName());
                    prizeMap.put("realName", accountInfo.getRealName());
                    prizeMap.put("headPic", accountInfo.getHeadPic());
                    prizeMap.put("idCard", accountInfo.getIdCard());
                    prizeMap.put("mobile", accountInfo.getMobile());
                    prizeMap.put("sex", accountInfo.getSex());
                    prizeMap.put("remark", accountInfo.getRemark());
                    prizeMap.put("isVoice", accountInfo.getIsVoice());
                    if(accountInfo.getCreateTime() != null){
                        prizeMap.put("createTime", format.format(accountInfo.getCreateTime()));
                    }
                    prizeMap.put("diamond", accountInfo.getDiamond());
                    prizeMap.put("diamondWealthVal", accountInfo.getDiamondWealthVal());
                    prizeMap.put("popularityVal", accountInfo.getPopularityVal());
                    prizeMap.put("status", accountInfo.getStatus());
                    prizeMap.put("cityName", accountInfo.getCityName());
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
     * 审核提交主播信息
     * @param id
     * @param status 审核状态
     * @param reason 备注
     * @return
     */
    @ResponseBody
    @RequestMapping("/submitAuditAccountInfo")
    public String submitAuditAccountInfo(@RequestParam int id,@RequestParam int status,@RequestParam String reason){
        boolean flag = false;
        try {
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("id",id);
            params.put("status", status);
            params.put("reason", reason);
            flag = accountInfoService.submitAuditAccountInfo(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

    /**
     * 查询举报信息
     * @param request
     * @param response
     * @param rows
     * @param page
     * @param reportName 举报人
     * @param beReportName 被举报人
     * @param idCard 身份证号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAccusationInfoList")
    public String getAccusationInfoList(HttpServletRequest request, HttpServletResponse response, @RequestParam int rows, @RequestParam int page, @RequestParam(value = "reportName", required = false) String reportName,
                                     @RequestParam(value = "beReportName", required = false) String beReportName,@RequestParam (value = "idCard", required = false) String idCard) {
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
            List<AccusationRecordInfo> data = accountInfoService.getAccusationInfoList(pageList, reportName, beReportName, idCard);

            if (data != null && data.size() > 0) {
                for (AccusationRecordInfo accusationRecordInfo : data) {
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id", accusationRecordInfo.getId());
                    prizeMap.put("accountID", accusationRecordInfo.getAccountID());
                    prizeMap.put("beAccountID", accusationRecordInfo.getBeAccountID());
                    prizeMap.put("reportName", accusationRecordInfo.getReportName());
                    prizeMap.put("beReportName", accusationRecordInfo.getBeReportName());
                    prizeMap.put("idCard",accusationRecordInfo.getIdCard());
                    prizeMap.put("status",accusationRecordInfo.getStatus());
                    prizeMap.put("accusationTime", format.format(accusationRecordInfo.getAccusationTime()));
                    prizeMap.put("accusationContent", accusationRecordInfo.getAccusationContent());
                    prizeMap.put("createTime", format.format(accusationRecordInfo.getCreateTime()));
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
     * 添加禁播数据以及禁播指定主播
     * @param durationLong 禁播时长
     * @param formerStatus 禁播前的状态
     * @param status 禁播设置
     * @param beAccountID 禁播人员ID
     * @param reason 禁播原因
     * @return
     */
    @ResponseBody
    @RequestMapping("/addNoPlay")
    public String addNoPlay(@RequestParam String durationLong,@RequestParam String formerStatus,@RequestParam String status,@RequestParam String beAccountID,@RequestParam String reason){
        boolean flag = false;//默认添加数据失败
        boolean beFlag = false;
        //添加禁播数据
        flag = accountInfoService.addNoPlay(formerStatus,beAccountID,reason,durationLong);
        //更新主播的状态（禁播）
        beFlag = accountInfoService.updateAccountInfoOfIsVoice(status,beAccountID);
        if(flag && beFlag){
            return String.valueOf(flag);
        }
        return String.valueOf(flag);
    }

    /**
     * 查询申诉信息
     * @param request
     * @param response
     * @param rows
     * @param page
     * @param appealName 申诉人
     * @param idCard 身份证号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAppealInfoList")
    public String getAppealInfoList(HttpServletRequest request, HttpServletResponse response, @RequestParam int rows, @RequestParam int page, @RequestParam(value = "appealName", required = false) String appealName,
                                    @RequestParam (value = "idCard", required = false) String idCard) {
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
            List<AppealInfo> data = accountInfoService.getAppealInfoList(pageList, appealName,idCard);
            if (data != null && data.size() > 0) {
                for (AppealInfo appealInfo : data) {
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id", appealInfo.getId());
                    prizeMap.put("reason",appealInfo.getReason());
                    prizeMap.put("accountID",appealInfo.getAccountID());
                    prizeMap.put("idCard", appealInfo.getIdCard());
                    prizeMap.put("complainantName",appealInfo.getComplainantName());
                    prizeMap.put("createTime", format.format(appealInfo.getCreateTime()));
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
     * 添加解禁数据以及解禁对应的主播
     * @param accountID 解禁人员
     * @param status 解禁状态
     * @param reason 备注信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRelievePlay")
    public String addRelievePlay(@RequestParam String accountID,@RequestParam String status,@RequestParam String reason){
        boolean flag = false;//默认添加数据失败
        boolean beFlag = false;
        //添加解禁数据
        flag = accountInfoService.addRelievePlay(accountID, reason,"0");
        //查询禁播前状态
        String formerStatus = accountInfoService.getNoPlayOfFormerStatus(accountID);
        if(formerStatus != null){
            //更新主播状态
            beFlag = accountInfoService.updateAccountInfoOfStatus(accountID,status,formerStatus);
        }
        if(flag && beFlag){
            return String.valueOf(flag);
        }
        return String.valueOf(flag);
    }
}
