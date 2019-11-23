package com.xiexinxin.xframe.business.config;

/**
 * author: xiexx
 * data: 2019/11/17
 * time: 14:50
 */
public class BusinessConfig {
    private Long id;
    // 服务号
    private String serviceCode;
    // 原子业务号
    private String businessCode;
    // 原子业务类型(1Atom,2bex)
    private String businessType;
    // 原子业务名字
    private String businessName;
    // 原子业务描述
    private String businessDesc;

    public BusinessConfig(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }
}
