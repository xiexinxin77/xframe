package com.xiexinxin.frame.modal;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * web请求参数类
 */
public class GenericServiceRequest extends GenericRequest {
    protected HttpServletRequest request;

    public GenericServiceRequest(HttpServletRequest request, Map<String, Object> params) {
        super(params);
    }

    public GenericServiceRequest(){}

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "GenericServiceRequest{" +
                "requestParams=" + requestParams +
                '}';
    }
}
