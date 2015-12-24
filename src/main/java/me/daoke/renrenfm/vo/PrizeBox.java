package me.daoke.renrenfm.vo;

/**
 * 箱子实体对象
 * Created by liyanqin on 2015/8/1.
 */
public class PrizeBox extends BaseEntity {

    private int id;

    /**箱子编码**/
    private String boxCode;

    /**箱子名称**/
    private String boxName;

    /**图片**/
    private String boxImg;

    /**开箱所需金额**/
    private int denseNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public int getDenseNum() {
        return denseNum;
    }

    public void setDenseNum(int denseNum) {
        this.denseNum = denseNum;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getBoxImg() {
        return boxImg;
    }

    public void setBoxImg(String boxImg) {
        this.boxImg = boxImg;
    }

    @Override
    public String toString() {
        return "PrizeBox{" +
                "id=" + id +
                ", boxCode='" + boxCode + '\'' +
                ", boxName='" + boxName + '\'' +
                ", boxImg='" + boxImg + '\'' +
                ", denseNum=" + denseNum +
                '}';
    }
}
