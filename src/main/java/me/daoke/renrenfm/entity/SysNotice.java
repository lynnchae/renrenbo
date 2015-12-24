package me.daoke.renrenfm.entity;

/**
 * 系统公告实体类
 * @author zhuosh
 * @date 2015/8/9
 */
public class SysNotice extends BaseEntity{

    /***主键*/
    private int id;

    /***标题*/
    private String title;

    /***简介*/
    private String brief;

    /***图标*/
    private String icon;

    /***类别  跳转的类别 0表示跳转网页  1表示跳转APP本地*/
    private int type;

    /***跳转的url*/
    private String url;

    /**显示的顺序*/
    private int showOrder;

    /***描述 1 表示活动*/
    private  int remark;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysNotice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", showOrder=" + showOrder +
                '}';
    }
}
