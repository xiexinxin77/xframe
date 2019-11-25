package com.xiexinxin.frame.business;

import com.xiexinxin.frame.business.config.BusinessConfig;
import com.xiexinxin.frame.modal.GenericRequest;
import com.xiexinxin.frame.modal.GenericResult;

public interface IBusiness {

    GenericResult doBusiness(GenericRequest genericRequest);

    GenericResult doBusiness(GenericRequest genericRequest, BusinessConfig businessConfig);
}
