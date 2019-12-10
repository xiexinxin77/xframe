package com.xiexinxin.frame.business.atom.exchange;

import com.xiexinxin.frame.business.config.BusinessConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Atom请求数据中转类
 */
public class DataExchangeAssembly {

    // 存放业务参数
    private static final ThreadLocal<Map> businessArea = ThreadLocal.withInitial(() -> new HashMap());

    private static final ThreadLocal<Map> serviceArea = ThreadLocal.withInitial(() -> new HashMap());


    public boolean setBusinessData(String key, Object value) {
        Map map = businessArea.get();
        map.put(key, value);
        businessArea.set(map);
        return true;
    }

    public boolean setServiceData(String key, Object value) {
        Map map = serviceArea.get();
        map.put(key, value);
        serviceArea.set(map);
        return true;
    }

    public DataExchangeAssembly setParams(Map params) {
        setBusinessData("params", params);
        return this;
    }

    public DataExchangeAssembly setBusinessConfig(BusinessConfig businessConfig) {
        setBusinessData("businessConfig", businessConfig);
        return this;
    }

    public BusinessConfig getBusinessConfig() {
        return (BusinessConfig) getBusinessAreaData("businessConfig");
    }

    public DataExchangeAssembly setRequest(HttpServletRequest request) {
        setBusinessData("request", request);
        return this;
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getBusinessAreaData("request");
    }

    public Object getBusinessAreaData(String key) {
        return businessArea.get().get(key);
    }

    public Object getServiceAreaData(String key) {
        return serviceArea.get().get(key);
    }

    public void startService() {
        serviceArea.remove();
    }

    public void endService() {
        serviceArea.remove();
    }

    public void startBusiness() {
        businessArea.remove();
    }

    public void endBusiness() {
        businessArea.remove();
    }

}
