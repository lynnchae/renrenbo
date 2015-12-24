package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.IListenerOrderDao;
import me.daoke.renrenfm.entity.BaseEntity;
import me.daoke.renrenfm.service.IListenerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开箱领奖品的记录
 * Created by liyanqin on 2015/8/19.
 */
@Service
public class ListenerOrderServiceImpl implements IListenerOrderService {

    @Autowired
    private IListenerOrderDao listenerOrderDao;

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
    public List<Map<String,Object>> getListenerOrderList(JqgridPageList pageList, String boxName,String prizeName,int status,int cancelType){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid", BaseEntity.ISVALID.VALID);
        if(boxName != null && !"".equals(boxName)){
            params.put("boxName",boxName);
        }
        if(prizeName != null && !"".equals(prizeName)){
            params.put("prizeName",prizeName);
        }
        if(status >= 0){
            params.put("status",status);
        }
        if(cancelType > 0){
            params.put("cancelType",cancelType);
        }
        List<Map<String,Object>> listenerOrders = listenerOrderDao.selectList("getListenerOrderList",params);
        int num = (Integer)listenerOrderDao.selectOne("getListenerOrderNum", params);
        pageList.setRecords(num);
        return listenerOrders;
    }

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    public Map<String,Object> queryOrderDetail(int orderId){
        Map<String,Object> orderDetail = listenerOrderDao.selectOne("queryOrderDetail",orderId);
        return orderDetail;
    }

    /**
     * 保存订单物流信息
     * @param orderId
     * @param sendCompany
     *          快递公司
     * @param sendNumber
     *          运单号
     * @return
     */
    public boolean saveSendMessage(int orderId,String sendCompany,String sendNumber){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("orderId",orderId);
        params.put("sendCompany",sendCompany);
        params.put("sendNumber",sendNumber);
        params.put("sendStatus",1);//已发货
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int result = listenerOrderDao.update("saveSendMessage",params);
        return result > 0 ? true :false;
    }
}

