package me.daoke.renrenfm.entity;

import java.util.Date;

/**
 * 举报信息
 * Created by zhaoym on 2015/9/11.
 */
public class AccusationRecordInfo extends BaseEntity{

    /**
     * 主键
     */
    private int id;

    /**
     * 举报人ID
     */
    private String accountID;

    /**
     * 举报人姓名
     */
    private String reportName;

    /**
     * 被举报人ID
     */
    private String beAccountID;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 主播状态
     */
    private String status;

    /**
     * 被举报人姓名
     */
    private String beReportName;

    /**
     * 举报的内容
     */
    private String accusationContent;

    /**
     * 吐槽的时间
     */
    private Date accusationTime;

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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getBeReportName() {
        return beReportName;
    }

    public void setBeReportName(String beReportName) {
        this.beReportName = beReportName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeAccountID() {
        return beAccountID;
    }

    public void setBeAccountID(String beAccountID) {
        this.beAccountID = beAccountID;
    }

    public String getAccusationContent() {
        return accusationContent;
    }

    public void setAccusationContent(String accusationContent) {
        this.accusationContent = accusationContent;
    }


    public Date getAccusationTime() {
        return accusationTime;
    }

    public void setAccusationTime(Date accusationTime) {
        this.accusationTime = accusationTime;
    }

    @Override
    public String toString() {
        return "AccusationRecordInfo{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", reportName='" + reportName + '\'' +
                ", beAccountID='" + beAccountID + '\'' +
                ", idCard='" + idCard + '\'' +
                ", status='" + status + '\'' +
                ", beReportName='" + beReportName + '\'' +
                ", accusationContent='" + accusationContent + '\'' +
                ", accusationTime=" + accusationTime +
                '}';
    }

}
