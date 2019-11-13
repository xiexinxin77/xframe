package com.xiexinxin.xframe.dao;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.xiexinxin.xframe.dao.config.ParamConfigConst;
import com.xiexinxin.xframe.exception.BexException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: xiexx
 * data: 2019/11/12
 * time: 22:01
 * bex 配置holder类
 */
@Component
public class BexConfigHolder implements InitializingBean {
    @Autowired
    private SqlSession sqlSession;
    private ResourceLoader resourceLoader;
    private String configPath;
    private static Map<String, Map> allBexConfigMap = new HashMap<>();
    private static Map<String, MapperInterfaceAndName> cachedMapperConfigMap = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.resourceLoader = new DefaultResourceLoader();
        if (StringUtils.isEmpty(configPath)) {
            configPath = "classpath:bexConfig.json";
        }
        init();
    }

    private void init() {
        Resource bexConfigResource = resourceLoader.getResource(this.configPath);
        String bexConfigStr = "";
        try {
            bexConfigStr = FileUtil.readString(bexConfigResource.getFile(), "UTF-8");
        } catch (IOException e) {
            throw new BexException("读取bex配置文件出错!");
        }
        // 解析配置json字符串
        List<Map> configList = JSONUtil.parseArray(bexConfigStr).toList(Map.class);
        if (CollectionUtil.isNotEmpty(configList)) {
            configList.forEach(config ->
                    allBexConfigMap.put((String) config.get(ParamConfigConst.bexCode), config));
        }
    }

    public MapperInterfaceAndName getMapperInterfaceAndName(String bexCode) {
        Map bexConfig = allBexConfigMap.get(bexCode);
        if (CollectionUtil.isEmpty(bexConfig)) {
            throw new BexException("没有找到该bex配置");
        }
        if (cachedMapperConfigMap.containsKey(bexCode)) {
            return cachedMapperConfigMap.get(bexCode);
        } else {
            String mapperInterfaceName = (String) bexConfig.get(ParamConfigConst.mapperInterfaceName);
            String mapperMethodName = (String) bexConfig.get(ParamConfigConst.mapperMethodName);
            Class<?> mapperInterfaceClass = getMapperInterfaceClass(mapperInterfaceName);
            Object mapper = sqlSession.getMapper(mapperInterfaceClass);
            Method mapperMethod = getMapperMethod(mapperInterfaceClass, mapperMethodName);
            MapperInterfaceAndName temp = new MapperInterfaceAndName(mapper, mapperInterfaceClass, mapperMethod);
            cachedMapperConfigMap.put(bexCode, temp);
            return temp;
        }
    }

    private Class<?> getMapperInterfaceClass(String mapperInterfaceName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(mapperInterfaceName);
        } catch (ClassNotFoundException e) {
            throw new BexException("加载MapperInterface失败,请检查配---!" + mapperInterfaceName);
        }
    }

    private Method getMapperMethod(Class<?> mapperInterfaceClass, String mapperMethodName) {
        try {
            return mapperInterfaceClass.getMethod(mapperMethodName, Map.class);
        } catch (NoSuchMethodException e) {
            throw new BexException("加载MapperMethod失败,请检查配置---!" + mapperMethodName);
        }
    }

    public static class MapperInterfaceAndName {
        private final Object mapper;
        private final Class mapperInterfaceClass;
        private final Method mapperMethod;

        public MapperInterfaceAndName(Object mapper, Class mapperInterfaceClass, Method mapperMethod) {
            this.mapper = mapper;
            this.mapperInterfaceClass = mapperInterfaceClass;
            this.mapperMethod = mapperMethod;
        }

        public Class getMapperInterfaceClass() {
            return mapperInterfaceClass;
        }

        public Method getMapperMethod() {
            return mapperMethod;
        }

        public Object getMapper() {
            return mapper;
        }
    }
}
