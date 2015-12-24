package me.daoke.renrenfm.controller.demo;

import java.util.List;

/**
 * Created by zhuosh on 2015/8/2.
 */
public class PageList<T> {
    private int page;
    private int total;
    private int records;

    private List<T> rows;

    public int getPage() {
        return page;
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

    public void setTotal(int records,int rows) {
        if(records%rows == 0){
            this.total = records/rows;
        }else{
            this.total = records/rows + 1;
        }


    }

    /**
     * 获取任一页第一条数据的位置,startIndex从0开始
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
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
