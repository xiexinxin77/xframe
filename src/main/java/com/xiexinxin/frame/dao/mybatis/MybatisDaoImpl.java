package com.xiexinxin.frame.dao.mybatis;

import com.xiexinxin.frame.business.bex.config.BexConfig;
import com.xiexinxin.frame.dao.IDao;
import com.xiexinxin.frame.modal.GenericRequest;
import com.xiexinxin.frame.modal.GenericResult;
import com.xiexinxin.frame.exception.BexException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MybatisDaoImpl implements IDao, ApplicationContextAware {

    private static final Map<String, MapperInterfaceAndMapperMethod> cachedMapperMap = new ConcurrentHashMap<>();

    private ApplicationContext context;

//    @Autowired
//    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public GenericResult doInvoke(GenericRequest genericRequest, BexConfig bexConfig) {
        SqlSessionTemplate sqlSessionTemplate = context.getBean(SqlSessionTemplate.class);
        String bexCode = bexConfig.getBexCode();
        MapperInterfaceAndMapperMethod mapperInterfaceAndMapperMethod = null;
        Object result = null;
        GenericResult genericResult = new GenericResult();
        if (cachedMapperMap.containsKey(bexCode)) {
            mapperInterfaceAndMapperMethod = cachedMapperMap.get(bexCode);
        } else {
            Class<?> mapperInterfaceClazz = getMapperInterfaceClazz(bexConfig.getMapperInterfaceName());
            Method mapperMethod = getMapperMethod(mapperInterfaceClazz, bexConfig.getMapperMethodName());
            Object mapper = sqlSessionTemplate.getMapper(mapperInterfaceClazz);
            mapperInterfaceAndMapperMethod = new MapperInterfaceAndMapperMethod(mapper, mapperInterfaceClazz, mapperMethod);
            cachedMapperMap.put(bexCode, mapperInterfaceAndMapperMethod);
        }
        try {
            result = mapperInterfaceAndMapperMethod.getMapperMethod().invoke(mapperInterfaceAndMapperMethod.getMapperInterface(), genericRequest.getRequestParams());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (result instanceof Map) {
            genericResult.getDataList().add((Map) result);
        } else {
            genericResult.setDataList((List<Map>) result);
        }
        return genericResult;
    }


    private Class<?> getMapperInterfaceClazz(String mapperInterfaceName) {
        Class<?> mapperClazz = null;
        try {
            mapperClazz = Thread.currentThread().getContextClassLoader().loadClass(mapperInterfaceName);
        } catch (ClassNotFoundException e) {
            throw new BexException("加载mapper失败--" + mapperInterfaceName);
        }
        return mapperClazz;
    }

    private Method getMapperMethod(Class<?> mapperInterface, String mapperMethodName) {
        Method mapperMethod = null;
        try {
            mapperMethod = mapperInterface.getMethod(mapperMethodName, Map.class);
        } catch (NoSuchMethodException e) {
            throw new BexException("加载Mapper method失败--" + mapperMethodName);
        }
        return mapperMethod;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    class MapperInterfaceAndMapperMethod {
        private Class<?> mapperInterface;
        private Method mapperMethod;
        private Object mapper;

        public MapperInterfaceAndMapperMethod(Object mapper, Class<?> mapperInterface, Method mapperMethod) {
            this.mapper = mapper;
            this.mapperInterface = mapperInterface;
            this.mapperMethod = mapperMethod;
        }

        public Class<?> getMapperInterface() {
            return mapperInterface;
        }

        public void setMapperInterface(Class<?> mapperInterface) {
            this.mapperInterface = mapperInterface;
        }

        public Method getMapperMethod() {
            return mapperMethod;
        }

        public void setMapperMethod(Method mapperMethod) {
            this.mapperMethod = mapperMethod;
        }

        public Object getMapper() {
            return mapper;
        }

        public void setMapper(Object mapper) {
            this.mapper = mapper;
        }
    }

}
