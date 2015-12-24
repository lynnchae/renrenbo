package me.daoke.renrenfm.entity;

/**
 * 提现记录
 * Created by zhaoym on 2015/9/9.
 */
public class WithdrawCash extends BaseEntity {

    /**
     * 主键
     */
    private int id;

    /**
     * 用户的姓名
     */
    private String nickName;

    /**
     * 交易码
     */
    private String tradeNumber;

    /**
     * 回执单号
     */
    private String receiptNumber;

    /**
     *支付宝账号
     */
    private String alipayAccount;

    /**
     *账户类型：1 支付宝
     */
    private int type;

    /**
     *提现金额（单位元）
     */
    private double cashNum;

    /**
     * 交易状态：1：待入账；2：入账失败；3：已入账
     */
    private int status;


    private String reason;

    public interface STATUS {
        final int WAIT_IN = 1;   //待入账
        final int FAILURE = 2;  //入账失败
        final int SUCCEED = 3; //已入账
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getCashNum() {
        return cashNum;
    }

    public void setCashNum(double cashNum) {
        this.cashNum = cashNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "WithdrawCash{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", tradeNumber='" + tradeNumber + '\'' +
                ", receiptNumber='" + receiptNumber + '\'' +
                ", alipayAccount='" + alipayAccount + '\'' +
                ", type=" + type +
                ", cashNum=" + cashNum +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                '}';
    }

}
