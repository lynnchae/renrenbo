package me.daoke.renrenfm.entity;

/**
 * 用户收货地址
 * @author zhuosh
 * @date 2015/6/18
 */
public class ShoppingAddress {

    /***主键*/
    private int id;

    /***用户ID*/
    private String accountID;

    /***当前的地址是否为默认地址  1是  0不是*/
    private int isDefault;

    /***收件人姓名*/
    private String recipientsName;

    /***收件人电话*/
    private String recipientsMobile;

    /***邮编*/
    private int postcode;

    /***省名*/
    private String provinceName;

    /***省编号*/
    private int provinceCode;

    /***城市名*/
    private String cityName;

    /***城市编号*/
    private int cityCode;

    /***区县名*/
    private String countyName;

    /***区县编号*/
    private int countyCode;

    /***详细地址*/
    private String detailAddress;


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

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName;
    }

    public String getRecipientsMobile() {
        return recipientsMobile;
    }

    public void setRecipientsMobile(String recipientsMobile) {
        this.recipientsMobile = recipientsMobile;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(int countyCode) {
        this.countyCode = countyCode;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        return "ShoppingAddress{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", isDefault=" + isDefault +
                ", recipientsName='" + recipientsName + '\'' +
                ", recipientsMobile='" + recipientsMobile + '\'' +
                ", postcode=" + postcode +
                ", provinceName='" + provinceName + '\'' +
                ", provinceCode=" + provinceCode +
                ", cityName='" + cityName + '\'' +
                ", cityCode=" + cityCode +
                ", countyName='" + countyName + '\'' +
                ", countyCode=" + countyCode +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }
}
