package com.xiexinxin.frame.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiexinxin.frame.business.atom.config.AtomConfig;
import com.xiexinxin.frame.business.bex.config.BexConfig;
import com.xiexinxin.frame.service.config.ServiceConfig;
import com.xiexinxin.frame.exception.BexException;
import com.xiexinxin.frame.exception.GlobalExceptionHandler;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置文件加载容器类
 * author: xiexx
 * data: 2019/11/17
 * time: 17:27
 */
@Component
public class ConfigLoader {
    private ResourceLoader resourceLoader;

    private static Map<String, BexConfig> bexConfigMap;
    private static Map<String, ServiceConfig> serviceConfigMap;
    private static Map<String, AtomConfig> atomConfigMap;

    public ConfigLoader() {
        init();
    }

    public static Map<String, BexConfig> getBexConfigMap() {
        return Collections.unmodifiableMap(bexConfigMap);
    }

    public static Map<String, ServiceConfig> getServiceConfigMap() {
        return Collections.unmodifiableMap(serviceConfigMap);
    }

    public static Map<String, AtomConfig> getAtomConfigMap() {
        return Collections.unmodifiableMap(atomConfigMap);
    }

    private void init() {
        resourceLoader = new DefaultResourceLoader();
        bexConfigMap = new HashMap<>();
        atomConfigMap = new HashMap<>();
        serviceConfigMap = new HashMap<>();
        initBexConfig();
        initAtomConfig();
        initServiceConfig();
    }

    private void initBexConfig() {
        String configStr = configFileToString("classpath:metaData/bexConfig.json");
        JSONObject jsonObject = JSONUtil.parseObj(configStr);
        JSONArray jsonArray = jsonObject.getJSONArray("bexConfig");
        List<BexConfig> bexConfigList = jsonArray.toList(BexConfig.class);
        for (BexConfig bexConfig : bexConfigList) {
            bexConfigMap.put(bexConfig.getBexCode(), bexConfig);
        }
    }

    private void initAtomConfig() {
        String configStr = configFileToString("classpath:metaData/atomConfig.json");
        JSONObject jsonObject = JSONUtil.parseObj(configStr);
        JSONArray jsonArray = jsonObject.getJSONArray("atomConfig");
        List<AtomConfig> atomConfigList = jsonArray.toList(AtomConfig.class);
        for (AtomConfig atomConfig : atomConfigList) {
            atomConfigMap.put(atomConfig.getAtomCode(), atomConfig);
        }
    }

    private void initServiceConfig() {
        String configStr = configFileToString("classpath:metaData/serviceConfig.json");
        JSONObject jsonObject = JSONUtil.parseObj(configStr);
        JSONArray jsonArray = jsonObject.getJSONArray("serviceConfig");
        List<ServiceConfig> serviceConfigList = jsonArray.toList(ServiceConfig.class);
        for (ServiceConfig serviceConfig : serviceConfigList) {
            serviceConfigMap.put(serviceConfig.getServiceCode(), serviceConfig);
        }
    }

    private String configFileToString(String configFilePath) {
        Resource resource = resourceLoader.getResource(configFilePath);
        try {
            String content = FileUtil.readString(resource.getFile(), "UTF-8");
            return content;
        } catch (IOException e) {
            throw new BexException("");
        }
    }
}
