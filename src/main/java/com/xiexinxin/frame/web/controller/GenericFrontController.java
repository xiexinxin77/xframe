package com.xiexinxin.frame.web.controller;

import com.xiexinxin.frame.holder.ApplicationContextHolder;
import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.modal.GenericServiceResult;
import com.xiexinxin.frame.service.IService;
import com.xiexinxin.frame.web.parser.IGenericParser;
import com.xiexinxin.frame.web.parser.impl.JSONRequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GenericFrontController {

    @Autowired
    private IService iService;

    @RequestMapping("/")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IGenericParser genericParser = ApplicationContextHolder.getContext().getBean(JSONRequestParser.class);
        GenericServiceRequest genericServiceRequest = genericParser.doRequestParser(request, true);
        GenericServiceResult result = iService.doService(genericServiceRequest);
        return result.buildJSONString();
    }
}
