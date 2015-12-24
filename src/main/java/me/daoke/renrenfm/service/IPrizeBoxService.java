package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.vo.PrizeBox;

import java.util.List;
import java.util.Map;

/**
 * Created by liyanqin on 2015/8/6.
 */
public interface IPrizeBoxService {

    /**
     * 查询箱子列表
     * @param pageList
     * @param name
     * @return
     */
    public List<PrizeBox> getBoxList(JqgridPageList pageList,String name);

    /**
     * 查询盒子中是否还有奖品，并返回箱子编码
     * @param boxIDs
     * @return
     */
    public String isExistPrize(String boxIDs);

    /**
     * 删除宝箱
     * @param boxIDs
     * @return
     */
    public boolean deletePrizeBox(String boxIDs);

    /**
     * 修改箱子信息
     * @param params
     * @return
     */
    public boolean editBoxInfo(Map<String,Object> params);

    /**
     * @param boxCode
     *        箱子编码
     * @return
     */
    public boolean judgeBoxCodeIsExist(String boxCode);

    /**
     * 新增箱子信息
     * @param params
     * @return
     */
    public boolean addBoxInfo(Map<String,Object> params);

    /**
     * 根据箱子ID查箱子
     * @param boxId
     * @return
     */
    public PrizeBox queryBoxByID(int boxId);

    /**
     * 删除宝箱中的奖品
     * @param mappingIds
     * @return
     */
    public boolean deletePrizeInBox(String mappingIds);

    /**
     * @param mappingId
     *      映射关系ID
     * @param percent
     *      修改奖品的百分比
     * @return
     */
    public boolean editPrizePercent(int mappingId,float percent);

    /**
     * 往箱子中添加奖品
     * @param boxCode
     * @param prizeCode
     * @return
     */
    public boolean addPrize2Box(String boxCode,String prizeCode);
}
