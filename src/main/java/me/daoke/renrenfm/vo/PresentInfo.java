package me.daoke.renrenfm.vo;

/**
 * 礼物
 * @author liyanqin
 * @date 2015/8/1
 */
public class PresentInfo extends BaseEntity {

    /***主键*/
    private int id;

    /***礼物名称*/
    private String name;

    /***礼物图标*/
    private String icon;

    /***购买礼物的密点数*/
    private int denseNum;

    /***该礼物的类型  0表示领取获得，1表示付费获得*/
    private int type;

    /***该礼物对应的人气值*/
    private int popularityVal;

    /***一段时间内允许赠送的数量（目前频率为1天）*/
    private int frequencyNum;

    /***呈现的效果的方式*/
    private int appearType;

    /***打赏主播时的钻数*/
    private int diamond;

    public interface TYPE{
        final int FREE = 0;//0表示领取获得
        final int NEED_MONEY = 1;//1表示付费获得
    }

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getDenseNum() {
        return denseNum;
    }

    public void setDenseNum(int denseNum) {
        this.denseNum = denseNum;
    }

    public int getPopularityVal() {
        return popularityVal;
    }

    public void setPopularityVal(int popularityVal) {
        this.popularityVal = popularityVal;
    }

    public int getFrequencyNum() {
        return frequencyNum;
    }

    public void setFrequencyNum(int frequencyNum) {
        this.frequencyNum = frequencyNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAppearType() {
        return appearType;
    }

    public void setAppearType(int appearType) {
        this.appearType = appearType;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    @Override
    public String toString() {
        return "PresentInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", denseNum=" + denseNum +'\'' +
                ", createTime=" + createTime +
                '}';
    }
}
