package me.daoke.renrenfm.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象 ，含分页数据 及分页信息
 *
 * @param <T>
 */
public class JqgridPageList<T> implements Serializable {
    private static final long serialVersionUID = -2949497622293336316L;

    //当前的页数
    private int page;

    //每页显示的条数
    private int size;

    //总页数
    private int total;

    //记录的总条数
    private int records;

    //查询记录的结果集
    private List<T> rows;

    public int getPage() {
        return page;
    }

    /**
     * 获取任一页第一条数据的位置,startIndex从0开始
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    public void setPage(int page) {
        if(page == 0){
            page = 1;
        }
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int records,int size) {
        if(records%size == 0){
            this.total = records/size;
        }else{
            this.total = records/size + 1;
        }


    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
