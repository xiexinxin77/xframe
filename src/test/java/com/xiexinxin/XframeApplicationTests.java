package com.xiexinxin;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.hutool.core.io.FileUtil;
import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.modal.GenericServiceResult;
import com.xiexinxin.frame.service.IService;
import com.xiexinxin.frame.web.parser.IGenericParser;
import com.xiexinxin.frame.web.parser.impl.JSONRequestParser;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class XframeApplicationTests {
    //    @Autowired
//    ResourceLoader resourceLoader;
//
//
    @Autowired
    private IService service;
//
//    @Autowired
//    private SqlSessionTemplate template;
//
//    @Autowired
//    TestMapper mapper;

    @Test
    void serviceTest() {
        GenericServiceRequest request = new GenericServiceRequest();
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("service", "Y0001");
        requestMap.put("BUSI_CODE", "V0001");
        request.setRequestParams(requestMap);
//        IService service = new GenericServiceImpl();
        GenericServiceResult result = service.doService(request);
        System.out.println(result);
    }
//
//    @Test
//    void hiTest() {
//        TestMapper mapper = template.getMapper(TestMapper.class);
//        System.out.println(mapper);
//    }


    @Test
    void testLambada() {
        List<Map> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map map = new HashMap();
            map.put("V", i + "");
            resultList.add(map);
        }
        Collections.shuffle(resultList);
        Map map1 = new HashMap();
        map1.put("V", "20");
        resultList.add(map1);
        Map map2 = new HashMap();
        map2.put("V", "0");
        resultList.add(map2);
        resultList = resultList.stream().sorted((v1, v2) -> {
            int a = Integer.valueOf((String) v1.get("V"));
            int b = Integer.valueOf((String) v2.get("V"));
            return b - a;
        }).collect(Collectors.toList());
        System.out.println(resultList);
    }

    @Test
    void testJsonParser() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:metaData/request.json");
        String s = FileUtil.readString(resource.getFile(), "UTF-8");
        IGenericParser genericParser = new JSONRequestParser();
        GenericServiceRequest request = genericParser.doRequestParser(s, false);
        System.out.println(request);
    }
}
