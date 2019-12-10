package com.xiexinxin.frame.business.atom.basic;

import com.xiexinxin.frame.holder.ApplicationContextHolder;
import com.xiexinxin.frame.modal.GenericResult;

import java.util.Map;

public class FrameDao {

    public static GenericResult doBexCall(String bexCode, Map params) {
        BusinessInstance bean = ApplicationContextHolder.getContext().getBean(BusinessInstance.class);
        GenericResult genericResult = bean.callBusiness(bexCode, params, "1");
        return genericResult;
    }
}
