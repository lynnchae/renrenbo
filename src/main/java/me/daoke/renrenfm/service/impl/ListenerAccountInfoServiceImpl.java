package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.dao.IListenerAccountInfoDao;
import me.daoke.renrenfm.entity.ListenerAccountInfo;
import me.daoke.renrenfm.service.IListenerAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/20.
 */
@Service
public class ListenerAccountInfoServiceImpl implements IListenerAccountInfoService{

    @Autowired
    private IListenerAccountInfoDao listenerAccountInfoDao;

    /**
     * 获取所有用户信息
     * @param pageList
     * @param name
     * @return
     */
    public List<ListenerAccountInfo> getAllListener(JqgridPageList pageList,String name){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid", ListenerAccountInfo.ISVALID.VALID);
        if(name != null && !"".equals(name)){
            params.put("nickName",name);
        }
        List<ListenerAccountInfo> resultList = listenerAccountInfoDao.selectList("getAllListener",params);
        int num = (Integer)listenerAccountInfoDao.selectOne("getAllListenerNum", params);
        pageList.setRecords(num);
        return resultList;
    }
}
