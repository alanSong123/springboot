package com.syt.utils;

import java.util.List;

public class ResultData {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public ResultData(long total,List<?> rows){
        this.total = total;
        this.rows = rows;
    }

    public ResultData(){}
}
