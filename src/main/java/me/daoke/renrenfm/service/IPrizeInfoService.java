package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.vo.PrizeInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/1.
 */
public interface IPrizeInfoService {

    /**
     * 查询实体礼物列表
     * @param pageList
     * @param name
     *      礼物名称
     * @return
     */
    public List<PrizeInfo> getPrizeInfoList(JqgridPageList pageList,String name);

    /**
     * 查询盒子中是否还有奖品，并返回箱子编码
     * @param prizeIDs
     * @return
     */
    public String isExistInBox(String prizeIDs);

    /**
     * 删除礼物
     * @param prizeIDs
     *          礼物ID，多个用，隔开
     * @return
     */
    public boolean deletePrizeInfo(String prizeIDs);

    /**
     * 修改奖品信息
     * @param params
     * @return
     */
    public boolean editPrizeInfo(Map<String,Object> params);

    /**
     *
     * @param prizeCode
     *        奖品编码
     * @return
     */
    public boolean judgeCodeIsExist(String prizeCode);

    /**
     * 新增奖品信息
     * @param params
     * @return
     */
    public boolean addPrizeInfo(Map<String,Object> params);

    /**
     * 根据盒子ID查询盒子中的奖品
     * @param boxID
     *      盒子ID
     * @return
     */
    public List<Map<String,Object>> getPrizeInfoInBox(int boxID);

    /**
     * 查询不在箱子中的奖品
     * @param boxCode
     *      箱子编码
     * @return
     */
    public List<Map<String,Object>> getPrizeInfoNotInBox(String boxCode);

    /**
     * 根据奖品ID查奖品
     * @param prizeId
     * @return
     */
    public PrizeInfo getPrizeById(int prizeId);
}
