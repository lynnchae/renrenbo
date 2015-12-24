package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.ISysConfigDao;
import me.daoke.renrenfm.entity.BaseEntity;
import me.daoke.renrenfm.entity.SysConfig;
import me.daoke.renrenfm.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数配置
 * @author zhuosh
 * @date 2015/8/8
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    /***系统参数配置dao层对象*/
    @Autowired
    private ISysConfigDao configDao;

    @Override
    public SysConfig queryConfirmConfig(String flag) {
        Map map = new HashMap<String,Object>();
        map.put("flag",flag);
        return configDao.selectOne("queryConfirmSysConfig",map);
    }

    /**
     * 修改系统配置信息
     * @param params
     * @return
     */
    public int updateConfirmConfigInfo(Map<String,Object> params){
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        params.put("isValid", SysConfig.ISVALID.VALID);
        int num = configDao.update("updateConfirmConfigByFlag",params);
        return num;
    }
}
