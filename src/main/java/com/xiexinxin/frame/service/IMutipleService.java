package com.xiexinxin.frame.service;

import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.modal.GenericServiceResultList;

import java.util.List;

/**
 * 处理多条请求(并发)
 */
public interface IMutipleService {
    GenericServiceResultList doMutipleService(List<GenericServiceRequest> requestList);
}
