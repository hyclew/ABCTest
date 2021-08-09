package com.abc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
//@Lazy(true)
//@DependsOn({"SpringContextHolder"})
public class ConfigUtil {

    private static String serverPort;
    private static String activeProfile;

    @Autowired
    private static Environment env;

    static {
        Environment environment = SpringContextHolder.getApplicationContext().getEnvironment();
        System.out.println(environment.getProperty("spring.profiles.active"));
        activeProfile = environment.getProperty("spring.profiles.active");
    }

//    @Value("${server.port}")
//    public void setServerPort (String serverPort){
//        ConfigUtil.serverPort = serverPort;
//    }
//
//    public ConfigUtil() {
//        System.out.println(env.getProperty("spring.profiles.active"));
//    }
//
//    @PostConstruct
//    public void init(){
//        System.out.println(env.getProperty("spring.profiles.active"));
//    }

    public static String read() {
//        System.out.println(env.getProperty("spring.profiles.active"));
        System.out.println(serverPort);
        System.out.println(activeProfile);
        return serverPort;
    }


}
