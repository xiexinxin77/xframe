package com.xiexinxin.xframe.dao.mybatis;

import com.xiexinxin.xframe.dao.InParamParser;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis 入参解析器
 * author: xiexx
 * data: 2019/11/13
 * time: 22:11
 */
public class MybatisInParamParser implements InParamParser {
    @Override
    public Object doParse(Object[] vals) {
        Map newParams = new HashMap<>();
        if (vals!=null && vals.length > 0) {
            for (Object val : vals) {
                newParams.putAll((Map)val);
            }
        }
        return newParams;
    }
}
