package com.xiexinxin.xframe;

import com.alibaba.fastjson.JSONArray;
import com.xiexinxin.xframe.dao.IDao;
import com.xiexinxin.xframe.dao.mybatis.MybatisIDao;
import com.xiexinxin.xframe.mapper.MapperForTest;
import com.xiexinxin.xframe.utils.FrameDao;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class XframeApplicationTests {
    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    SqlSession sqlSession;

    @Autowired
    MapperForTest test;

    IDao idao = new MybatisIDao();

    @Test
    void contextLoads() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resource resource = resourceLoader.getResource("classpath:bexConfig.json");
        InputStream inputStream = resource.getInputStream();
        byte[] buffer = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(buffer)) != -1) {
            sb.append(new String(buffer));
        }
        System.out.println(sb.toString());
        JSONArray array = JSONArray.parseArray(sb.toString());
        Object id = array.getJSONObject(0).get("id");
        System.out.println(id);
        MapperForTest mapper = sqlSession.getMapper(MapperForTest.class);
        Method getInfo = mapper.getClass().getMethod("getInfo", Map.class);
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        Object invoke = getInfo.invoke(mapper, params);
        System.out.println(invoke);
        FrameDao.doBexCall("Y0001");
    }

    @Test
    void testMapper() {
        Map params = new HashMap();
        params.put("id","1");

        Object y0001 = idao.doInvoke("Y00901", params, new HashMap());
        System.out.println(y0001);
    }

}
