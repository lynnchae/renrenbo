package me.daoke.renrenfm.entity;

/**
 * 路况拥堵状况参数
 *
 * @author zhuosh
 * @date 2015/8/8
 */
public class RoadConditionPercent extends BaseEntity {

    /**
     * 主键
     */
    private int id;

    /***缓行比值*/
    private float slowPercent;

    /***拥挤比值*/
    private float crowPercent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSlowPercent() {
        return slowPercent;
    }

    public void setSlowPercent(float slowPercent) {
        this.slowPercent = slowPercent;
    }

    public float getCrowPercent() {
        return crowPercent;
    }

    public void setCrowPercent(float crowPercent) {
        this.crowPercent = crowPercent;
    }
}