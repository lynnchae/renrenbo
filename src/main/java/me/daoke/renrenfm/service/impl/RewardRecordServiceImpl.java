package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.dao.IRewardRecordDao;
import me.daoke.renrenfm.entity.BaseEntity;
import me.daoke.renrenfm.service.IRewardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 打赏记录
 * Created by liyanqin on 2015/8/19.
 */
@Service
public class RewardRecordServiceImpl implements IRewardRecordService {

    @Autowired
    private IRewardRecordDao rewardRecordDao;

    /**
     * 查询打赏记录
     * @param pageList
     * @param anchorName
     *          主播的真实姓名或昵称
     * @param listenerName
     *          听众昵称
     * @return
     */
    public List<Map<String,Object>> getRewardRecord(JqgridPageList pageList, String anchorName,String listenerName,String presentName){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid", BaseEntity.ISVALID.VALID);
        if(anchorName != null && !"".equals(anchorName)){
            params.put("anchorName",anchorName);
        }
        if(listenerName != null && !"".equals(listenerName)){
            params.put("listenerName",listenerName);
        }
        if(presentName != null && !"".equals(presentName)){
            params.put("presentName",presentName);
        }
        List<Map<String,Object>> rewardRecords = rewardRecordDao.selectList("getRewardRecord",params);
        int num = (Integer)rewardRecordDao.selectOne("getRewardRecordNum", params);
        pageList.setRecords(num);
        return rewardRecords;
    }

}
