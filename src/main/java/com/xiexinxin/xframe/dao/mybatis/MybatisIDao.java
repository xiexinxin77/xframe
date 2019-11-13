package com.xiexinxin.xframe.dao.mybatis;

import com.xiexinxin.xframe.dao.BexConfigHolder;
import com.xiexinxin.xframe.dao.IDao;
import com.xiexinxin.xframe.dao.InParamParser;
import com.xiexinxin.xframe.exception.BexException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * author: xiexx
 * data: 2019/11/12
 * time: 23:07
 */
@Component
public class MybatisIDao implements IDao, ApplicationContextAware {
    private BexConfigHolder bexConfigHolder;

    private InParamParser inParamParser;

    private static  ApplicationContext applicationContext;

    private void init() {
        this.bexConfigHolder = applicationContext.getBean(BexConfigHolder.class);
        this.inParamParser = new MybatisInParamParser();
    }

    @Override
    public Object doInvoke(String bexCode, Map params, Map comParams) {
        init();
        BexConfigHolder.MapperInterfaceAndName mapperInterfaceAndName = bexConfigHolder.getMapperInterfaceAndName(bexCode);
        Object inParams = inParamParser.doParse(params, comParams);
        Object result = null;
        try {
            result = mapperInterfaceAndName.getMapperMethod().invoke(mapperInterfaceAndName.getMapper(), inParams);
        } catch (Exception e) {
            throw new BexException("调用mapper方法失败");
        }
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
