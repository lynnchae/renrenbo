package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;

import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/18.
 */
public interface IRechargeOrderService {

    /**
     * 查询所有的充值记录
     * @param pageList
     * @param orderStatus 订单状态
     * @param listenerName 用户昵称
     * @param rechargeNum 充值账号
     * @param mobile 手机号
     * @return
     */
    public List<Map<String,Object>> getAllRechargeOrder(JqgridPageList pageList,int orderStatus,String listenerName,String rechargeNum, String mobile);

    /**
     * 查询充值的回调记录
     * @param pageList
     * @param orderNo
     *          订单号
     * @param tradeStatus
     *          交易状态
     * @return
     */
    public List<Map<String,Object>> getAllPayBackRecord(JqgridPageList pageList,String orderNo,String tradeStatus);
}
