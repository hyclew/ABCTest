package com.abc.controller;

import com.abc.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Value("${server.port}")
    private String serverPort;

//    private String serverPort2 = ConfigUtil.read();


    @RequestMapping("/")
    @ResponseBody
    public String sayHello() {
//        System.out.println(serverPort2);
        System.out.println(ConfigUtil.read());
        return "hello" + serverPort;
    }
}
