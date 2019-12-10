package com.xiexinxin.business.atom;

import com.xiexinxin.frame.business.atom.basic.FrameDao;
import com.xiexinxin.frame.business.atom.exchange.DataExchangeAssembly;
import com.xiexinxin.frame.modal.GenericResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestAtom {

    public GenericResult queryAcs(DataExchangeAssembly dataExchangeAssembly) {
        Map params = new HashMap();
        params.put("BUSI_CODE", "V0001");
        GenericResult genericResult = FrameDao.doBexCall("Z0001", params);
        return genericResult;
    }
}
