package me.daoke.renrenfm.entity;

import java.math.BigDecimal;

/**
 * 用户充值记录表
 * @author zhuosh
 * @date 2015/7/7
 */
public class RechargeOrder extends BaseEntity{


    /***主键*/
    private int id;

    /***账户ID*/
    private String accountID;

    /***充值订单状态 0;未支付 1:支付中  2:已完成*/
    private int orderStatus;

    /***订单编号*/
    private String orderNo;

    /***密点数*/
    private int denseNum;

    /**
     * 支付类别 1 支付宝
     * */
    private int type;

    /***支付的总金额*/
    private BigDecimal totalMoney;

    public interface ORDER_STATUS{
        final int NOT_PAY = 0;//未支付
        final int PAYING = 1;//支付中
        final int PAYED = 2;//已完成
    }

    public interface TYPE{
       final int ALI_PAY = 1;
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

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getDenseNum() {
        return denseNum;
    }

    public void setDenseNum(int denseNum) {
        this.denseNum = denseNum;
    }

    @Override
    public String toString() {
        return "RechargeOrder{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderNo='" + orderNo + '\'' +
                ", denseNum=" + denseNum +
                ", type=" + type +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
