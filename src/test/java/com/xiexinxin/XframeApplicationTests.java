package com.xiexinxin;

import com.xiexinxin.business.dao.TestMapper;
import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.modal.GenericServiceResult;
import com.xiexinxin.frame.service.IService;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class XframeApplicationTests {
    @Autowired
    ResourceLoader resourceLoader;


    @Autowired
    private IService service;

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    TestMapper mapper;

    @Test
    void serviceTest() {
        GenericServiceRequest request = new GenericServiceRequest();
        Map requestMap = new HashMap();
        requestMap.put("service", "Y0001");
        request.setRequestParams(requestMap);
        GenericServiceResult result = service.doService(request);
    }

    @Test
    void hiTest() {
        TestMapper mapper = template.getMapper(TestMapper.class);
        System.out.println(mapper);
    }

}
