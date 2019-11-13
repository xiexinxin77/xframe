package com.xiexinxin.xframe.dao;

import java.util.Map;

/**
 * dao层接口方法
 * author: xiexx
 * data: 2019/11/12
 * time: 23:03
 */
public interface IDao {
    Object doInvoke(String bexCode, Map params, Map comParams);
}
