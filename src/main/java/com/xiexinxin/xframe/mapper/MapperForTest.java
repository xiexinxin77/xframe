package com.xiexinxin.xframe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface MapperForTest {
    @Select("SELECT * FROM goods_innodb where id = #{id}")
    Map getInfo(Map params);
}
