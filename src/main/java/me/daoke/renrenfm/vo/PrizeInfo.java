package me.daoke.renrenfm.vo;

/**
 * 奖品列表
 * @author liyanqin
 * @date 2015/6/17
 */
public class PrizeInfo extends BaseEntity {

    /***主键*/
    private int id;

    /***奖品的名称*/
    private String name;

    /***密点数*/
    private int denseNum;

    /***图标*/
    private String icon;

    /***领取奖品后，给主播加的人气值*/
    private int collectPopularity;

    /****赠送给主播，给主播加的人气值*/
    private int givePopularity;

    /***领取奖品时，给主播加的财富值*/
    private int collectWealthVal;

    /***送奖品给主播，给主播加的财富值*/
    private int giveWealthVal;

    /***奖品的描述*/
    private String description;

    /***奖品的编码*/
    private String prizeCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDenseNum() {
        return denseNum;
    }

    public void setDenseNum(int denseNum) {
        this.denseNum = denseNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCollectPopularity() {
        return collectPopularity;
    }

    public void setCollectPopularity(int collectPopularity) {
        this.collectPopularity = collectPopularity;
    }

    public int getGivePopularity() {
        return givePopularity;
    }

    public void setGivePopularity(int givePopularity) {
        this.givePopularity = givePopularity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCollectWealthVal() {
        return collectWealthVal;
    }

    public void setCollectWealthVal(int collectWealthVal) {
        this.collectWealthVal = collectWealthVal;
    }

    public int getGiveWealthVal() {
        return giveWealthVal;
    }

    public void setGiveWealthVal(int giveWealthVal) {
        this.giveWealthVal = giveWealthVal;
    }

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }
}
