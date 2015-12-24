package me.daoke.renrenfm.vo;

import java.util.Date;

/**
 * 用户列表
 * @author zhoaym
 * @date 2015/8/6
 */
public class AccountInfo extends BaseEntity{
    /**
     * 主键
     */
    private int id;

    /**
     * 对应公司内部系统用户ID
     */
    private String accountID;

    /***主播的标签*/
    private String remark;

    /**
     * 用户的昵称
     */
    public String nickName;

    /**
     * 用户的真实姓名
     */
    public String realName;

    /**
     * 身份证号
     */
    public String idCard;

    /**
     * 用户手机号
     */
    public String mobile;

    /**
     * 用户性别
     */
    public String sex;

    /**
     * 用户头像图片路径
     */
    public String headPic;

    /**
     *主播状态
     */
    public int status;

    /***
     *主播排行
     */
    public int rankNum;

    /**
     * 是否允许播音
     */
    public int isVoice;

    /**
     * 城市名称
     */
    public String cityName;

    /**
     * 钻的财富值
     */
    public int diamondWealthVal;

    /**
     * 用户钻数
     */
    public int diamond;

    /**
     * 主播人气值
     */
    public int popularityVal;

    public AccountInfo() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getSex() {
        return sex;
    }

    public int getRankNum() {
        return rankNum;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCityName() { return cityName; }

    public void setCityName(String cityName) { this.cityName = cityName; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getRealName() {
        return realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIsVoice() {
        return isVoice;
    }

    public void setIsVoice(int isVoice) {
        this.isVoice = isVoice;
    }

    public int getDiamondWealthVal() {
        return diamondWealthVal;
    }

    public void setDiamondWealthVal(int diamondWealthVal) {
        this.diamondWealthVal = diamondWealthVal;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getPopularityVal() {
        return popularityVal;
    }

    public void setPopularityVal(int popularityVal) {
        this.popularityVal = popularityVal;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", remark='" + remark + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex='" + sex + '\'' +
                ", headPic='" + headPic + '\'' +
                ", status=" + status +
                ", rankNum=" + rankNum +
                ", isVoice=" + isVoice +
                ", cityName='" + cityName + '\'' +
                ", diamondWealthVal=" + diamondWealthVal +
                ", diamond=" + diamond +
                ", popularityVal=" + popularityVal +
                '}';
    }

}
