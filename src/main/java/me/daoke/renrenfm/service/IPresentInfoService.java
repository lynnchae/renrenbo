package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.vo.PresentInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/1.
 */
public interface IPresentInfoService {

    /**
     * 查询虚礼礼物列表
     * @param pageList
     * @return
     */
    public List<PresentInfo> getPresentInfoList(JqgridPageList pageList,int type,String name,int appearType);

    /**
     * 删除礼物
     * @param presentIDs
     *      礼物ID，多个用，隔开
     * @return
     */
    public boolean deletePresentInfo(String presentIDs);

    /**
     * 修改礼物
     * @param params
     * @return
     */
    public boolean editPresentInfo(Map<String,Object> params);

    /**
     * 添加礼物
     * @param params
     * @return
     */
    public boolean addPresentInfo(Map<String,Object> params);
}
