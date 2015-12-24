package me.daoke.renrenfm.vo;

/**
 * 单个主播数据统计
 * Created by zhaoym on 2015/9/7.
 */
public class SingleAnchor {

    /**
     * 主键
     */
    private  int id;

    /**
     * 主播名
     */
    private String anchorName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 主播的accountID
     */
    private String accountID;

    /**
     * 粉丝数
     */
    private String fanNum;

    /**
     * 人气数
     */
    private String popNum;

    /**
     * 财富值
     */
    private int wealthVal;

    /**
     * 总财富值
     */
    private int totalData;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 排名
     */
    private int ranking;

    /**
     * 发送消息数
     */
    private int sendMessageNum;

    /**
     * 转发消息数(微密)
     */
    private int foMeWemeNum;

    /**
     * 转发消息数(FM)
     */
    private int foMeFmNum;

    /**
     * 回复数
     */
    private int replyNum;

    /**
     * 粉丝活跃度
     */
    private int fanActivity;

    /**
     * 粉丝留存
     */
    private int fansRetention;

    /**
     * 覆盖人次
     */
    private int coverPerson;

    /**
     * 播出时长
     */
    private int broadcastTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFanNum() {
        return fanNum;
    }

    public void setFanNum(String fanNum) {
        this.fanNum = fanNum;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public String getPopNum() {
        return popNum;
    }

    public void setPopNum(String popNum) {
        this.popNum = popNum;
    }

    public int getWealthVal() {
        return wealthVal;
    }

    public void setWealthVal(int wealthVal) {
        this.wealthVal = wealthVal;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getSendMessageNum() {
        return sendMessageNum;
    }

    public void setSendMessageNum(int sendMessageNum) {
        this.sendMessageNum = sendMessageNum;
    }

    public int getFoMeWemeNum() {
        return foMeWemeNum;
    }

    public void setFoMeWemeNum(int foMeWemeNum) {
        this.foMeWemeNum = foMeWemeNum;
    }

    public int getFoMeFmNum() {
        return foMeFmNum;
    }

    public void setFoMeFmNum(int foMeFmNum) {
        this.foMeFmNum = foMeFmNum;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getFanActivity() {
        return fanActivity;
    }

    public void setFanActivity(int fanActivity) {
        this.fanActivity = fanActivity;
    }

    public int getFansRetention() {
        return fansRetention;
    }

    public void setFansRetention(int fansRetention) {
        this.fansRetention = fansRetention;
    }

    public int getCoverPerson() {
        return coverPerson;
    }

    public void setCoverPerson(int coverPerson) {
        this.coverPerson = coverPerson;
    }

    public int getBroadcastTime() {
        return broadcastTime;
    }

    public void setBroadcastTime(int broadcastTime) {
        this.broadcastTime = broadcastTime;
    }

    @Override
    public String toString() {
        return "SingleAnchor{" +
                "id=" + id +
                ", anchorName='" + anchorName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", accountID='" + accountID + '\'' +
                ", fanNum='" + fanNum + '\'' +
                ", popNum='" + popNum + '\'' +
                ", wealthVal=" + wealthVal +
                ", totalData=" + totalData +
                ", mobile='" + mobile + '\'' +
                ", ranking=" + ranking +
                ", sendMessageNum=" + sendMessageNum +
                ", foMeWemeNum=" + foMeWemeNum +
                ", foMeFmNum=" + foMeFmNum +
                ", replyNum=" + replyNum +
                ", fanActivity=" + fanActivity +
                ", fansRetention=" + fansRetention +
                ", coverPerson=" + coverPerson +
                ", broadcastTime=" + broadcastTime +
                '}';
    }

}