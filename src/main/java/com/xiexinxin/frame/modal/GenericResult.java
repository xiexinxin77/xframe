package com.xiexinxin.frame.modal;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * business 执行接口对应实体类
 */
public class GenericResult implements Serializable {
    private String flag;
    private String prompt;
    private Long runTimes;
    private List<Map> dataList;

    public GenericResult() {
        this.dataList = new ArrayList<>();
    }

    public GenericResult(String flag, String prompt) {
        this.flag = flag;
        this.prompt = prompt;
        this.dataList = new ArrayList<>();
    }

    public GenericResult(String flag, String prompt, List<Map> dataList) {
        this.flag = flag;
        this.prompt = prompt;
        this.dataList = dataList;
    }

    public GenericResult(List<Map> dataList) {
        this.flag = "0";
        this.prompt = "查询成功";
        this.dataList = dataList;
    }

    public boolean isSuccess() {
        return Objects.equals("1", this.flag) ? true : false;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Long getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(Long runTimes) {
        this.runTimes = runTimes;
    }

    public List<Map> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "GenericResult{" +
                "flag='" + flag + '\'' +
                ", prompt='" + prompt + '\'' +
                ", runTimes=" + runTimes +
                ", dataList=" + dataList +
                '}';
    }
}
