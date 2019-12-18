package com.xiexinxin.frame.web.parser.impl;

import com.alibaba.fastjson.JSON;
import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.util.EncryptUtils;
import com.xiexinxin.frame.web.parser.IGenericParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONRequestParser implements IGenericParser {
    @Override
    public GenericServiceRequest doRequestParser(Object value, boolean isEncrypt) {
        String jsonContent = "";
        if (value instanceof String) {
            jsonContent = (String) value;
        } else {
            HttpServletRequest request = (HttpServletRequest) value;
            try {
                StringBuffer sb = new StringBuffer();
                InputStream inputStream = request.getInputStream();
                byte[] buffer = new byte[1024];
                int count;
                while ((count = inputStream.read(buffer)) != -1) {
                    sb.append(new String(buffer, 0, count, "UTF-8"));
                }
                jsonContent = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (isEncrypt) {
            jsonContent = EncryptUtils.decrypt(jsonContent, "We@53&es&esT*7%s");
        }
        List<GenericServiceRequest> genericServiceRequests = parseRequestContent(jsonContent);
        return genericServiceRequests.get(0);
    }

    @Override
    public List<GenericServiceRequest> doRequestParserList(Object value, boolean isEncrypt) {
        String jsonContent = "";
        if (value instanceof String) {
            jsonContent = (String) value;
        } else {
            HttpServletRequest request = (HttpServletRequest) value;
            StringBuffer sb = new StringBuffer();
            try {
                InputStream is = request.getInputStream();
                byte[] buffer = new byte[1024];
                int count;
                while ((count = is.read(buffer)) != -1) {
                    sb.append(new String(buffer, 0, count, "UTF-8"));
                }
                jsonContent = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return parseRequestContent(jsonContent);
    }

    private List<GenericServiceRequest> parseRequestContent(String requestContent) {
        List<GenericServiceRequest> genericServiceRequests = new ArrayList<>();
        Map<String, List<Map<String, Map>>> requestMap = (Map) JSON.parse(requestContent);
        List<Map<String, Map>> requests = requestMap.get("REQUESTS");
        for (Map<String, Map> request : requests) {
            GenericServiceRequest genericServiceRequest = new GenericServiceRequest();
            genericServiceRequest.setRequestParams(request.get("PARAMS"));
            genericServiceRequests.add(genericServiceRequest);
        }
        return genericServiceRequests;
    }
}
