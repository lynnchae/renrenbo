package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.exception.ServiceException;
import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.IPrizeBoxDao;
import me.daoke.renrenfm.service.IPrizeBoxService;
import me.daoke.renrenfm.service.IPrizeInfoService;
import me.daoke.renrenfm.vo.PrizeBox;
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
 * Created by liyanqin on 2015/8/6.
 */
@Service
public class PrizeBoxServiceImpl implements IPrizeBoxService {

    @Autowired
    private IPrizeBoxDao prizeBoxDao;

    /**奖品sevice层**/
    @Autowired
    private IPrizeInfoService prizeInfoService;

    /**
     * 查询箱子列表
     * @param pageList
     * @param name
     * @return
     */
    public List<PrizeBox> getBoxList(JqgridPageList pageList,String name){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid", PrizeInfo.ISVALID.VALID);
        if(name != null && !"".equals(name)){
            params.put("boxName",name);
        }
        List<PrizeBox> resultList = prizeBoxDao.selectList("getPrizeBoxList",params);
        int num = (Integer)prizeBoxDao.selectOne("getPrizeBoxNum", params);
        pageList.setRecords(num);
        return resultList;
    }


    /**
     * 查询盒子中是否还有奖品，并返回箱子编码
     * @param boxIDs
     * @return
     */
    public String isExistPrize(String boxIDs){
        String[] idStr = boxIDs.split(",");
        int ids[] = new int[idStr.length];
        for (int i=0;i<idStr.length;i++){
            ids[i] = Integer.parseInt(idStr[i]);
        }
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("boxIDs",ids);
        params.put("isValid", PrizeBox.ISVALID.VALID);
        String boxCode = prizeBoxDao.selectOne("isExistPrize",params);
        return boxCode;
    }

    /**
     * 删除宝箱
     * @param boxIDs
     * @return
     */
    @Transactional
    public boolean deletePrizeBox(String boxIDs){
        String[] idStr = boxIDs.split(",");
        int ids[] = new int[idStr.length];
        for (int i=0;i<idStr.length;i++){
            ids[i] = Integer.parseInt(idStr[i]);
        }
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("boxIDs",ids);
        params.put("isValid", PrizeBox.ISVALID.INVALID);
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int num = (Integer)prizeBoxDao.update("deletePrizeBox",params);
        if(num != ids.length){
            throw new ServiceException();
        }
        return num == ids.length ? true : false;
    }

    /**
     * 修改奖品信息
     * @param params
     * @return
     */
    @Override
    @Transactional
    public boolean editBoxInfo(Map<String,Object> params){
        //先删除之前的记录
        String id = params.get("boxID").toString();
        boolean flag = this.deletePrizeBox(id);
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
            flag = (Integer)prizeBoxDao.update("addPrizeBox",params) > 0;
        }else {
            throw new ServiceException();
        }
        return flag;
    }

    /**
     * 判断编码是否已经使用，已使用返回true,为使用返回false
     * @param boxCode
     *        箱子编码
     * @return
     */
    public boolean judgeBoxCodeIsExist(String boxCode){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("boxCode",boxCode);
        params.put("isValid",PrizeInfo.ISVALID.VALID);
        int num = (Integer)prizeBoxDao.selectOne("judgeBoxCodeIsExist",params);
        return num > 0 ? true : false;
    }

    /**
     * 新增箱子信息
     * @param params
     * @return
     */
    public boolean addBoxInfo(Map<String,Object> params){
        try{
            Date currentDate = new Date();
            params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            params.put("createTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            params.put("isValid", PrizeBox.ISVALID.VALID);
            int num = (Integer)prizeBoxDao.update("addPrizeBox",params);
            return num > 0 ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据箱子ID查箱子
     * @param boxId
     * @return
     */
    public PrizeBox queryBoxByID(int boxId){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("boxId",boxId);
        params.put("isValid", PrizeBox.ISVALID.VALID);
        PrizeBox box = prizeBoxDao.selectOne("queryBoxByID",params);
        return box;
    }

    /**
     * 删除宝箱中的奖品
     * @param mappingIds
     * @return
     */
    @Transactional
    public boolean deletePrizeInBox(String mappingIds){
        Map<String,Object> params = new HashMap<String, Object>();
        String[] idStr = mappingIds.split(",");
        int ids[] = new int[idStr.length];
        for (int i=0;i<idStr.length;i++){
            ids[i] = Integer.parseInt(idStr[i]);
        }
        params.put("mappingIds", ids);
        params.put("isValid", PrizeBox.ISVALID.INVALID);
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int num = (Integer)prizeBoxDao.update("deletePrizeInBox",params);
        return num >0 ? true : false;
    }

    /**
     * 修改奖品的百分比
     * @param mappingId
     *      映射关系的ID
     * @param percent
     * @return
     */
    public boolean editPrizePercent(int mappingId,float percent){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("mappingId",mappingId);
        params.put("percent", percent);
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int num = (Integer)prizeBoxDao.update("editPrizePercent",params);
        return num >0 ? true : false;
    }

    /**
     * 往箱子中添加奖品
     * @param boxCode
     * @param prizeIDs
     * @return
     */
    @Transactional
    public boolean addPrize2Box(String boxCode,String prizeIDs){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("boxCode",boxCode);
        params.put("percent", 0);
        params.put("isValid", PrizeBox.ISVALID.VALID);
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        params.put("createTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        String[] idStr = prizeIDs.split(",");
        int num = 0;
        for(int i=0;i<idStr.length;i++){
            int prizeId = Integer.valueOf(idStr[i]);
            PrizeInfo prizeInfo = prizeInfoService.getPrizeById(prizeId);
            params.put("prizeCode",prizeInfo.getPrizeCode());
            int result = prizeBoxDao.update("addPrize2Box",params);
            if(result > 0){
                num ++;
            }
        }
        if(num != idStr.length){
            throw new ServiceException();
        }
        return num == idStr.length ? true : false;
    }
}
