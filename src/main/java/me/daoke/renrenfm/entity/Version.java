package me.daoke.renrenfm.entity;

/**
 * 版本号
 * Created by zhaoym on 2015/8/12.
 */
public class Version {

    /**
     * 主键
     */
    private int id;

    /**
     * ios版本号
     */
    private String iosNumber;

    /**
     * android版本号
     */
    private String androidNumber;

    /**
     * iOS的更新地址
     */
    private String iosUpdateUrl;

    /**
     * Android的更新地址
     */
    private String androidUpdateUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否是最新 1:是 0:否
     */
    private int isUpToDate;

    /**
     * 是否强制更新 1:是 0:否
     */
    private int isForcedUpDate;

    /**
     * 是否有效 1:是 0:否
     */
    private int isValid;

    /**
     * 创建时间
     */
    private int createTime;

    /**
     * 修改时间
     */
    private int updateTime;

    public int getId() {
        return id;
    }

    public String getIosUpdateUrl() {
        return iosUpdateUrl;
    }

    public String getAndroidUpdateUrl() {
        return androidUpdateUrl;
    }

    public String getRemark() {
        return remark;
    }

    public int getIsUpToDate() {
        return isUpToDate;
    }

    public String getIosNumber() {
        return iosNumber;
    }

    public String getAndroidNumber() {
        return androidNumber;
    }

    public void setAndroidNumber(String androidNumber) {
        this.androidNumber = androidNumber;
    }

    public void setIosNumber(String iosNumber) {
        this.iosNumber = iosNumber;
    }

    public int getIsValid() {
        return isValid;
    }

    public int getCreateTime() {
        return createTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIosUpdateUrl(String iosUpdateUrl) {
        this.iosUpdateUrl = iosUpdateUrl;
    }

    public void setAndroidUpdateUrl(String androidUpdateUrl) {
        this.androidUpdateUrl = androidUpdateUrl;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setIsUpToDate(int isUpToDate) {
        this.isUpToDate = isUpToDate;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsForcedUpDate() {
        return isForcedUpDate;
    }

    public void setIsForcedUpDate(int isForcedUpDate) {
        this.isForcedUpDate = isForcedUpDate;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", iosNumber='" + iosNumber + '\'' +
                ", androidNumber='" + androidNumber + '\'' +
                ", iosUpdateUrl='" + iosUpdateUrl + '\'' +
                ", androidUpdateUrl='" + androidUpdateUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", isUpToDate=" + isUpToDate +
                ", isForcedUpDate=" + isForcedUpDate +
                ", isValid=" + isValid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
