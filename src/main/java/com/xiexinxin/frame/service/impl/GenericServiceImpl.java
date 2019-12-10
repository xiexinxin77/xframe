package com.xiexinxin.frame.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpStatus;
import com.xiexinxin.frame.business.IBusiness;
import com.xiexinxin.frame.business.config.BusinessConfig;
import com.xiexinxin.frame.business.factory.BusinessFactory;
import com.xiexinxin.frame.config.ConfigLoader;
import com.xiexinxin.frame.exception.XframeException;
import com.xiexinxin.frame.modal.GenericRequest;
import com.xiexinxin.frame.modal.GenericResult;
import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.modal.GenericServiceResult;
import com.xiexinxin.frame.service.IService;
import com.xiexinxin.frame.service.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class GenericServiceImpl implements IService {

    @Autowired
    private BusinessFactory businessFactory;

    public GenericServiceImpl() {
    }

    @Override
    public GenericServiceResult doService(GenericServiceRequest request) {
        GenericServiceResult result = new GenericServiceResult();
        Map<String, Object> requestParams = request.getRequestParams();
        String serviceCode = (String) requestParams.get("service");
        ServiceConfig serviceConfig = ConfigLoader.getServiceConfigMap().get(serviceCode);
        if (null == serviceConfig) {
            throw new XframeException("找不到服务号[" + serviceCode + "]配置");
        }
        List<BusinessConfig> businessList = serviceConfig.getBusinessList();
        result.setMsg(serviceConfig.getServiceDescription());
        if (CollectionUtil.isNotEmpty(businessList)) {
            doBusiness(businessList, request, result);
        }
        return result;
    }

    private void doBusiness(List<BusinessConfig> businessConfigList, GenericServiceRequest request, GenericServiceResult result) {
        long startTime = System.currentTimeMillis();
        Iterator<BusinessConfig> iterator = businessConfigList.iterator();
        while (iterator.hasNext()) {
            BusinessConfig businessConfig = iterator.next();
            String businessType = businessConfig.getBusinessType();
            IBusiness businessInstance = businessFactory.getBusinessInstance(businessType);
            GenericRequest genericRequest = new GenericRequest();
            genericRequest.setRequestParams(request.getRequestParams());
            GenericResult genericResult = businessInstance.doBusiness(genericRequest, businessConfig);
            result.getDataList().add(genericResult);
        }
        long endTime = System.currentTimeMillis();
        result.setRunTimes(endTime - startTime);
        result.setCode(HttpStatus.HTTP_OK );
    }
}
