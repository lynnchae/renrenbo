package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.dao.IRoadConditionPercentDao;
import me.daoke.renrenfm.entity.RoadConditionPercent;
import me.daoke.renrenfm.service.IRoadConditionPercentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 路况参数配置service的实现层
 * @author zhuosh
 * @date 2015/8/8
 */
@Service
public class RoadConditionPercentServiceImpl implements IRoadConditionPercentService{

    /***路况参数配置dao层对象*/
    @Autowired
    private IRoadConditionPercentDao roadConditionPercentDao;

    @Override
    public RoadConditionPercent queryConfigInfo() {
        return roadConditionPercentDao.selectOne("queryInfo",null);
    }

    @Override
    public int updateInfo(float slowPercent, float crowPercent) {
        Map map = new HashMap<String,Object>();
        map.put("slowPercent",slowPercent);
        map.put("crowPercent",crowPercent);
        return roadConditionPercentDao.update("updateInfo",map);
    }
}
