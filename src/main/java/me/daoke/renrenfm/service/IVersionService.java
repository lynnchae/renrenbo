package me.daoke.renrenfm.service;

import me.daoke.renrenfm.entity.Version;

import java.util.Map;

/**
 * 版本信息Service
 * Created by zhaoym on 2015/8/12.
 */
public interface IVersionService {

    /**
     * 获取FM版本信息
     * @param appKey
     * @return
     */
    public Version getVersionFm(String appKey);

    /**
     * 获取人人播版本信息
     * @param appKey
     * @return
     */
    public Version getVersionRrb(String appKey);

    /**
     * 更新FM版本信息
     * @param params
     * @return
     */
    public Integer updateVersionFm(Map<String, Object> params);

    /**
     * 更新人人播版本信息
     * @param params
     * @return
     */
    public Integer updateVersionRrb(Map<String, Object> params);

}
