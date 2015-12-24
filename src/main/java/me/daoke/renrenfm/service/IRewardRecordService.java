package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;

import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/19.
 */
public interface IRewardRecordService {

    /**
     * 查询打赏记录
     * @param pageList
     * @param anchorName
     *          主播的真实姓名或昵称
     * @param listenerName
     *          听众昵称
     * @return
     */
    public List<Map<String,Object>> getRewardRecord(JqgridPageList pageList, String anchorName,String listenerName,String presentName);

}
