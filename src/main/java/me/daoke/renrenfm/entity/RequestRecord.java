package me.daoke.renrenfm.entity;

/**
 * 请求记录表
 * @author zhaoym
 * @date 2015/8/28
 */
public class RequestRecord extends BaseEntity{

    /**
     * 主键
     */
    private int id;

    /**
     * 请求人ID
     */
    private String accountID;

    /**
     * app类型：0 人人播  1 FM
     */
    private int app;

    /**
     * 手机唯一标识符
     */
    private String phoneCode;

    /**
     * 请求的Url
     */
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return "RequestRecord{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", app=" + app +
                ", phoneCode='" + phoneCode + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
