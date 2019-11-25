package com.xiexinxin.frame.dao;

import com.xiexinxin.frame.business.bex.config.BexConfig;
import com.xiexinxin.frame.modal.GenericRequest;
import com.xiexinxin.frame.modal.GenericResult;

/**
 * 数据库操作基类
 */
public interface IDao {
    GenericResult doInvoke(GenericRequest genericRequest, BexConfig bexConfig);
}
