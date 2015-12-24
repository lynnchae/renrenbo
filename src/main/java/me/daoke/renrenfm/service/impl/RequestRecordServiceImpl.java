package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.dao.IRequestRecordDao;
import me.daoke.renrenfm.service.IRequestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求记录service的实现层
 * @author zhaoym
 * @date 2015/8/28
 */
@Service
public class RequestRecordServiceImpl implements IRequestRecordService {

    @Autowired
    private IRequestRecordDao requestRecorDao;

    /**
     * 人人播查询新增用户(天)
     * @param key
     * @return
     */
    @Override
    public int getNewRegisteredUserRrbDay() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getNewRegisteredUserRrbDay") != null){
            num = (Integer)requestRecorDao.selectOne("getNewRegisteredUserRrbDay");
        }
        return num;
    }

    /**
     * 人人播查询新增用户(周)
     * @param key
     * @return
     */
    @Override
    public int getNewRegisteredUserRrbWeek() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getNewRegisteredUserRrbWeek") != null){
            num = (Integer)requestRecorDao.selectOne("getNewRegisteredUserRrbWeek");
        }
        return num;
    }

    /**
     * 人人播查询新增用户(月)
     * @param key
     * @return
     */
    @Override
    public int getNewRegisteredUserRrbMonth() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getNewRegisteredUserRrbMonth") != null){
            num = (Integer)requestRecorDao.selectOne("getNewRegisteredUserRrbMonth");
        }
        return num;
    }

    /**
     * FM查询新增用户(天)
     * @param key
     * @return
     */
    @Override
    public int getNewRegisteredUserFmDay() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getNewRegisteredUserFmDay") != null){
            num = (Integer)requestRecorDao.selectOne("getNewRegisteredUserFmDay");
        }
        return num;
    }

    /**
     * FM查询新增用户(周)
     * @param key
     * @return
     */
    @Override
    public int getNewRegisteredUserFmWeek() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getNewRegisteredUserFmWeek") != null){
            num = (Integer)requestRecorDao.selectOne("getNewRegisteredUserFmWeek");
        }
        return num;
    }

    /**
     * FM查询新增用户(月)
     * @param key
     * @return
     */
    @Override
    public int getNewRegisteredUserFmMonth() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getNewRegisteredUserFmMonth") != null){
            num = (Integer)requestRecorDao.selectOne("getNewRegisteredUserFmMonth");
        }
        return num;
    }


    /**
     * 人人播查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRateRrbDay() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getRetentionRateRrbDay") != null){
            num = (Integer)requestRecorDao.selectOne("getRetentionRateRrbDay");
        }
        return num;
    }

    /**
     * 人人播查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRateRrbWeek() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getRetentionRateRrbWeek") != null){
            num = (Integer)requestRecorDao.selectOne("getRetentionRateRrbWeek");
        }
        return num;
    }

    /**
     * 人人播查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRateRrbMonth() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getRetentionRateRrbMonth") != null){
            num = (Integer)requestRecorDao.selectOne("getRetentionRateRrbMonth");
        }
        return num;
    }

    /**
     * FM查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRateAiFmDay() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getRetentionRateAiFmDay") != null){
            num = (Integer)requestRecorDao.selectOne("getRetentionRateAiFmDay");
        }
        return num;
    }

    /**
     * FM查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRateAiFmWeek() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        return (Integer)requestRecorDao.selectOne("getRetentionRateAiFmWeek");
    }

    /**
     * FM查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRateAiFmMonth() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getRetentionRateAiFmMonth") != null){
            num = (Integer)requestRecorDao.selectOne("getRetentionRateAiFmMonth");
        }
        return num;
    }

    /**
     * FM查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRatePcFmDay() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getRetentionRatePcFmDay") != null){
            num = (Integer)requestRecorDao.selectOne("getRetentionRatePcFmDay");
        }
        return num;
    }

    /**
     * FM查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRatePcFmWeek() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getRetentionRatePcFmWeek") != null){
            num = (Integer)requestRecorDao.selectOne("getRetentionRatePcFmWeek");
        }
        return num;
    }

    /**
     * FM查询留存率
     * @param key
     * @return
     */
    @Override
    public int getRetentionRatePcFmMonth() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getRetentionRatePcFmMonth") != null){
            num = (Integer)requestRecorDao.selectOne("getRetentionRatePcFmMonth");
        }
        return num;
    }

    /**
     * 人人播查询活跃用户
     * @param key
     * @return
     */
    @Override
    public int getActiveUserRrbDay() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getActiveUserRrbDay") != null){
            num = (Integer)requestRecorDao.selectOne("getActiveUserRrbDay");
        }
        return num;
    }

    /**
     * 人人播查询活跃用户
     * @param key
     * @return
     */
    @Override
    public int getActiveUserRrbWeek() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getActiveUserRrbWeek") != null){
            num = (Integer)requestRecorDao.selectOne("getActiveUserRrbWeek");
        }
        return num;
    }

    /**
     * 人人播查询活跃用户
     * @param key
     * @return
     */
    @Override
    public int getActiveUserRrbMonth() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getActiveUserRrbMonth") != null){
            num = (Integer)requestRecorDao.selectOne("getActiveUserRrbMonth");
        }
        return num;
    }

    /**
     * FM查询活跃用户
     * @param key
     * @return
     */
    @Override
    public int getActiveUserFmDay() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getActiveUserFmDay") != null){
            num = (Integer)requestRecorDao.selectOne("getActiveUserFmDay");
        }
        return num;
    }

    /**
     * FM查询活跃用户
     * @param key
     * @return
     */
    @Override
    public int getActiveUserFmWeek() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getActiveUserFmWeek") != null){
            num = (Integer)requestRecorDao.selectOne("getActiveUserFmWeek");
        }
        return num;
    }

    /**
     * FM查询活跃用户
     * @param key
     * @return
     */
    @Override
    public int getActiveUserFmMonth() {
//        Map<String,Object> params = new HashMap<String, Object>();
//        params.put("key",key);
        int num = 0;
        if(requestRecorDao.selectOne("getActiveUserFmMonth") != null){
            num = (Integer)requestRecorDao.selectOne("getActiveUserFmMonth");
        }
        return num;
    }

    /**
     * 人人播查询总用户
     * @return
     */
    @Override
    public int getOverallUserRrb(){
        return (Integer)requestRecorDao.selectOne("getOverallUserRrb",null);
    }

    /**
     * FM查询总用户
     * @return
     */
    @Override
    public int getOverallUserFm(){
        return (Integer)requestRecorDao.selectOne("getOverallUserFm",null);
    }

}
