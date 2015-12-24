package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;

import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/19.
 */
public interface IListenerOrderService {

    /**
     * 查询开箱记录
     * @param pageList
     * @param boxName
     *          箱子名称
     * @param prizeName
     *          奖品名称
     * @param status
     *          状态
     * @param cancelType
     *          领取方式
     * @return
     */
    public List<Map<String,Object>> getListenerOrderList(JqgridPageList pageList, String boxName,String prizeName,int status,int cancelType);

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    public Map<String,Object> queryOrderDetail(int orderId);

    /**
     * 保存订单物流信息
     * @param orderId
     * @param sendCompany
     *          快递公司
     * @param sendNumber
     *          运单号
     * @return
     */
    public boolean saveSendMessage(int orderId,String sendCompany,String sendNumber);
}
