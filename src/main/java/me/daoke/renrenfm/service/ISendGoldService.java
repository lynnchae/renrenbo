package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;

import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/19.
 */
public interface ISendGoldService {

    /**
     * 赠送金币领取列表
     * @param pageList
     * @param mobile
     *          手机号
     * @param isCollect
     *          是否领取
     * @return
     */
    public List<Map<String,Object>> sendGoldList(JqgridPageList pageList,String mobile,int isCollect);

}
