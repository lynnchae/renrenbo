package me.daoke.renrenfm.vo;

/**
 * 键值对应
 * Created by zhaoym on 2015/9/10.
 */
public class KeyValue {

    private String ky;

    private String val;

    public String getKy() {
        return ky;
    }

    public void setKy(String ky) {
        this.ky = ky;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "KeyValue{" +
                "ky='" + ky + '\'' +
                ", val='" + val + '\'' +
                '}';
    }

}
