package me.daoke.renrenfm.vo;

import java.util.Date;

/**
 * 实体类公共属性
 * @author zhuosh
 * @date 2015/6/1
 */
public class BaseEntity {
    /**
     * 添加记录的时间
     */
    public Date createTime;

    /***
     * 修改记录的时间
     */
    public Date updateTime;

    /**
     * 记录是否有效 1 表示有效   0表示无效
     */
    public int isValid;

    public interface ISVALID{
        final int VALID = 1;   //有效
        final int INVALID = 0;  //无效
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }
}
