package com.xiexinxin.frame.business.atom;

import com.xiexinxin.frame.business.IBusiness;
import com.xiexinxin.frame.business.atom.config.AtomConfig;
import com.xiexinxin.frame.business.atom.exchange.DataExchangeAssembly;
import com.xiexinxin.frame.business.config.BusinessConfig;
import com.xiexinxin.frame.config.ConfigLoader;
import com.xiexinxin.frame.exception.XframeException;
import com.xiexinxin.frame.holder.ApplicationContextHolder;
import com.xiexinxin.frame.modal.GenericRequest;
import com.xiexinxin.frame.modal.GenericResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class AtomBusinessImpl implements IBusiness {

    @Autowired
    private ConfigLoader configLoader;

    @Override
    public GenericResult doBusiness(GenericRequest genericRequest) {
        return null;
    }

    @Override
    public GenericResult doBusiness(GenericRequest genericRequest, BusinessConfig businessConfig) {
        long startTime = System.currentTimeMillis();
        String businessCode = businessConfig.getBusinessCode();
        Map<String, AtomConfig> atomConfigMap = configLoader.getAtomConfigMap();
        AtomConfig atomConfig = atomConfigMap.get(businessCode);
        DataExchangeAssembly dataExchangeAssembly = new DataExchangeAssembly();
        dataExchangeAssembly.startBusiness();
        dataExchangeAssembly.setBusinessData("params", genericRequest.getRequestParams());
        Class<?> atomClass = getAtomClass(atomConfig);
        Method atomMethod = getAtomMethod(atomClass, atomConfig);
        Object bean = ApplicationContextHolder.getContext().getBean(atomClass);
        atomMethod.setAccessible(true);
        GenericResult genericResult = null;
        try {
            genericResult = (GenericResult) atomMethod.invoke(bean, dataExchangeAssembly);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        genericResult.setRunTimes(endTime - startTime);
        genericResult.setFlag("true");
        genericResult.setPrompt(atomConfig.getAtomDescription());
        return genericResult;
    }

    private Class<?> getAtomClass(AtomConfig atomConfig) {
        Class<?> clazz = null;
        try {
            clazz = Thread.currentThread().getContextClassLoader().loadClass(atomConfig.getClassName());
        } catch (ClassNotFoundException e) {
            throw new XframeException("原子业务全限定名配置错误,请检查! [" + atomConfig.getClassName() + "]");
        }
        return clazz;
    }


    private Method getAtomMethod(Class<?> atomClass, AtomConfig atomConfig) {
        String methodName = atomConfig.getClassMethod();
        Method method = null;
        try {
            method = atomClass.getMethod(methodName, DataExchangeAssembly.class);
        } catch (NoSuchMethodException e) {
            throw new XframeException("原子业务方法名配置错误,请检查!" + atomConfig);
        }
        return method;
    }
}
