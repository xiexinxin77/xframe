package com.xiexinxin;

import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.modal.GenericServiceResult;
import com.xiexinxin.frame.service.IService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> requestMap = new HashMap();
        requestMap.put("service", "Y0001");
        requestMap.put("BUSI_CODE","V0001");
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

}
