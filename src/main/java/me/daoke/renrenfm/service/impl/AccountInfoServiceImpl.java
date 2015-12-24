package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.page.PageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.IAccountInfoDao;
import me.daoke.renrenfm.entity.*;
import me.daoke.renrenfm.service.IAccountInfoService;
import me.daoke.renrenfm.vo.AccountInfo;
import me.daoke.renrenfm.vo.FansSingle;
import me.daoke.renrenfm.vo.SingleAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhaoym on 2015/8/6.
 */
@Service
public class AccountInfoServiceImpl implements IAccountInfoService {

    @Autowired
    private IAccountInfoDao accountInfoDao;

    @Override
    public List<AccountInfo> getAccountInfoList(JqgridPageList pageList,String anchorStatus,String cityName,String mobile){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid",AccountInfo.ISVALID.VALID);
        if(!"-1".equals(anchorStatus) && !"".equals(anchorStatus) && anchorStatus != null){
            params.put("anchorStatus",anchorStatus);
        }
        if(cityName != null && !"".equals(cityName)){
            params.put("cityName",cityName);
        }
        if(mobile != null && !"".equals(mobile)){
            params.put("mobile",mobile);
        }
        List<AccountInfo> resultList = accountInfoDao.selectList("getAccountInfoList", params);
        int num = (Integer)accountInfoDao.selectOne("getAccountInfoNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public boolean submitAuditAccountInfo(Map<String, Object> params) {
        boolean flag = false;//默认提交失败
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        flag = (Integer)accountInfoDao.update("submitAuditAccountInfo",params) > 0;
        return flag;
    }

    @Override
    public List<AccusationRecordInfo> getAccusationInfoList(JqgridPageList pageList, String reportName, String beReportName,String idCard) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid",AccountInfo.ISVALID.VALID);
        if(idCard != null && !"".equals(idCard)){
            params.put("idCard",idCard);
        }
        if(reportName != null && !"".equals(reportName)){
            params.put("reportName",reportName);
        }
        if(beReportName != null && !"".equals(beReportName)){
            params.put("beReportName",beReportName);
        }
        List<AccusationRecordInfo> resultList = accountInfoDao.selectList("getAccusationInfoList", params);
        int num = (Integer)accountInfoDao.selectOne("getAccusationInfoNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public boolean addNoPlay(String formerStatus,String beAccountID,String reason,String durationLong) {
        Map<String,Object> params = new HashMap<String, Object>();
        int duration = 72;//定义禁播时长，默认为三天
        if("2".equals(durationLong)){
            duration = 48;
        }
        if("1".equals(durationLong)){
            duration = 24;
        }
        params.put("accountID",beAccountID);
        params.put("formerStatus",formerStatus);
        params.put("reason",reason);
        params.put("duration",duration);
        params.put("isValid",1);
        Date currentDate = new Date();
        params.put("noPlayTime",AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        params.put("createTime",AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int result = accountInfoDao.insert("addNoPlay",params);
        return result > 0 ? true :false;
    }

    @Override
    public boolean updateAccountInfoOfIsVoice(String status, String beAccountID) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("accountID",beAccountID);
        params.put("isVoice",status);
        int result = accountInfoDao.update("updateAccountInfoOfIsVoice",params);
        return result > 0 ? true :false;
    }

    @Override
    public List<AppealInfo> getAppealInfoList(JqgridPageList pageList, String appealName,String idCard) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid",AccountInfo.ISVALID.VALID);
        if(appealName != null && !"".equals(appealName)){
            params.put("appealName",appealName);
        }
        if(idCard != null && !"".equals(idCard)){
            params.put("idCard",idCard);
        }
        List<AppealInfo> resultList = accountInfoDao.selectList("getAppealInfoList", params);
        int num = (Integer)accountInfoDao.selectOne("getAppealInfoNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public boolean addRelievePlay(String accountID, String reason,String type) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("accountID",accountID);
        params.put("type",type);//解禁方式: 0 人工解禁 1 系统解禁
        params.put("reason",reason);
        params.put("isValid",1);
        Date currentDate = new Date();
        params.put("createTime",AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int result = accountInfoDao.insert("addRelievePlay",params);
        return result > 0 ? true :false;
    }


    @Override
    public String getNoPlayOfFormerStatus(String accountID) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("accountID",accountID);
        return accountInfoDao.selectOne("getNoPlayOfFormerStatus",params);
    }

    @Override
    public List<NoPlay> getNoPlayList() {
        return accountInfoDao.selectList("getNoPlayList");
    }

    @Override
    public boolean updateAccountInfoOfStatus(String accountID,String isVoice, String status) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("isVoice",isVoice);
        params.put("status",status);
        params.put("accountID",accountID);
        int result = accountInfoDao.update("updateAccountInfoOfStatus",params);
        return result > 0 ? true :false;
    }

    @Override
    public int getSendMessage() {
        int num = 0;
        if(accountInfoDao.selectOne("getSendMessage") != null){
            num = (Integer)accountInfoDao.selectOne("getSendMessage");
        }
        return num;
    }

    @Override
    public int getForwardMessageWeme() {
        int num = 0;
        if(accountInfoDao.selectOne("getForwardMessageWeme") != null){
            num = (Integer)accountInfoDao.selectOne("getForwardMessageWeme");
        }
        return num;
    }

    @Override
    public int getForwardMessageFm() {
        int num = 0;
        if(accountInfoDao.selectOne("getForwardMessageFm") != null){
            num = (Integer)accountInfoDao.selectOne("getForwardMessageFm");
        }
        return num;
    }

    @Override
    public int getFansReply() {
        int num = 0;
        if(accountInfoDao.selectOne("getFansReply") != null){
            num = (Integer)accountInfoDao.selectOne("getFansReply");
        }
        return num;
    }

    @Override
    public int getFansRetention() {
        int num = 0;
        if(accountInfoDao.selectOne("getFansRetention") != null){
            num = (Integer)accountInfoDao.selectOne("getFansRetention");
        }
        return num;
    }

    @Override
    public int getCoverPeople() {
        int num = 0;
        if(accountInfoDao.selectOne("getCoverPeople") != null){
            num = (Integer)accountInfoDao.selectOne("getCoverPeople");
        }
        return num;
    }

    @Override
    public int getBroadcastTime() {
        int num = 0;
        if(accountInfoDao.selectOne("getBroadcastTime") != null){
            num = (Integer)accountInfoDao.selectOne("getBroadcastTime");
        }
        return num;
    }

    @Override
    public int getBroadcastPopularCities() {
        int num = 0;
        if(accountInfoDao.selectOne("getBroadcastPopularCities") != null){
            num = (Integer)accountInfoDao.selectOne("getBroadcastPopularCities");
        }
        return num;
    }

    @Override
    public List<SingleAnchor> getSingleAnchorList(JqgridPageList pageList,String anchorName,String idCard,String mobile) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        if (anchorName != null && !"".equals(anchorName)) {
            params.put("anchorName", anchorName);
        }
        if (idCard != null && !"".equals(idCard)) {
            params.put("idCard", idCard);
        }
        if (mobile != null && !"".equals(mobile)) {
            params.put("mobile", mobile);
        }
        List<SingleAnchor> resultList = accountInfoDao.selectList("getSingleAnchorList",params);
        int num = (Integer)accountInfoDao.selectOne("getSingleAnchorNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public List<Map<String,Object>> getFansRanking(String accountID) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (accountID != null && !"".equals(accountID)) {
            params.put("accountID", accountID);
        }
        //获取最近的排名时间
        String uniqueCode = accountInfoDao.selectOne("getLatelyTime",params);
        if(uniqueCode != null && !"".equals(uniqueCode)){
            params.put("uniqueCode",uniqueCode);
            return accountInfoDao.selectList("getFansRanking",params);
        }
        return null;
    }

    @Override
    public List<Map<String,Object>> getPresentOfAccountID(JqgridPageList pageList,String accountID) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        if (accountID != null && !"".equals(accountID)) {
            params.put("accountID", accountID);
        }
        List<Map<String,Object>> resultList = accountInfoDao.selectList("getPresentOfAccountID", params);
        int num = (Integer)accountInfoDao.selectOne("getPresentOfAccountIDNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public List<FansSingle> getFansSingleList(JqgridPageList pageList,String nickName,String mobile) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        if (nickName != null && !"".equals(nickName)) {
            params.put("nickName", nickName);
        }
        if (mobile != null && !"".equals(mobile)) {
            params.put("mobile", mobile);
        }
        List<FansSingle> resultList = accountInfoDao.selectList("getFansSingleList",params);
        int num = (Integer)accountInfoDao.selectOne("getFansSingleListNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public List<Map<String,Object>> rewardPresentOfAccountID(JqgridPageList pageList,String accountID) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        if (accountID != null && !"".equals(accountID)) {
            params.put("accountID", accountID);
        }
        List<Map<String,Object>> resultList = accountInfoDao.selectList("rewardPresentOfAccountID", params);
        int num = (Integer)accountInfoDao.selectOne("rewardPresentOfAccountIDNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public List<Map<String, Object>> rewardRankingOfAccountID(String accountID) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (accountID != null && !"".equals(accountID)) {
            params.put("accountID", accountID);
        }
        return accountInfoDao.selectList("rewardRankingOfAccountID",params);
    }

    @Override
    public int getReplyNum() {
        int num = 0;
        if(accountInfoDao.selectOne("getReplyNum") != null){
            num = (Integer)accountInfoDao.selectOne("getReplyNum");
        }
        return num;
    }

    @Override
    public int getRewardMoney() {
        int num = 0;
        if(accountInfoDao.selectOne("getRewardMoney") != null){
            num = (Integer)accountInfoDao.selectOne("getRewardMoney");
        }
        return num;
    }

    @Override
    public int getOutOfBoxNum() {
        int num = 0;
        if(accountInfoDao.selectOne("getOutOfBoxNum") != null){
            num = (Integer)accountInfoDao.selectOne("getOutOfBoxNum");
        }
        return num;
    }

//    @Override
//    public int getPresentReceiveNum() {
//        int num = 0;
//        if(accountInfoDao.selectOne("getPresentReceiveNum") != null){
//            num = (Integer)accountInfoDao.selectOne("getPresentReceiveNum");
//        }
//        return num;
//    }
//
//    @Override
//    public int getPresentReceiveRate() {
//        int num = 0;
//        if(accountInfoDao.selectOne("getPresentReceiveRate") != null){
//            num = (Integer)accountInfoDao.selectOne("getPresentReceiveRate");
//        }
//        return num;
//    }
//
//    @Override
//    public int getOutOfBoxReceiveNum() {
//        int num = 0;
//        if(accountInfoDao.selectOne("getOutOfBoxReceiveNum") != null){
//            num = (Integer)accountInfoDao.selectOne("getOutOfBoxReceiveNum");
//        }
//        return num;
//    }
//
//    @Override
//    public int getOutOfBoxReceiveRate() {
//        int num = 0;
//        if(accountInfoDao.selectOne("getOutOfBoxReceiveRate") != null){
//            num = (Integer)accountInfoDao.selectOne("getOutOfBoxReceiveRate");
//        }
//        return num;
//    }

    @Override
    public int getSendAnchorNum() {
        int num = 0;
        if(accountInfoDao.selectOne("getSendAnchorNum") != null){
            num = (Integer)accountInfoDao.selectOne("getSendAnchorNum");
        }
        return num;
    }

    @Override
    public int getDeliveryOrderNum() {
        int num = 0;
        if(accountInfoDao.selectOne("getDeliveryOrderNum") != null){
            num = (Integer)accountInfoDao.selectOne("getDeliveryOrderNum");
        }
        return num;
    }

    @Override
    public List<Map<String,Object>> presentDataCount(JqgridPageList pageList,String name){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        if(name != null && !"".equals(name)){
            params.put("name",name);
        }
        List<Map<String,Object>> resultList = accountInfoDao.selectList("presentDataCount", params);
        int num = (Integer)accountInfoDao.selectOne("presentDataCountNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public List<Map<String, Object>> openBoxReceiveCount(JqgridPageList pageList, String name) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        if(name != null && !"".equals(name)){
            params.put("name",name);
        }
        List<Map<String,Object>> resultList = accountInfoDao.selectList("openBoxReceiveCount", params);
        int num = (Integer)accountInfoDao.selectOne("openBoxReceiveCountNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public List<Map<String, Object>> presentReceiveCount(JqgridPageList pageList, String name) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        if(name != null && !"".equals(name)){
            params.put("name",name);
        }
        List<Map<String,Object>> resultList = accountInfoDao.selectList("presentReceiveCount", params);
        int num = (Integer)accountInfoDao.selectOne("presentReceiveCountNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public String getAccountIDInfoIsAccountID(int id) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id",id);
        return accountInfoDao.selectOne("getAccountIDInfoIsAccountID",params);
    }

    @Override
    public String getListenerAccountIsAccountID(int id) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id",id);
        return accountInfoDao.selectOne("getListenerAccountIsAccountID",params);
    }

}
