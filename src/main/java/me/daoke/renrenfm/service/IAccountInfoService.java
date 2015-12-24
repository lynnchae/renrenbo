package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.page.PageList;
import me.daoke.renrenfm.entity.*;
import me.daoke.renrenfm.vo.AccountInfo;
import me.daoke.renrenfm.vo.FansSingle;
import me.daoke.renrenfm.vo.SingleAnchor;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaoym on 2015/8/6.
 */
public interface IAccountInfoService {

    /**
     * 查询主播列表
     * @param pageList
     * @param anchorStatus  主播状态
     * @param cityName 城市名称
     * @param mobile 手机号
     * @return
     */
    public List<AccountInfo> getAccountInfoList(JqgridPageList pageList, String anchorStatus, String cityName,String mobile);

    /**
     * 主播审核提交
     * @param params
     * @return
     */
    public boolean submitAuditAccountInfo(Map<String, Object> params);

    /**
     * 查询举报列表
     * @param pageList
     * @param reportName 举报人
     * @param beReportName 被举报人
     * @param idCard 身份证号
     * @return
     */
    public List<AccusationRecordInfo> getAccusationInfoList(JqgridPageList pageList, String reportName, String beReportName,String idCard);

    /**
     * 添加禁播数据
     * @param formerStatus 禁播前状态
     * @param beAccountID 被禁播人员ID
     * @param reason 禁播原因
     * @param durationLong 禁播时长
     * @return
     */
    public boolean addNoPlay(String formerStatus,String beAccountID,String reason,String durationLong);

    /**
     * 更新主播的状态（禁播）
     * @param status
     * @param beAccountID
     * @return
     */
    public boolean updateAccountInfoOfIsVoice(String status,String beAccountID);

    /**
     * 查询申诉列表
     * @param pageList
     * @param appealName 申诉人
     * @param idCard 身份证号
     * @return
     */
    public List<AppealInfo> getAppealInfoList(JqgridPageList pageList, String appealName, String idCard);

    /**
     * 添加解禁数据
     * @param accountID 被解禁的人员ID
     * @param reason 备注信息
     * @param type 解禁方式: 0 人工解禁 1 系统解禁
     * @return
     */
    public boolean addRelievePlay(String accountID,String reason,String type);

    /**
     * 查询禁播之前的状态
     * @param accountID
     * @return
     */
    public String getNoPlayOfFormerStatus(String accountID);

    /**
     * 获取禁播数据列表
     * @return
     */
    public List<NoPlay> getNoPlayList();



    /**
     * 更新主播状态
     * @param accountID 更新主播对应的ID
     * @param isVoice 解禁
     * @param status 主播状态
     * @return
     */
    public boolean updateAccountInfoOfStatus(String accountID,String isVoice,String status);

    /**
     *查询所有主播发送的信息
     */
    public int getSendMessage();

    /**
     * 查询所有主播转发的信息(微密语音)
     * @return
     */
    public int getForwardMessageWeme();

    /**
     * 查询所有主播转发的信息(FM语音)
     * @return
     */
    public int getForwardMessageFm();

    /**
     * 查询所有主播粉丝回复数
     * @return
     */
    public int getFansReply();

    /**
     * 查询所有主播粉丝留存
     * @return
     */
    public int getFansRetention();

    /**
     * 查询所有主播覆盖人次
     * @return
     */
    public int getCoverPeople();

    /**
     * 查询所有主播播出时长
     * @return
     */
    public int getBroadcastTime();

    /**
     *查询所有主播播报热门城市
     * @return
     */
    public int getBroadcastPopularCities();

    /**
     * 单个主播情况数据统计
     * @param pageList
     * @param anchorName 主播姓名
     * @param idCard 身份证号
     * @return
     */
    public  List<SingleAnchor> getSingleAnchorList(JqgridPageList pageList,String anchorName,String idCard,String mobile);

    /**
     * 根据主播的accountID查询粉丝的贡献值
     * @param accountID
     * @return
     */
    public List<Map<String,Object>> getFansRanking(String accountID);

    /**
     * 根据主播的accountID查询获取的礼物信息
     * @param accountID
     * @return
     */
    public List<Map<String,Object>> getPresentOfAccountID(JqgridPageList pageList,String accountID);


    /**
     * 获取每个粉丝的数据统计
     * @param pageList
     * @param nickName 昵称
     * @param mobile 手机号
     * @return
     */
    public List<FansSingle> getFansSingleList(JqgridPageList pageList,String nickName,String mobile);

    /**
     * 根据听众的accountID查询打赏的礼物信息
     * @param pageList
     * @param accountID
     * @return
     */
    public List<Map<String,Object>> rewardPresentOfAccountID(JqgridPageList pageList,String accountID);

    /**
     * 根据听众的accountID查询打赏金额排名
     * @param accountID
     * @return
     */
    public List<Map<String,Object>> rewardRankingOfAccountID(String accountID);

    /**
     * 查询所有听众的回复消息
     * @return
     */
    public int getReplyNum();

    /**
     * 查询所有听众打赏的金额总数
     * @return
     */
    public int getRewardMoney();

    /**
     * 查询开箱数
     * @return
     */
    public int getOutOfBoxNum();

//    /**
//     * 查询礼品领取数
//     * @return
//     */
//    public int getPresentReceiveNum();

//    /**
//     * 查询礼品领取率
//     * @return
//     */
//    public int getPresentReceiveRate();
//
//    /**
//     * 查询开箱领取数
//     * @return
//     */
//    public int getOutOfBoxReceiveNum();
//
//    /**
//     * 查询开箱领取率
//     * @return
//     */
//    public int getOutOfBoxReceiveRate();

    /**
     * 查询送主播数
     * @return
     */
    public int getSendAnchorNum();

    /**
     * 查询发货订单数
     * @return
     */
    public int getDeliveryOrderNum();

    /**
     * 查询礼物的数据统计
     * @param pageList
     * @param name 礼物名称
     * @return
     */
    public List<Map<String,Object>> presentDataCount(JqgridPageList pageList,String name);

    /**
     * 开箱领取数据统计
     * @param pageList
     * @param name 箱子名称
     * @return
     */
    public List<Map<String,Object>> openBoxReceiveCount(JqgridPageList pageList,String name);

    /**
     * 礼品领取数据统计
     * @param pageList
     * @param name 礼品名称
     * @return
     */
    public List<Map<String,Object>> presentReceiveCount(JqgridPageList pageList,String name);

    /**
     * 根据用户信息的id获取用户的AccountID
     * @param id
     * @return
     */
    public String getAccountIDInfoIsAccountID(int id);

    /**
     * 根据听众用户的id查询听众用户的AccountID
     * @param id
     * @return
     */
    public String getListenerAccountIsAccountID(int id);


}
