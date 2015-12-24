package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.exception.ServiceException;
import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.IPresentInfoDao;
import me.daoke.renrenfm.service.IPresentInfoService;
import me.daoke.renrenfm.vo.BaseEntity;
import me.daoke.renrenfm.vo.PresentInfo;
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
public class PresentInfoServiceImpl implements IPresentInfoService{

    /**presentInfoDao**/
    @Autowired
    private IPresentInfoDao presentInfoDao;

    /**
     * 查询虚礼礼物列表
     * @param pageList
     * @param name
     *          姓名
     * @param type
     *          类型
     * @param appearType
     *         呈现方式
     * @return
     */
    @Override
    public List<PresentInfo> getPresentInfoList(JqgridPageList pageList,int type,String name,int appearType){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace",JqgridPageList.getStartOfPage(pageList.getPage(),pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid",PresentInfo.ISVALID.VALID);
        if(type == 0 || type ==1){
            params.put("type",type);
        }
        if(name != null && !"".equals(name)){
            params.put("name",name);
        }
        if(appearType != -1){
            params.put("appearType",appearType);
        }
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        List<PresentInfo> resultList = presentInfoDao.selectList("getPresentInfoList", params);
        int num = (Integer)presentInfoDao.selectOne("getPresentInfoNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    /**
     * 删除礼物
     * @param presentIDs
     *          礼物ID，多个用，隔开
     * @return
     */
    @Override
    @Transactional
    public boolean deletePresentInfo(String presentIDs){
        Map<String,Object> params = new HashMap<String, Object>();
        try{
            String[] idStr = presentIDs.split(",");
            int ids[] = new int[idStr.length];
            for (int i=0;i<idStr.length;i++){
                ids[i] = Integer.parseInt(idStr[i]);
            }
            params.put("presentIDs",ids);
            params.put("isValid",PresentInfo.ISVALID.INVALID);
            Date currentDate = new Date();
            params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            int num = (Integer)presentInfoDao.update("deletePresentInfo",params);
            if(num != idStr.length){
                throw new ServiceException();
            }
            return num == idStr.length ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改礼物
     * @param params
     * @return
     */
    @Override
    @Transactional
    public boolean editPresentInfo(Map<String,Object> params){
        try{
            //先删除数据
            String presentID = params.get("presentID").toString();
            boolean flag = this.deletePresentInfo(presentID);
            if(flag){
                //新增一条数据
                //再新增一条记录
                Date currentDate = new Date();
                params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
                SimpleDateFormat sf = new SimpleDateFormat(AbDateUtil.dateFormatYMDHMS);
                String createTime = params.get("createTime").toString();
                params.put("isValid", PresentInfo.ISVALID.VALID);
                try {
                    Date date = sf.parse(createTime);
                    params.put("createTime", AbDateUtil.getConfirmDateTimeOfDay(date));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int num = (Integer)presentInfoDao.update("addPresentInfo",params);
                return num > 0 ? true : false;
            }else{
                throw new ServiceException();
            }
            /*Date currentDate = new Date();
            params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            int num = (Integer)presentInfoDao.update("updatePresentInfo",params);
            return num > 0 ? true : false;*/
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加礼物
     * @param params
     * @return
     */
    @Override
    public boolean addPresentInfo(Map<String,Object> params){
        try{
            Date currentDate = new Date();
            params.put("createTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
            params.put("isValid",PresentInfo.ISVALID.VALID);
            params.put("inventory",0);
            int num = (Integer)presentInfoDao.update("addPresentInfo",params);
            return num > 0 ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
