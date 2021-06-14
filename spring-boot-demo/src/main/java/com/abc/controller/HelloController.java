package com.abc.controller;

import com.abc.log.domain.AvaLog;
import com.abc.log.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    LogUtils logUtils;

    @RequestMapping("/")
    @ResponseBody
    public Object Home(AvaLog log) {
//        String msg = logUtils.show("abc");
//        return "hello maven";
        return log;
    }

    @RequestMapping("/addGl")
    @ResponseBody
    public Object addGl(AvaLog log) {
        log.setType("GL");
        return log;
    }

    @RequestMapping("/addSp")
    @ResponseBody
    public Object addSp(AvaLog log) {
        log.setType("SP");
        return log;
    }

    @RequestMapping("/redis")
    @ResponseBody
    public String redis() {
        logUtils.compareOnce();
        return "hello redis";
    }

}