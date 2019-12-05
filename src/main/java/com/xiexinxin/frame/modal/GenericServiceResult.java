package com.xiexinxin.frame.modal;

import java.io.Serializable;
import java.util.List;

public class GenericServiceResult implements Serializable {
    private String code;
    private String msg;
    private Long runTimes;
    private List<GenericResult> dataList;

    public GenericServiceResult(String code, String msg, List<GenericResult> dataList) {
        this.code = code;
        this.msg = msg;
        this.dataList = dataList;
    }

    public GenericServiceResult() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(Long runTimes) {
        this.runTimes = runTimes;
    }

    public List<GenericResult> getDataList() {
        return dataList;
    }

    public void setDataList(List<GenericResult> dataList) {
        this.dataList = dataList;
    }
}
