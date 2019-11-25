package com.xiexinxin.frame.business.bex.config;

/**
 *bex配置实体类
 * author: xiexx
 * data: 2019/11/17
 * time: 17:02
 */
public class BexConfig {
    private String bexCode;
    private String mapperInterfaceName;
    private String mapperMethodName;
    private String bexName;
    private String bexDescription;

    public String getBexCode() {
        return bexCode;
    }

    public void setBexCode(String bexCode) {
        this.bexCode = bexCode;
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

    public String getBexName() {
        return bexName;
    }

    public void setBexName(String bexName) {
        this.bexName = bexName;
    }

    public String getBexDescription() {
        return bexDescription;
    }

    public void setBexDescription(String bexDescription) {
        this.bexDescription = bexDescription;
    }

    @Override
    public String toString() {
        return "BexConfig{" +
                "bexCode='" + bexCode + '\'' +
                ", mapperInterfaceName='" + mapperInterfaceName + '\'' +
                ", mapperMethodName='" + mapperMethodName + '\'' +
                ", bexName='" + bexName + '\'' +
                ", bexDescription='" + bexDescription + '\'' +
                '}';
    }
}
