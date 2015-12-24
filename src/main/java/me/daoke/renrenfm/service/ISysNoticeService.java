package me.daoke.renrenfm.service;

import me.daoke.renrenfm.entity.SysNotice;

import java.util.List;

/**
 * 查询系统公告列表 service层的接口
 * @author zhuosh
 * @date 2015/8/9
 */
public interface ISysNoticeService {

    /**
     * 查询所有的公告信息
     * @return
     */
    public List<SysNotice> queryAllSysNotice();


    /**
     * 根据id查询系统公告
     * @param id
     * @return
     */
    public SysNotice querySysNoticeByRemark(int id);


    /**
     * 更新指定的系统公告模块
     * @param sysNotice
     */
    public int updateSysNoticeByRemark(SysNotice sysNotice);


    public int addSysNotice(SysNotice sysNotice);


      /**
     * 设置指定的系统信息
     * 如果删除做
     * @param sysNotice
     * @return
     */
    public int setSysNoticeInfo(SysNotice sysNotice);


}
