package me.daoke.renrenfm.vo;

/**
 * 单个粉丝的数据统计
 * Created by zhaoym on 2015/10/17.
 */
public class FansSingle {

    /**
     * 主键
     */
    private int id;

    /**
     * 听众的accountID
     */
    private String accountID;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 收听的主播语音条数
     */
    private int voiceAnchorNum;

    /**
     * 收听的系统语音条数
     */
    private int voiceSystemNum;

    /**
     * 回复数
     */
    private int replyNum;

    /**
     * 打赏的总金额
     */
    private int moneyNum;

    /**
     *
     */
    private int moneyRanking;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getVoiceAnchorNum() {
        return voiceAnchorNum;
    }

    public void setVoiceAnchorNum(int voiceAnchorNum) {
        this.voiceAnchorNum = voiceAnchorNum;
    }

    public int getVoiceSystemNum() {
        return voiceSystemNum;
    }

    public void setVoiceSystemNum(int voiceSystemNum) {
        this.voiceSystemNum = voiceSystemNum;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(int moneyNum) {
        this.moneyNum = moneyNum;
    }

    public int getMoneyRanking() {
        return moneyRanking;
    }

    public void setMoneyRanking(int moneyRanking) {
        this.moneyRanking = moneyRanking;
    }

    @Override
    public String toString() {
        return "FansSingle{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", voiceAnchorNum=" + voiceAnchorNum +
                ", voiceSystemNum=" + voiceSystemNum +
                ", replyNum=" + replyNum +
                ", moneyNum=" + moneyNum +
                ", moneyRanking=" + moneyRanking +
                '}';
    }

}
