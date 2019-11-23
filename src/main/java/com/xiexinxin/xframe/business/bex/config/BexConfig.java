package com.xiexinxin.xframe.business.bex.config;

/**
 * author: xiexx
 * data: 2019/11/17
 * time: 14:58
 */
public class BexConfig {
    private Long id;
    private String bexCode;
    private String bexName;
    private String mapperInterfaceName;
    private String mapperMethodName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBexCode() {
        return bexCode;
    }

    public void setBexCode(String bexCode) {
        this.bexCode = bexCode;
    }

    public String getBexName() {
        return bexName;
    }

    public void setBexName(String bexName) {
        this.bexName = bexName;
    }

    public String getMapperInterfaceName() {
        return mapperInterfaceName;
    }

    public void setMapperInterfaceName(String mapperInterfaceName) {
        this.mapperInterfaceName = mapperInterfaceName;
    }

    public String getMapperMethodName() {
        return mapperMethodName;
    }

    public void setMapperMethodName(String mapperMethodName) {
        this.mapperMethodName = mapperMethodName;
    }
}
