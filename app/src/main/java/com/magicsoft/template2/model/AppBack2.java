package com.magicsoft.template2.model;

import java.io.Serializable;

public class AppBack2<T> implements Serializable {

    /**
     * 成功0
     * 错误-1
     */
    private int status;
    //private Object result;
    //private Object result_en;
    private int totalcount;//记录总数
    private String hasNext; //是否有下一页
    private T result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public String getHasNext() {
        return hasNext;
    }

    public void setHasNext(String hasNext) {
        this.hasNext = hasNext;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}