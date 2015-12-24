package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.dao.ISysNoticeDao;
import me.daoke.renrenfm.entity.SysNotice;
import me.daoke.renrenfm.service.ISysNoticeService;
import me.daoke.renrenfm.util.AbStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * 系统公告sevice的实现层
 * @author zhuosh
 * @date 2015/8/9
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService{

    /***系统公告dao层对象*/
    @Autowired
    private ISysNoticeDao sysNoticeDao;

    @Override
    public List<SysNotice> queryAllSysNotice() {
        return sysNoticeDao.selectList("queryAllNotice",null);
    }

    @Override
    public SysNotice querySysNoticeByRemark(int id) {
        Map map = new HashMap<String,Object>();
        map.put("remark",id);
        return sysNoticeDao.selectOne("querySysNoticeByRemark",map);
    }

    @Override
    public int updateSysNoticeByRemark(SysNotice sysNotice) {
        Map map = new HashMap<String,Object>();
        if(!AbStrUtil.isEmpty(sysNotice.getIcon())){
            map.put("icon",sysNotice.getIcon());
        }
        map.put("title",sysNotice.getTitle());
        map.put("url",sysNotice.getUrl());
        map.put("brief",sysNotice.getBrief());
        map.put("remark",sysNotice.getRemark());
        map.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(new Date()));
        return sysNoticeDao.update("updateSysNoticeByID", map);
    }

    @Override
    public int addSysNotice(SysNotice sysNotice) {
        Map map = new HashMap<String,Object>();
        map.put("showOrder",sysNotice.getShowOrder());
        map.put("isValid",1);
        map.put("icon",sysNotice.getIcon());
        map.put("type",sysNotice.getType());
        map.put("createTime",AbDateUtil.getConfirmDateTimeOfDay(new Date()));
        map.put("title",sysNotice.getTitle());
        map.put("url",sysNotice.getUrl());
        map.put("brief",sysNotice.getBrief());
        map.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(new Date()));
        return sysNoticeDao.update("addSysNoticeByID", map);
    }

    @Override
    public int setSysNoticeInfo(SysNotice sysNotice) {
        //根据ID查询活动
        SysNotice oldSysNotice = querySysNoticeByRemark(sysNotice.getRemark());
        if(oldSysNotice == null){
            //做新增操作
            return addSysNotice(sysNotice);
        }else{
            return updateSysNoticeByRemark(sysNotice);
        }

    }
}
