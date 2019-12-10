package com.xiexinxin.frame.business.atom.basic;

import com.xiexinxin.frame.business.IBusiness;
import com.xiexinxin.frame.business.config.BusinessConfig;
import com.xiexinxin.frame.business.factory.BusinessFactory;
import com.xiexinxin.frame.holder.ApplicationContextHolder;
import com.xiexinxin.frame.modal.GenericRequest;
import com.xiexinxin.frame.modal.GenericResult;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class BusinessInstance {

    public GenericResult callBusiness(String businessCode, Map requestParams, String businessType) {
        BusinessFactory businessFactory = ApplicationContextHolder.getContext().getBean(BusinessFactory.class);
        IBusiness businessInstance = businessFactory.getBusinessInstance(businessType);
        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessCode(businessCode);
        businessConfig.setBusinessType(businessType);
        GenericRequest request = new GenericRequest();
        request.setRequestParams(requestParams);
        GenericResult genericResult = businessInstance.doBusiness(request, businessConfig);
        return genericResult;
    }
}
