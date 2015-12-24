package me.daoke.renrenfm.service;

import java.util.Map;

/**
 * 请求记录service的接口层
 * @author zhaoym
 * @date 2015/8/28
 */
public interface IRequestRecordService {


    /**
     * 人人播查询新增用户(天)
     * @return
     */
    public int getNewRegisteredUserRrbDay();

    /**
     * 人人播查询新增用户(周)
     * @return
     */
    public int getNewRegisteredUserRrbWeek();

    /**
     * 人人播查询新增用户(月)
     * @return
     */
    public int getNewRegisteredUserRrbMonth();

    /**
     * FM查询新增用户(天)
     * @return
     */
    public int getNewRegisteredUserFmDay();

    /**
     * FM查询新增用户(周)
     * @return
     */
    public int getNewRegisteredUserFmWeek();

    /**
     * FM查询新增用户(月)
     * @return
     */
    public int getNewRegisteredUserFmMonth();

    /**
     * 人人播查询留存率(天)
     * @param key
     * @return
     */
    public int getRetentionRateRrbDay();

    /**
     * 人人播查询留存率(周)
     * @param key
     * @return
     */
    public int getRetentionRateRrbWeek();

    /**
     * 人人播查询留存率(月)
     * @param key
     * @return
     */
    public int getRetentionRateRrbMonth();

    /**
     * FM查询留存率(天)
     * @param key
     * @return
     */
    public int getRetentionRateAiFmDay();

    /**
     * FM查询留存率(周)
     * @param key
     * @return
     */
    public int getRetentionRateAiFmWeek();

    /**
     * FM查询留存率(月)
     * @param key
     * @return
     */
    public int getRetentionRateAiFmMonth();

    /**
     * FM查询留存率(天)
     * @param key
     * @return
     */
    public int getRetentionRatePcFmDay();

    /**
     * FM查询留存率(周)
     * @param key
     * @return
     */
    public int getRetentionRatePcFmWeek();

    /**
     * FM查询留存率(月)
     * @param key
     * @return
     */
    public int getRetentionRatePcFmMonth();

    /**
     * 人人播查询活跃用户(天)
     * @param key
     * @return
     */
    public int getActiveUserRrbDay();

    /**
     * 人人播查询活跃用户(周)
     * @param key
     * @return
     */
    public int getActiveUserRrbWeek();

    /**
     * 人人播查询活跃用户(月)
     * @param key
     * @return
     */
    public int getActiveUserRrbMonth();

    /**
     * FM查询活跃用户(天)
     * @param key
     * @return
     */
    public int getActiveUserFmDay();

    /**
     * FM查询活跃用户(周)
     * @param key
     * @return
     */
    public int getActiveUserFmWeek();

    /**
     * FM查询活跃用户(月)
     * @param key
     * @return
     */
    public int getActiveUserFmMonth();

    /**
     * 人人播查询总用户
     * @return
     */
    public int getOverallUserRrb();

    /**
     * FM查询总用户
     * @return
     */
    public int getOverallUserFm();

}
