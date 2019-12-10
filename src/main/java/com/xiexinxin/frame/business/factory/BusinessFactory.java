package com.xiexinxin.frame.business.factory;

import com.xiexinxin.frame.business.IBusiness;
import com.xiexinxin.frame.business.bex.BexBusinessImpl;
import com.xiexinxin.frame.exception.XframeException;
import com.xiexinxin.frame.holder.ApplicationContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class BusinessFactory {
    private Map<String, IBusiness> businessMap = new ConcurrentHashMap<>();

    public BusinessFactory() {
    }

    public IBusiness getBusinessInstance(String businessType) {
        switch (businessType) {
            case "1":
                return ApplicationContextHolder.getContext().getBean(BexBusinessImpl.class);
            default:
                throw new XframeException("非法的businessType: " + businessType);
        }
    }
}
