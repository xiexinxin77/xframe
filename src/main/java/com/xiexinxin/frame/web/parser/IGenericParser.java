package com.xiexinxin.frame.web.parser;

import com.xiexinxin.frame.modal.GenericServiceRequest;

import java.util.List;

public interface IGenericParser {

    GenericServiceRequest doRequestParser(Object value, boolean isEncrypt);

    List<GenericServiceRequest> doRequestParserList(Object value, boolean isEncrypt);
}
