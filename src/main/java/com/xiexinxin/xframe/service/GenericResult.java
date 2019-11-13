package com.xiexinxin.xframe.service;

import java.util.List;

/**
 * author: xiexx
 * data: 2019/11/13
 * time: 22:52
 */
public class GenericResult {
    // 状态码
    private String code;
    // 提示信息
    private String msg;
    // 查询花费时间
    private Long times;
    // 返回结果
    private List dataList;

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

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }
}
