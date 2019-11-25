package com.xiexinxin.frame.modal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用请求实体类
 */
public class GenericRequest implements Serializable {
    protected Map<String, Object> requestParams;

    public GenericRequest() {
        requestParams = new HashMap<>();
    }

    public GenericRequest(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }

    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }
}
