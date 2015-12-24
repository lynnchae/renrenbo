package me.daoke.renrenfm.service.impl;


import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.IWemeDao;
import me.daoke.renrenfm.entity.BaseEntity;
import me.daoke.renrenfm.service.IWemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微密活动的service
 * Created by zhaoym on 2015/10/09.
 */
@Service
public class WemeServiceImpl implements IWemeService {

    @Autowired
    private IWemeDao wemeDao;

    @Override
    public List<Map<String, Object>> getWemeInfoList(JqgridPageList pageList, String mobile, String denseStatus) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid", BaseEntity.ISVALID.VALID);
        if(mobile != null && !"".equals(mobile)){
            params.put("mobile",mobile);
        }
        if(!"-1".equals(denseStatus) && !"".equals(denseStatus) && denseStatus != null){
            params.put("denseStatus",denseStatus);
        }
        List<Map<String,Object>> denseList = wemeDao.selectList("getWemeInfoList",params);
        int num = (Integer)wemeDao.selectOne("getWemeInfoListNum", params);
        pageList.setRecords(num);
        return denseList;
    }

    @Override
    public boolean updateWemeStatus(int id,int denseStatus,String reason) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id",id);
        params.put("denseStatus",denseStatus);
        params.put("reason",reason);
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int num = (Integer)wemeDao.update("updateWemeStatus",params);
        return num > 0;
    }

}
