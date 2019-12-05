package com.xiexinxin.business.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    @Select("SELECT * FROM OPP_BUSI_ACS_CFG WHERE BUSI_CODE='V0001'")
    List<Map> queryAcsInfo(Map params);
}
