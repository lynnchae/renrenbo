package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.ISendGoldDao;
import me.daoke.renrenfm.entity.BaseEntity;
import me.daoke.renrenfm.entity.RechargeOrder;
import me.daoke.renrenfm.service.ISendGoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 赠送金币活动
 * Created by liyanqin on 2015/8/19.
 */
@Service
public class SendGoldServiceImpl implements ISendGoldService {

    @Autowired
    private ISendGoldDao sendGoldDao;

    /**
     * 赠送金币领取列表
     * @param pageList
     * @param mobile
     *          手机号
     * @param isCollect
     *          是否领取
     * @return
     */
    public List<Map<String,Object>> sendGoldList(JqgridPageList pageList,String mobile,int isCollect){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid", BaseEntity.ISVALID.VALID);
        if(mobile != null && !"".equals(mobile)){
            params.put("mobile",mobile);
        }
        if(isCollect >= 0){
            params.put("isCollect",isCollect);
        }
        List<Map<String,Object>> sendGoldList = sendGoldDao.selectList("sendGoldList",params);
        int num = (Integer)sendGoldDao.selectOne("sendGoldListNum", params);
        pageList.setRecords(num);
        return sendGoldList;
    }

}
