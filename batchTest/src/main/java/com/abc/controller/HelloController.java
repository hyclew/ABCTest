package com.abc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public Object Home(String in) {
//        String msg = logUtils.show("abc");
//        return "hello maven";
        return "ok";
    }



}