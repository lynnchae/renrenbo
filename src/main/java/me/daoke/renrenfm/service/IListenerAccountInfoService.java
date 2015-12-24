package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.entity.ListenerAccountInfo;

import java.util.List;

/**
 * Created by liyanqin on 2015/8/20.
 */
public interface IListenerAccountInfoService {

    /**
     * 获取所有用户信息
     * @param pageList
     * @param name
     * @return
     */
    public List<ListenerAccountInfo> getAllListener(JqgridPageList pageList,String name);



}
