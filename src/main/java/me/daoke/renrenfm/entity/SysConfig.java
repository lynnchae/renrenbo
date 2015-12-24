package me.daoke.renrenfm.entity;

/**
 * 系统参数配置
 * @author zhuosh
 * @date 2015/8/8
 */
public class SysConfig extends BaseEntity{


    /***主键*/
    private int id;

    /***参数值1*/
    private String valOne;

    /***参数值2*/
    private String valTwo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValOne() {
        return valOne;
    }

    public void setValOne(String valOne) {
        this.valOne = valOne;
    }

    public String getValTwo() {
        return valTwo;
    }

    public void setValTwo(String valTwo) {
        this.valTwo = valTwo;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
                "id=" + id +
                ", valOne='" + valOne + '\'' +
                ", valTwo='" + valTwo + '\'' +
                '}';
    }
}
