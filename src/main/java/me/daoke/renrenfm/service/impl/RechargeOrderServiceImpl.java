package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.dao.IRechargeOrderDao;
import me.daoke.renrenfm.entity.RechargeOrder;
import me.daoke.renrenfm.service.IRechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户充值记录
 * Created by liyanqin on 2015/8/18.
 */
@Service
public class RechargeOrderServiceImpl implements IRechargeOrderService {

    @Autowired
    private IRechargeOrderDao rechargeOrderDao;

    /**
     * 查询所有的充值记录
     * @param pageList
     * @param orderStatus 订单状态
     * @param listenerName 用户昵称
     * @param accountNum 充值账号
     * @param mobile 手机号
     * @return
     */
    public List<Map<String,Object>> getAllRechargeOrder(JqgridPageList pageList,int orderStatus,String listenerName, String rechargeNum, String mobile){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid", RechargeOrder.ISVALID.VALID);
        if(orderStatus >= 0){
            params.put("orderStatus", orderStatus);
        }
        if(listenerName != null && !"".equals(listenerName)){
            params.put("nickName",listenerName);
        }
        if(rechargeNum != null && !"".equals(rechargeNum)){
            params.put("rechargeNum",rechargeNum);
        }
        if(mobile != null && !"".equals(mobile)){
            params.put("mobile",mobile);
        }
        List<Map<String,Object>> rechargeOrders = rechargeOrderDao.selectList("getAllRechargeOrder",params);
        int num = (Integer)rechargeOrderDao.selectOne("getAllRechargeOrderNum", params);
        pageList.setRecords(num);
        return rechargeOrders;
    }

    /**
     * 查询充值的回调记录
     * @param pageList
     * @param orderNo
     *          订单号
     * @param tradeStatus
     *          交易状态
     * @return
     */
    public List<Map<String,Object>> getAllPayBackRecord(JqgridPageList pageList,String orderNo,String tradeStatus){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid", RechargeOrder.ISVALID.VALID);
        if(orderNo != null && !"".equals(orderNo)){
            params.put("orderNo", orderNo);
        }
        if(tradeStatus != null && !"".equals(tradeStatus)){
            params.put("tradeStatus",tradeStatus.toUpperCase());
        }
        List<Map<String,Object>> rechargeOrders = rechargeOrderDao.selectList("getAllPayBackRecord",params);
        int num = (Integer)rechargeOrderDao.selectOne("getAllPayBackRecordNum", params);
        pageList.setRecords(num);
        return rechargeOrders;
    }
}
