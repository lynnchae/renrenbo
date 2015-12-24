package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.IVersionDao;
import me.daoke.renrenfm.entity.Version;
import me.daoke.renrenfm.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 版本信息Service
 * Created by zhaoym on 2015/8/12.
 */
@Service
public class VersionServiceImpl implements IVersionService {

    @Autowired
    private IVersionDao versionDao;

    @Override
    public Version getVersionFm(String appKey) {
        Map map = new HashMap<String,Object>();
        map.put("appKey",appKey);
        return versionDao.selectOne("getVersionFm",map);
    }

    @Override
    public Version getVersionRrb(String appKey) {
        Map map = new HashMap<String,Object>();
        map.put("appKey",appKey);
        return versionDao.selectOne("getVersionRrb",map);
    }

    @Override
    public Integer updateVersionFm(Map<String, Object> params) {
        Date currentDate = new Date();//添加更新时间
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        return versionDao.update("updateVersionFm",params);
    }

    @Override
    public Integer updateVersionRrb(Map<String, Object> params) {
        Date currentDate = new Date();//添加更新时间
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        return versionDao.update("updateVersionRrb",params);
    }

}
