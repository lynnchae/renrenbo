package me.daoke.renrenfm.service;

import me.daoke.renrenfm.entity.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * 系统配置参数
 * @author zhuosh
 * @date 2015/8/8
 */
public interface ISysConfigService {

    /**
     *查询指定类型的配置文件
     * @param flag
     *         类别
     */
    public SysConfig queryConfirmConfig(String flag);

    /**
     * 修改系统配置信息
     * @param params
     * @return
     */
    public int updateConfirmConfigInfo(Map<String,Object> params);

}
