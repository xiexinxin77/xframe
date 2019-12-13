package com.xiexinxin.frame.modal;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenericServiceResult implements Serializable {
    private Integer code;
    private String msg;
    private Long runTimes;
    private List<GenericResult> dataList;

    public GenericServiceResult(Integer code, String msg, List<GenericResult> dataList) {
        this.code = code;
        this.msg = msg;
        this.dataList = dataList;
    }

    public GenericServiceResult() {
        this.dataList = new ArrayList<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    @Override
    public String toString() {
        return "GenericServiceResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", runTimes=" + runTimes +
                ", dataList=" + dataList +
                '}';
    }

    public String buildJSONString() {
        JSON parse = JSONUtil.parse(this);
        return parse.toString();
    }
}
