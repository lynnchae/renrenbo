package me.daoke.renrenfm.entity;

import java.util.Date;

/**
 * Created by zhaoym on 2015/9/15.
 */
public class NoPlay extends BaseEntity {

    /**
     * 主键
     */
    private int id;

    /**
     * 禁播人员对应的ID
     */
    private String accountID;

    /**
     * 禁播前状态  0 不申请审核  1表示申请审核  2表示审核中  3表示审核成功   4审核失败
     */
    private String formerStatus;

    /**
     * 禁播时间
     */
    private Date noPlayTime;

    /**
     * 持续时间 (单位 : 小时)
     */
    private int duration;

    /**
     * 禁播原因
     */
    private String  reason;

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

    public String getFormerStatus() {
        return formerStatus;
    }

    public void setFormerStatus(String formerStatus) {
        this.formerStatus = formerStatus;
    }

    public Date getNoPlayTime() {
        return noPlayTime;
    }

    public void setNoPlayTime(Date noPlayTime) {
        this.noPlayTime = noPlayTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "NoPlay{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", formerStatus=" + formerStatus +
                ", noPlayTime=" + noPlayTime +
                ", duration=" + duration +
                ", reason='" + reason + '\'' +
                '}';
    }

}
