package com.xiexinxin.xframe.holder;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigHolder implements InitializingBean {
    private ResourceLoader resourceLoader;
    private String basePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.resourceLoader = new DefaultResourceLoader();
        init();
    }

    private static List<Map> allBexMap = new ArrayList<>();
    private static Map<String, BexClassMethod> bexCodeMap = new ConcurrentHashMap();

    private void init() {
        if (StringUtils.isEmpty(basePath)) {
            basePath = "classpath:bexConfig.json";
        }
        Resource mapperConfigResource = resourceLoader.getResource(basePath);
        try {
            String mapperConfigStr = FileUtil.readString(mapperConfigResource.getFile(), "UTF-8");
            allBexMap = JSONUtil.parseArray(mapperConfigStr).toList(Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CollectionUtil.isNotEmpty(allBexMap)) {
            for (Map v : allBexMap) {
                try {
                    Class<?> mapperClass = Class.forName((String) v.get("className"));
                    Method mapperMethod = mapperClass.getMethod((String) v.get("mapperMethod"));
                    BexClassMethod bexClassMethod = new BexClassMethod(mapperClass, mapperMethod);
                    bexCodeMap.put((String) v.get("bexCode"), bexClassMethod);
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<Map> getAllBexMap() {
        return Collections.unmodifiableList(allBexMap);
    }

    public static Map<String, BexClassMethod> getBexCodeMap() {
        return Collections.unmodifiableMap(bexCodeMap);
    }

    public static class BexClassMethod {
        private Class<?> mapperClass;
        private Method mapperMethod;

        BexClassMethod(Class<?> mapperClass, Method mapperMethod) {
            this.mapperClass = mapperClass;
            this.mapperMethod = mapperMethod;
        }

        public Class<?> getMapperClass() {
            return mapperClass;
        }

        public void setMapperClass(Class<?> mapperClass) {
            this.mapperClass = mapperClass;
        }

        public Method getMapperMethod() {
            return mapperMethod;
        }

        public void setMapperMethod(Method mapperMethod) {
            this.mapperMethod = mapperMethod;
        }
    }
}
