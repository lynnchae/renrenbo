package me.daoke.renrenfm.service;

import me.daoke.renrenfm.entity.RoadConditionPercent;

/**
 * 路况信息参数配置service层对象
 * @author zhuosh
 * @date 2015/8/8
 */
public interface IRoadConditionPercentService {


    /**
     * 查询路况的配置的信息
     * @return
     */
    public RoadConditionPercent queryConfigInfo();


    /**
     * 更新路况信息
     * @param crowPercent
     *         拥堵指标
     * @param slowPercent
     *         缓行指标
     */
    public int updateInfo(float slowPercent,float crowPercent);
}
