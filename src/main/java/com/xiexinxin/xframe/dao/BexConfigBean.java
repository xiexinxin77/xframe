package com.xiexinxin.xframe.dao;

/**
 * author: xiexx
 * data: 2019/11/12
 * time: 21:54
 * Bex 配置实体类
 */
public class BexConfigBean {
    // mapper接口全限定名
    private String mapperInterfaceName;
    // mapper方法名称
    private String mapperMethodName;
    // 对应bex code
    private String bexCode;
    // bex 类型
    private BexTypeEnum bexType;
    // 分页类型
    private PageModeEnum pageMode;

    public BexConfigBean() {}

    public BexConfigBean(String mapperInterfaceName, String mapperMethodName, String bexCode, BexTypeEnum bexType, PageModeEnum pageMode) {
        this.mapperInterfaceName = mapperInterfaceName;
        this.mapperMethodName = mapperMethodName;
        this.bexCode = bexCode;
        this.bexType = bexType;
        this.pageMode = pageMode;
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

    public String getBexCode() {
        return bexCode;
    }

    public void setBexCode(String bexCode) {
        this.bexCode = bexCode;
    }

    public BexTypeEnum getBexType() {
        return bexType;
    }

    public void setBexType(BexTypeEnum bexType) {
        this.bexType = bexType;
    }

    public PageModeEnum getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageModeEnum pageMode) {
        this.pageMode = pageMode;
    }

    /**
     * bex类型
     */
    enum BexTypeEnum {
        MybatisBex,
        RedisBex,
        SpringDataJpaBex;
    }

    /**
     * 分页查询模式
     */
    enum PageModeEnum{
        PageHelper,
        // 逻辑分页
        LogicPageMode;
    }
}
