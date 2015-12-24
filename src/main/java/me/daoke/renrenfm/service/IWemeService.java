package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;

import java.util.List;
import java.util.Map;

/**
 * 微密相关活动的Service
 * Created by zhaoym on 2015/10/09.
 */
public interface IWemeService {

    /**
     * 返回微密用户密点数列表信息
     * @param pageList
     * @param mobile 手机号
     * @param denseStatus 是否已返回
     * @return
     */
    public List<Map<String ,Object>> getWemeInfoList(JqgridPageList pageList,String mobile,String denseStatus);

    /**
     * 修改返回微密用户密点数状态
     * @param id
     * @param denseStatus 返回微密用户密点数状态
     * @param reason 备注信息
     * @return
     */
    public boolean updateWemeStatus(int id,int denseStatus,String reason);

}
