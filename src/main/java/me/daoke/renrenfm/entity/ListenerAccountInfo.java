package me.daoke.renrenfm.entity;

import java.util.List;

/**
 * 听众用户信息 实体类
 * @author zhuosh
 * @date 2015/6/15
 */
public class ListenerAccountInfo extends BaseEntity{

    /***主键*/
    private int id;

    /***用户ID*/
    private String accountID;

    /***用户性别*/
    private String sex;

    /***用户昵称*/
    private String nickName;

    /***用户头像*/
    private String headPic;

    /***用户车类型*/
    private int carType;


    /***用户的密点数*/
    private long denseNum;

    /***用户车牌号*/
    private String carNo;

    /***关注的主播ID列表*/
    private List<String> attentionAnchorIDList;

    /***当前正在收听的主播列表*/
    private List<String> listeningAnchorIDList;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public long getDenseNum() {
        return denseNum;
    }

    public void setDenseNum(long denseNum) {
        this.denseNum = denseNum;
    }

    public List<String> getAttentionAnchorIDList() {
        return attentionAnchorIDList;
    }

    public void setAttentionAnchorIDList(List<String> attentionAnchorIDList) {
        this.attentionAnchorIDList = attentionAnchorIDList;
    }

    public List<String> getListeningAnchorIDList() {
        return listeningAnchorIDList;
    }

    public void setListeningAnchorIDList(List<String> listeningAnchorIDList) {
        this.listeningAnchorIDList = listeningAnchorIDList;
    }

    @Override
    public String toString() {
        return "ListenerAccountInfo{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", sex='" + sex + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headPic='" + headPic + '\'' +
                ", carType=" + carType +
                ", denseNum=" + denseNum +
                ", carNo='" + carNo + '\'' +
                ", attentionAnchorIDList=" + attentionAnchorIDList +
                ", listeningAnchorIDList=" + listeningAnchorIDList +
                '}';
    }
}
