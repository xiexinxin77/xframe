package com.xiexinxin.frame.service;

import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.modal.GenericServiceResult;

/**
 * 通用服务接口类
 */
public interface IService {
    GenericServiceResult doService(GenericServiceRequest request);
}
