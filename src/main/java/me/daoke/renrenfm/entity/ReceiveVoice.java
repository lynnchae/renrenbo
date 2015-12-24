package me.daoke.renrenfm.entity;

/**
 * 接受语音消息表
 * @author zhaoym
 * @date 2015/8/26
 */
public class ReceiveVoice extends BaseEntity {

    /**
     * 主键
     */
    private int id;

    /**
     *发送语音的人
     */
    private String senderAccountID;

    /**
     * 接收语音的人
     */
    private String receiveAccountID;

    /**
     * 发送的内容
     */
    private String content;

    /**
     * 手机唯一标识码
     */
    private String phoneCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderAccountID() {
        return senderAccountID;
    }

    public void setSenderAccountID(String senderAccountID) {
        this.senderAccountID = senderAccountID;
    }

    public String getReceiveAccountID() {
        return receiveAccountID;
    }

    public void setReceiveAccountID(String receiveAccountID) {
        this.receiveAccountID = receiveAccountID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    @Override
    public String toString() {
        return "ReceiveVoice{" +
                "id=" + id +
                ", senderAccountID='" + senderAccountID + '\'' +
                ", receiveAccountID='" + receiveAccountID + '\'' +
                ", content='" + content + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                '}';
    }

}
