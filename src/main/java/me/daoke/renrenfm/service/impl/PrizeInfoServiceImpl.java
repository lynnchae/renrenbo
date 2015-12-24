package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.exception.ServiceException;
import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.IPrizeInfoDao;
import me.daoke.renrenfm.service.IPrizeInfoService;
import me.daoke.renrenfm.vo.BaseEntity;
import me.daoke.renrenfm.vo.PresentInfo;
import me.daoke.renrenfm.vo.PrizeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/1.
 */
@Service
public class PrizeInfoServiceImpl implements IPrizeInfoService{

    @Autowired
    private IPrizeInfoDao prizeInfoDao;

    /**
     * 查询实体礼物列表
     * @param pageList
     * @param name
     *      礼物名称
     * @return
     */
    @Override
    public List<PrizeInfo> getPrizeInfoList(JqgridPageList pageList,String name){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid",PrizeInfo.ISVALID.VALID);
        if(name != null && !"".equals(name)){
            params.put("name",name);
        }
        List<PrizeInfo> resultList = prizeInfoDao.selectList("getPrizeInfoList", params);
        int num = (Integer)prizeInfoDao.selectOne("getPrizeInfoNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    /**
     * 查询盒子中是否还有奖品，并返回箱子编码
     * @param prizeIDs
     * @return
     */
    public String isExistInBox(String prizeIDs){
        Map<String,Object> params = new HashMap<String, Object>();
        String[] idStr = prizeIDs.split(",");
        int ids[] = new int[idStr.length];
        for (int i=0;i<idStr.length;i++){
            ids[i] = Integer.parseInt(idStr[i]);
        }
        params.put("prizeIDs",ids);
        params.put("isValid", PrizeInfo.ISVALID.VALID);
        String prizeCode = prizeInfoDao.selectOne("isExistInBox",params);
        return prizeCode;
    }

    /**
     * 删除礼物
     * @param prizeIDs
     *          礼物ID，多个用，隔开
     * @return
     */
    @Override
    @Transactional
    public boolean deletePrizeInfo(String prizeIDs){
        Map<String,Object> params = new HashMap<String, Object>();
        String[] idStr = prizeIDs.split(",");
        int ids[] = new int[idStr.length];
        for (int i=0;i<idStr.length;i++){
            ids[i] = Integer.parseInt(idStr[i]);
        }
        params.put("prizeIDs",ids);
        params.put("isValid", PresentInfo.ISVALID.INVALID);
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int num = (Integer)prizeInfoDao.update("deletePrizeInfo",params);
        if(num != idStr.length){
            throw new ServiceException();
        }
        return num == idStr.length ? true : false;
    }


    /**
     * 修改奖品信息
     * @param params
     * @return
     */
    @Override
    @Transactional
    public boolean editPrizeInfo(Map<String,Object> params){
        //先删除之前的记录
        String id = params.get("prizeID").toString();
        boolean flag = this.deletePrizeInfo(id);
        if(flag){
            //再新增一条记录
            Date currentDate = new Date();
            params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            SimpleDateFormat sf = new SimpleDateFormat(AbDateUtil.dateFormatYMDHMS);
            String createTime = params.get("createTime").toString();
            params.put("isValid", PrizeInfo.ISVALID.VALID);
            try {
                Date date = sf.parse(createTime);
                params.put("createTime", AbDateUtil.getConfirmDateTimeOfDay(date));
            } catch (Exception e) {
                e.printStackTrace();
            }
            flag = (Integer)prizeInfoDao.update("addPrizeInfo",params) > 0;
        }else {
            throw new ServiceException();
        }
        return flag;
    }

    /**
     * 新增奖品信息
     * @param params
     * @return
     */
    public boolean addPrizeInfo(Map<String,Object> params){
        try{
            Date currentDate = new Date();
            params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            params.put("createTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            params.put("isValid", PrizeInfo.ISVALID.VALID);
            int num = (Integer)prizeInfoDao.update("addPrizeInfo",params);
            return num > 0 ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断编码是否已经使用，已使用返回true,为使用返回false
     * @param prizeCode
     *        奖品编码
     * @return
     */
    public boolean judgeCodeIsExist(String prizeCode){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("prizeCode",prizeCode);
        params.put("isValid",PrizeInfo.ISVALID.VALID);
        int num = (Integer)prizeInfoDao.selectOne("getPrizeCode",params);
        return num > 0 ? true : false;
    }

    /**
     * 根据盒子ID查询盒子中的奖品
     * @param boxID
     *      盒子ID
     * @return
     */
    @Override
    public List<Map<String,Object>> getPrizeInfoInBox(int boxID){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("isValid",PrizeInfo.ISVALID.VALID);
        params.put("boxID",boxID);
        List<Map<String,Object>> resultList = prizeInfoDao.selectList("getPrizeInfoInBox", params);
        return resultList;
    }

    /**
     * 查询不在箱子中的奖品
     * @param boxCode
     *      盒子编码
     * @return
     */
    @Override
    public List<Map<String,Object>> getPrizeInfoNotInBox(String boxCode){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("isValid",PrizeInfo.ISVALID.VALID);
        params.put("boxCode",boxCode);
        List<Map<String,Object>> resultList = prizeInfoDao.selectList("getPrizeInfoNotInBox", params);
        return resultList;
    }

    /**
     * 根据奖品ID查奖品
     * @param prizeId
     * @return
     */
    public PrizeInfo getPrizeById(int prizeId){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("isValid",PrizeInfo.ISVALID.VALID);
        params.put("prizeID",prizeId);
        PrizeInfo prizeInfo = (PrizeInfo)prizeInfoDao.selectOne("getPrizeInfoById",params);
        return prizeInfo;
    }
}
