package com.xiexinxin.frame.service.config;

import com.xiexinxin.frame.business.config.BusinessConfig;

import java.util.List;

/**
 * author: xiexx
 * data: 2019/11/17
 * time: 17:14
 */
public class ServiceConfig {
    private String serviceCode;
    private String serviceName;
    private String serviceDescription;
    private List<BusinessConfig> businessList;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public List<BusinessConfig> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<BusinessConfig> businessList) {
        this.businessList = businessList;
    }

    @Override
    public String toString() {
        return "ServiceConfig{" +
                "serviceCode='" + serviceCode + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", businessList=" + businessList +
                '}';
    }
}
