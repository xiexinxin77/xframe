package com.xiexinxin.frame.config;

import com.xiexinxin.frame.business.bex.config.BexConfig;
import org.springframework.core.io.ResourceLoader;

import java.util.Map;

/**
 * author: xiexx
 * data: 2019/11/17
 * time: 17:27
 */
public class ConfigLoader {
    private ResourceLoader resourceLoader;

    private static Map<String, BexConfig> bexConfigMap;
}
