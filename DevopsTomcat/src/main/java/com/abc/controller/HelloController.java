package com.abc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Value("${server.port}")
    private String serverPort;


    @RequestMapping("/")
    @ResponseBody
    public String sayHello() {
        return "hello" + serverPort;
    }
}
