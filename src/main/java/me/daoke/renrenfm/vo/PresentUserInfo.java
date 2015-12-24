package me.daoke.renrenfm.vo;

/**
 * 提现用户数据
 * Created by zhaoym on 2015/9/19.
 */
public class PresentUserInfo {

    /**
     * 主键
     */
    private int id;

    /**
     * 用户对应的ID
     */
    private String accountID;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 财富值
     */
    private int wealthVal;

    /**
     * 钻数
     */
    private int diamond;

    /**
     * 待提取金额
     */
    private double waitMoney;

    /**
     * 已选金额
     */
    private double selectedMoney;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getWealthVal() {
        return wealthVal;
    }

    public void setWealthVal(int wealthVal) {
        this.wealthVal = wealthVal;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }


    public double getWaitMoney() {
        return waitMoney;
    }

    public void setWaitMoney(double waitMoney) {
        this.waitMoney = waitMoney;
    }

    public double getSelectedMoney() {
        return selectedMoney;
    }

    public void setSelectedMoney(double selectedMoney) {
        this.selectedMoney = selectedMoney;
    }

    @Override
    public String toString() {
        return "PresentUserInfo{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", wealthVal=" + wealthVal +
                ", diamond=" + diamond +
                ", waitMoney=" + waitMoney +
                ", selectedMoney=" + selectedMoney +
                '}';
    }

}
