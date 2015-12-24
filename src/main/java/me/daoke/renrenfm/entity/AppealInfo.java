package me.daoke.renrenfm.entity;

/**
 * 申诉表
 * Created by zhaoym on 2015/9/12.
 */
public class AppealInfo extends BaseEntity{

    /**
     * 主键
     */
    private int id;

    /**
     * 申诉人员对应的ID
     */
    private String accountID;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 申诉人
     */
    private String complainantName;

    /**
     * 申诉原因
     */
    private String reason;

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getComplainantName() {
        return complainantName;
    }

    public void setComplainantName(String complainantName) {
        this.complainantName = complainantName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "AppealInfo{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", idCard='" + idCard + '\'' +
                ", complainantName='" + complainantName + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

}
