package com.xiexinxin.frame.business.bex;

import com.xiexinxin.frame.business.IBusiness;
import com.xiexinxin.frame.business.bex.config.BexConfig;
import com.xiexinxin.frame.business.config.BusinessConfig;
import com.xiexinxin.frame.config.ConfigLoader;
import com.xiexinxin.frame.dao.IDao;
import com.xiexinxin.frame.dao.mybatis.MybatisDaoImpl;
import com.xiexinxin.frame.modal.GenericRequest;
import com.xiexinxin.frame.modal.GenericResult;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * bex 服务类
 */
public class BexBusinessImpl implements IBusiness, ApplicationContextAware {

    private ConfigLoader configLoader;
    private IDao iDao;


    @Override
    public GenericResult doBusiness(GenericRequest genericRequest) {
        return null;
    }

    @Override
    public GenericResult doBusiness(GenericRequest genericRequest, BusinessConfig businessConfig) {
        Map<String, BexConfig> bexConfigMap = configLoader.getBexConfigMap();
        BexConfig bexConfig = bexConfigMap.get(businessConfig.getBusinessCode());
        return doBusiness(genericRequest, bexConfig);
    }

    private GenericResult doBusiness(GenericRequest genericRequest, BexConfig bexConfig) {
        String businessType = bexConfig.getBusinessType();
        switch (businessType) {
            case "1": {
                iDao = new MybatisDaoImpl();
                break;
            }
            default:
                break;
        }
        return iDao.doInvoke(genericRequest, bexConfig);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
