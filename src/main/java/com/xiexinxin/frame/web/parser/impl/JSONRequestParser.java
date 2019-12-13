package com.xiexinxin.frame.web.parser.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.util.EncryptUtils;
import com.xiexinxin.frame.web.parser.IGenericParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
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
        return null;
    }

    @Override
    public List<GenericServiceRequest> doRequestParserList(Object value, boolean isEncrypt) {
        return null;
    }

    private List<GenericServiceRequest> parseRequestContent(String requestContent) {
//        Map<List> requestMap = (Map) JSON.parse(requestContent);\
        return null;
    }
}
