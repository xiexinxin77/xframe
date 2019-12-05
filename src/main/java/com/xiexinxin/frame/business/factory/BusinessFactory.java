package com.xiexinxin.frame.business.factory;

import com.xiexinxin.frame.business.IBusiness;
import com.xiexinxin.frame.business.bex.BexBusinessImpl;
import com.xiexinxin.frame.exception.XframeException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BusinessFactory {
    private Map<String, IBusiness> businessMap = new ConcurrentHashMap<>();

    public BusinessFactory() {
        init();
    }

    private void init() {
        businessMap.put("bex", new BexBusinessImpl());
        // todo
    }

    public IBusiness getBusinessInstance(String businessType) {
        switch (businessType) {
            case "1":
                return this.businessMap.get("bex");
            default:
                throw new XframeException("非法的businessType: " + businessType);
        }
    }
}
