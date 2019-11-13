package com.xiexinxin.xframe.utils;

import com.xiexinxin.xframe.holder.ConfigHolder;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
@Component
public class FrameDao implements ApplicationContextAware {

    private static ApplicationContext context;

    public static Object doBexCall(String bexCode) throws InvocationTargetException, IllegalAccessException {
        ConfigHolder.BexClassMethod bexClassMethod = ConfigHolder.getBexCodeMap().get(bexCode);
        SqlSession sqlSession = context.getBean(SqlSession.class);
        Object mapper = sqlSession.getMapper(bexClassMethod.getMapperClass());
        Object invoke = bexClassMethod.getMapperMethod().invoke(mapper);
        return invoke;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
