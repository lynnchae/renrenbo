package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.page.PageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IAccountInfoService;
import me.daoke.renrenfm.service.IDataCountService;
import me.daoke.renrenfm.service.IRequestRecordService;
import me.daoke.renrenfm.vo.FansSingle;
import me.daoke.renrenfm.vo.SingleAnchor;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 数据统计
 * Created by zhaoym on 2015/10/20.
 */
@Controller
@RequestMapping(value="/dataCount")
public class DataCountController {

    /**
     * 每天数据标识
     */
    private static final String DAY = "day";

    /**
     * 每周数据标识
     */
    private static final String WEEK = "week";

    /**
     * 每月数据标识
     */
    private static final String MONTH = "month";

    /**
     * 数据统计service接口
     */
    @Autowired
    private IDataCountService dataCountService;

    /**
     * 请求记录service接口
     */
    @Autowired
    private IRequestRecordService requestRecordService;

    /**
     * 用户的service接口
     */
    @Autowired
    private IAccountInfoService accountInfoService;

    /**
     * 用户分析首次请求
     * @return
     */
    @RequestMapping(value = "/userChangeIndex")
    public ModelAndView userChangeIndex() {
        return new ModelAndView("dataCount/userChange");
    }

    /**
     * 所有主播数据统计首次请求
     * @return
     */
    @RequestMapping(value = "/anchorTotalIndex")
    public ModelAndView anchorTotalIndex() {
        return new ModelAndView("dataCount/anchorTotal");
    }

    /**
     * 单个主播数据统计首次请求
     * @return
     */
    @RequestMapping(value = "/singleAnchorIndex")
    public ModelAndView singleAnchorIndex() {
        return new ModelAndView("dataCount/singleAnchor");
    }

    /**
     * 单个粉丝数据统计首次请求
     * @return
     */
    @RequestMapping(value = "/singleFansIndex")
    public ModelAndView singleFansIndex() {
        return new ModelAndView("dataCount/singleFans");
    }

    /**
     * 礼物数据统计首次请求
     * @return
     */
    @RequestMapping(value = "/presentDataCountIndex")
    public ModelAndView presentDataCountIndex() {
        return new ModelAndView("dataCount/presentDataCount");
    }

    /**
     * 开箱数据统计首次请求
     * @return
     */
    @RequestMapping(value = "/openBoxDataCountIndex")
    public ModelAndView openBoxDataCountIndex() {
        return new ModelAndView("dataCount/openBoxDataCount");
    }

    /**
     * 礼品领取数据统计首次请求
     * @return
     */
    @RequestMapping(value = "/presentReceiveCountIndex")
    public ModelAndView presentReceiveCountIndex() {
        return new ModelAndView("dataCount/presentReceiveCount");
    }

    /**
     * 开箱领取数据统计首次请求
     * @return
     */
    @RequestMapping(value = "/openBoxReceiveCountIndex")
    public ModelAndView openBoxReceiveCountIndex() {
        return new ModelAndView("dataCount/openBoxReceiveCount");
    }

    /**
     * 听众数据总统计首次请求
     * @return
     */
    @RequestMapping(value = "/listenerTotalIndex")
    public ModelAndView listenerTotalIndex() {
        return new ModelAndView("dataCount/listenerTotal");
    }

    /**
     * 用户分析（新增用户、留存率、活跃户、总用户）
     * @return
     */
    @RequestMapping("/getUserChange")
    @ResponseBody
    public String getUserChange(){
        try {
            Map map = new HashMap<String, Object>();
            //人人播查询该天、周、月的新增用户
            int dayNumberRrb = requestRecordService.getNewRegisteredUserRrbDay();
            int weekNumberRrb = requestRecordService.getNewRegisteredUserRrbWeek();
            int monthNumberRrb = requestRecordService.getNewRegisteredUserRrbMonth();
            //FM查询该天、周、月的新增用户
            int dayNumberFm = requestRecordService.getNewRegisteredUserFmDay();
            int weekNumberFm = requestRecordService.getNewRegisteredUserFmWeek();
            int monthNumberFm = requestRecordService.getNewRegisteredUserFmMonth();
            //人人播查询该天、周、月的留存率
            int dayRetentionRrb = requestRecordService.getRetentionRateRrbDay();
            int weekRetentionRrb = requestRecordService.getRetentionRateRrbWeek();
            int monthRetentionRrb = requestRecordService.getRetentionRateRrbMonth();
            //FM查询该天、周、月的留存率(根据用户的ID)
            int dayRetentionAiFm = requestRecordService.getRetentionRateAiFmDay();
            int weekRetentionAiFm = requestRecordService.getRetentionRateAiFmWeek();
            int monthRetentionAiFm = requestRecordService.getRetentionRateAiFmMonth();
            //FM查询该天、周、月的留存率（根据登录的手机唯一标示码）
            int dayRetentionPcFm = requestRecordService.getRetentionRatePcFmDay();
            int weekRetentionPcFm = requestRecordService.getRetentionRatePcFmWeek();
            int monthRetentionPcFm = requestRecordService.getRetentionRatePcFmMonth();
            //人人播查询该天、周、月的活跃户
            int dayActiveRrb = requestRecordService.getActiveUserRrbDay();
            int weekActiveRrb = requestRecordService.getActiveUserRrbWeek();
            int monthActiveRrb = requestRecordService.getActiveUserRrbMonth();
            //FM查询该天、周、月的活跃户
            int dayActiveFm = requestRecordService.getActiveUserFmDay();
            int weekActiveFm = requestRecordService.getActiveUserFmWeek();
            int monthActiveFm = requestRecordService.getActiveUserFmMonth();
            //人人播查询总用户
            int overallRrb = requestRecordService.getOverallUserRrb();
            //FM查询总用户
            int overallFm = requestRecordService.getOverallUserFm();
            map.put("dayNumberRrb",dayNumberRrb);
            map.put("weekNumberRrb",weekNumberRrb);
            map.put("monthNumberRrb",monthNumberRrb);
            map.put("dayNumberFm",dayNumberFm);
            map.put("weekNumberFm",weekNumberFm);
            map.put("monthNumberFm",monthNumberFm);

            map.put("dayRetentionRrb",dayRetentionRrb);
            map.put("weekRetentionRrb",weekRetentionRrb);
            map.put("monthRetentionRrb",monthRetentionRrb);
            map.put("dayRetentionFm",dayRetentionAiFm+dayRetentionPcFm);
            map.put("weekRetentionFm",weekRetentionAiFm+weekRetentionPcFm);
            map.put("monthRetentionFm",monthRetentionAiFm+monthRetentionPcFm);

            map.put("dayActiveRrb",dayActiveRrb);
            map.put("weekActiveRrb",weekActiveRrb);
            map.put("monthActiveRrb",monthActiveRrb);
            map.put("dayActiveFm",dayActiveFm);
            map.put("weekActiveFm",weekActiveFm);
            map.put("monthActiveFm",monthActiveFm);

            map.put("overallRrb",overallRrb);
            map.put("overallFm",overallFm);
            if (map != null) {
                return JsonMapper.toJson(map, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    /**
//     * 用户分析（新增用户、留存率、活跃户、总用户）
//     * @return
//     */
//    @RequestMapping("/getUserChange")
//    @ResponseBody
//    public String getUserChange(){
//        try {
//            Map map = new HashMap<String, Object>();
//            //人人播查询该天、周、月的新增用户
//            int dayNumberRrb = requestRecordService.getNewRegisteredUserRrb(DAY);
//            int weekNumberRrb = requestRecordService.getNewRegisteredUserRrb(WEEK);
//            int monthNumberRrb = requestRecordService.getNewRegisteredUserRrb(MONTH);
//            //FM查询该天、周、月的新增用户
//            int dayNumberFm = requestRecordService.getNewRegisteredUserFm(DAY);
//            int weekNumberFm = requestRecordService.getNewRegisteredUserFm(WEEK);
//            int monthNumberFm = requestRecordService.getNewRegisteredUserFm(MONTH);
//            //人人播查询该天、周、月的留存率
//            int dayRetentionRrb = requestRecordService.getRetentionRateRrb(DAY);
//            int weekRetentionRrb = requestRecordService.getRetentionRateRrb(WEEK);
//            int monthRetentionRrb = requestRecordService.getRetentionRateRrb(MONTH);
//            //FM查询该天、周、月的留存率
//            int dayRetentionFm = requestRecordService.getRetentionRateFm(DAY);
//            int weekRetentionFm = requestRecordService.getRetentionRateFm(WEEK);
//            int monthRetentionFm = requestRecordService.getRetentionRateFm(MONTH);
//            //人人播查询该天、周、月的活跃户
//            int dayActiveRrb = requestRecordService.getActiveUserRrb(DAY);
//            int weekActiveRrb = requestRecordService.getActiveUserRrb(WEEK);
//            int monthActiveRrb = requestRecordService.getActiveUserRrb(MONTH);
//            //FM查询该天、周、月的活跃户
//            int dayActiveFm = requestRecordService.getActiveUserFm(DAY);
//            int weekActiveFm = requestRecordService.getActiveUserFm(WEEK);
//            int monthActiveFm = requestRecordService.getActiveUserFm(MONTH);
//            //人人播查询总用户
//            int overallRrb = requestRecordService.getOverallUserRrb();
//            //FM查询总用户
//            int overallFm = requestRecordService.getOverallUserFm();
//            map.put("dayNumberRrb",dayNumberRrb);
//            map.put("weekNumberRrb",weekNumberRrb);
//            map.put("monthNumberRrb",monthNumberRrb);
//            map.put("dayNumberFm",dayNumberFm);
//            map.put("weekNumberFm",weekNumberFm);
//            map.put("monthNumberFm",monthNumberFm);
//
//            map.put("dayRetentionRrb",dayRetentionRrb);
//            map.put("weekRetentionRrb",weekRetentionRrb);
//            map.put("monthRetentionRrb",monthRetentionRrb);
//            map.put("dayRetentionFm",dayRetentionFm);
//            map.put("weekRetentionFm",weekRetentionFm);
//            map.put("monthRetentionFm",monthRetentionFm);
//
//            map.put("dayActiveRrb",dayActiveRrb);
//            map.put("weekActiveRrb",weekActiveRrb);
//            map.put("monthActiveRrb",monthActiveRrb);
//            map.put("dayActiveFm",dayActiveFm);
//            map.put("weekActiveFm",weekActiveFm);
//            map.put("monthActiveFm",monthActiveFm);
//
//            map.put("overallRrb",overallRrb);
//            map.put("overallFm",overallFm);
//            if (map != null) {
//                return JsonMapper.toJson(map, true);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    /**
     * 人人播所有主播数据统计和FM所有粉丝数据统计
     * @return
     */
    @RequestMapping("/getAnchorTotal")
    @ResponseBody
    public String getAnchorTotal(){
        try {
            Map map = new HashMap<String, Object>();
            //查询所有主播发送的消息
            int sendMessageNum = accountInfoService.getSendMessage();
            //转发信息（微密语音）
            int frowardMessageWeme = accountInfoService.getForwardMessageWeme();
            //转发信息（FM语音）
            int forwardMessageFm = accountInfoService.getForwardMessageFm();
            //接收粉丝回复数
            int fansReplyNum = accountInfoService.getFansReply();
            //获取粉丝留存数
            int fansRetentionNum = accountInfoService.getFansRetention();
            //覆盖人次数
            int coverPeopleNum = accountInfoService.getCoverPeople();
            //播出时长
            int broadcastTimeNum = accountInfoService.getBroadcastTime();
            //播报热门城市
            int populaCityNum = accountInfoService.getBroadcastPopularCities();

            map.put("sendMessageNum",sendMessageNum);
            //转发信息为微密语音和FM语音累加
            map.put("frowardMessageNum",frowardMessageWeme+forwardMessageFm);
            map.put("fansReplyNum",fansReplyNum);
            map.put("fansRetentionNum",fansRetentionNum);
            map.put("coverPeopleNum",coverPeopleNum);
            map.put("broadcastTimeNum",broadcastTimeNum);
            map.put("populaCityNum",populaCityNum);
            if (map != null) {
                return JsonMapper.toJson(map, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 查询单个主播数据统计
     * @param request
     * @param response
     * @param rows
     * @param page
     * @param anchorName 主播姓名
     * @param idCard 身份证号
     * @return
     */
    @RequestMapping("/getAnchorSingleList")
    @ResponseBody
    public String getAnchorSingleList(HttpServletRequest request, HttpServletResponse response, @RequestParam int rows, @RequestParam int page,
                                      @RequestParam(value = "anchorName", required = false) String anchorName,@RequestParam(value = "idCard", required = false) String idCard,
                                      @RequestParam(value = "mobile", required = false) String mobile){
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
            List<SingleAnchor> data = accountInfoService.getSingleAnchorList(pageList,anchorName,idCard,mobile);
            if (data != null && data.size() > 0) {
                for (SingleAnchor singleAnchor : data) {
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id", singleAnchor.getId());
                    prizeMap.put("anchorName", singleAnchor.getAnchorName());
                    prizeMap.put("accountID", singleAnchor.getAccountID());
                    prizeMap.put("idCard", singleAnchor.getIdCard());
                    prizeMap.put("mobile", singleAnchor.getMobile());
                    prizeMap.put("broadcastTime", singleAnchor.getBroadcastTime());
                    prizeMap.put("coverPerson", singleAnchor.getCoverPerson());
                    prizeMap.put("ranking", singleAnchor.getRanking());
                    prizeMap.put("fanNum", singleAnchor.getFanNum());
                    prizeMap.put("fansRetention", singleAnchor.getFansRetention());
                    prizeMap.put("forwardMessageNum", singleAnchor.getFoMeFmNum()+singleAnchor.getFoMeWemeNum());
                    prizeMap.put("popNum", singleAnchor.getPopNum());
                    prizeMap.put("replyNum", singleAnchor.getReplyNum());
                    prizeMap.put("totalData", singleAnchor.getTotalData());
                    //粉丝活跃度
//                    prizeMap.put("fanActivity", singleAnchor.getFanActivity());
                    prizeMap.put("sendMessageNum", singleAnchor.getSendMessageNum());
                    prizeMap.put("broadcastTime", singleAnchor.getBroadcastTime());
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
     * 根据主播accountID查询粉丝贡献值
     * @return
     */
    @RequestMapping("/getFansRanking")
    @ResponseBody
    public String getFansRanking(@RequestParam int id){
        String str = "";
        try {
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            //根据用户id查询主播accountID
            String accountID = accountInfoService.getAccountIDInfoIsAccountID(id);
            List<Map<String,Object>> data = accountInfoService.getFansRanking(accountID);
            for (Map<String,Object> order : data) {
                Map<String, Object> prizeMap = new HashMap<String, Object>();
                prizeMap.put("id", order.get("id"));
                prizeMap.put("nickName", order.get("nickName"));
                prizeMap.put("headPic", order.get("headPic"));
                prizeMap.put("denseNum", order.get("denseNum"));
                prizeMap.put("ranking", order.get("ranking"));
                resultList.add(prizeMap);
            }
            str = JsonMapper.toJson(resultList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 根据主播的id查询该主播获取的礼物信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getPresentOfAccountID")
    public String getPresentOfAccountID(@RequestParam int rows, @RequestParam int page,@RequestParam int id){
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
            //根据用户id查询主播accountID
            String accountID = accountInfoService.getAccountIDInfoIsAccountID(id);
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>>  data = accountInfoService.getPresentOfAccountID(pageList,accountID);
            for (Map<String,Object> order : data) {
                Map<String, Object> prizeMap = new HashMap<String, Object>();
                prizeMap.put("id", order.get("id"));
                prizeMap.put("prizeNum", order.get("num"));
                prizeMap.put("prizeName", order.get("name"));
                prizeMap.put("prizeIcon", order.get("icon"));
                resultList.add(prizeMap);
            }
            pageList.setTotal(pageList.getRecords(), rows);
            pageList.setRows(resultList);
            str = JsonMapper.toJson(pageList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    @RequestMapping("/getListenerTotal")
    @ResponseBody
    public String getListenerTotal(){
        try {
            Map map = new HashMap<String, Object>();
            //查询所有听众的回复消息
            int replyNum = accountInfoService.getReplyNum();
            //查询所有听众打赏的金额总数
            int rewardMoney = accountInfoService.getRewardMoney();
            map.put("replyNum",replyNum);
            map.put("rewardMoney",rewardMoney);
            if (map != null) {
                return JsonMapper.toJson(map, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 查询单个粉丝数据统计
     * @param request
     * @param response
     * @param rows
     * @param page
     * @param nickName 粉丝昵称
     * @param mobile 手机号
     * @return
     */
    @RequestMapping("/getFansSingleList")
    @ResponseBody
    public String getFansSingleList(HttpServletRequest request, HttpServletResponse response, @RequestParam int rows, @RequestParam int page,
                                    @RequestParam(value = "nickName", required = false) String nickName, @RequestParam(value = "mobile", required = false) String mobile){
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
            List<FansSingle> data = accountInfoService.getFansSingleList(pageList, nickName, mobile);
            if (data != null && data.size() > 0) {
                for (FansSingle fansSingle : data) {
                    Map<String, Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id", fansSingle.getId());
                    prizeMap.put("nickName", fansSingle.getNickName());
                    prizeMap.put("mobile", fansSingle.getMobile());
                    prizeMap.put("accountID", fansSingle.getAccountID());
                    prizeMap.put("voiceAnchorNum", fansSingle.getVoiceAnchorNum());
                    prizeMap.put("voiceSystemNum", fansSingle.getVoiceSystemNum());
                    prizeMap.put("replyNum", fansSingle.getReplyNum());
                    prizeMap.put("moneyNum", fansSingle.getMoneyNum());
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
     * 根据听众的id查询打赏的礼物信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/rewardPresentOfAccountID")
    public String rewardPresentOfAccountID(@RequestParam int rows, @RequestParam int page,@RequestParam int id){
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
            //根据用户id查询听众用户的accountID
            String accountID = accountInfoService.getListenerAccountIsAccountID(id);
            List<Map<String, Object>>  data = accountInfoService.rewardPresentOfAccountID(pageList, accountID);
            for (Map<String,Object> order : data) {
                Map<String, Object> prizeMap = new HashMap<String, Object>();
                prizeMap.put("id", order.get("id"));//主键
                prizeMap.put("prizeNum", order.get("num"));//礼物数量
                prizeMap.put("moneyNum", order.get("moneyNum"));//礼物总金额
                prizeMap.put("prizeName", order.get("name"));//礼物名称
                prizeMap.put("prizeIcon", order.get("icon"));//礼物图片
                resultList.add(prizeMap);
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
     * 根据主播accountID查询粉丝贡献值
     * @return
     */
    @RequestMapping("/rewardRankingOfAccountID")
    @ResponseBody
    public String rewardRankingOfAccountID(@RequestParam int id){
        String str = "";
        try {
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            //根据用户id查询听众用户的accountID
            String accountID = accountInfoService.getListenerAccountIsAccountID(id);
            List<Map<String,Object>> data = accountInfoService.rewardRankingOfAccountID(accountID);
            for(int i=0 ; i<data.size() ; i++){
                Map<String, Object> prizeMap = new HashMap<String, Object>();
                prizeMap.put("id", data.get(i).get("id"));
                prizeMap.put("nickName", data.get(i).get("nickName"));
                prizeMap.put("headPic", data.get(i).get("headPic"));
                prizeMap.put("denseNum", data.get(i).get("denseNum"));
                prizeMap.put("ranking", i+1);
                resultList.add(prizeMap);
            }
            str = JsonMapper.toJson(resultList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 礼物数据统计
     * @param rows
     * @param page
     * @param name 礼物名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/presentDataCount")
    public String presentDataCount(@RequestParam int rows, @RequestParam int page, @RequestParam(value = "name", required = false) String name){
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
            List<Map<String, Object>>  data = accountInfoService.presentDataCount(pageList, name);
            for(int i=0 ; i<data.size() ; i++){
                Map<String, Object> prizeMap = new HashMap<String, Object>();
                prizeMap.put("id", data.get(i).get("id"));//主键
                prizeMap.put("name", data.get(i).get("name"));//礼物名称
                prizeMap.put("icon", data.get(i).get("icon"));//礼物图片
                prizeMap.put("num", data.get(i).get("num"));//礼物销量
                prizeMap.put("ranking", data.get(i).get("ranking"));//销量排名
                prizeMap.put("totalAmount", data.get(i).get("totalAmount"));//礼物总金额
                resultList.add(prizeMap);
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
     * 开箱数据统计
     * @return
     */
    @RequestMapping("/openBoxDataCount")
    @ResponseBody
    public String openBoxDataCount(){
        try {
            Map map = new HashMap<String, Object>();
            //查询开箱数
            int outOfBoxNum = accountInfoService.getOutOfBoxNum();
            //查询送主播数
            int sendAnchorNum = accountInfoService.getSendAnchorNum();
            //查询发货订单数
            int deliveryOrderNum = accountInfoService.getDeliveryOrderNum();

            map.put("outOfBoxNum",outOfBoxNum);
            map.put("sendAnchorNum",sendAnchorNum);
            map.put("deliveryOrderNum",deliveryOrderNum);
            if (map != null) {
                return JsonMapper.toJson(map, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 礼品领取数据统计
     * @param rows
     * @param page
     * @param name 礼品名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/presentReceiveCount")
    public String presentReceiveCount(@RequestParam int rows, @RequestParam int page, @RequestParam(value = "name", required = false) String name){
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
            List<Map<String, Object>>  data = accountInfoService.presentReceiveCount(pageList, name);
            for(int i=0 ; i<data.size() ; i++){
                Map<String, Object> prizeMap = new HashMap<String, Object>();
                prizeMap.put("id", data.get(i).get("id"));
                prizeMap.put("name", data.get(i).get("name"));
                prizeMap.put("icon", data.get(i).get("icon"));
                prizeMap.put("receiveNum", data.get(i).get("receiveNum"));
                prizeMap.put("rate", data.get(i).get("rate"));
                resultList.add(prizeMap);
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
     * 开箱领取数据统计
     * @param rows
     * @param page
     * @param name 箱子名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/openBoxReceiveCount")
    public String openBoxReceiveCount(@RequestParam int rows, @RequestParam int page, @RequestParam(value = "name", required = false) String name){
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
            List<Map<String, Object>>  data = accountInfoService.openBoxReceiveCount(pageList, name);
            for(int i=0 ; i<data.size() ; i++){
                Map<String, Object> prizeMap = new HashMap<String, Object>();
                prizeMap.put("id", data.get(i).get("id"));
                prizeMap.put("boxName", data.get(i).get("boxName"));
                prizeMap.put("boxImg", data.get(i).get("boxImg"));
                prizeMap.put("receiveNum", data.get(i).get("receiveNum"));
                prizeMap.put("rate", data.get(i).get("rate"));
                resultList.add(prizeMap);
            }
            pageList.setTotal(pageList.getRecords(), rows);
            pageList.setRows(resultList);
            str = JsonMapper.toJson(pageList, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
