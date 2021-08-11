package com.abc.util;

import com.abc.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
//@Lazy(true)
//@DependsOn({"SpringContextHolder"})
public class ConfigUtil {

    private static final String activeProfile;
    private static final String serverPort;

    static {
        Environment environment = SpringContextHolder.getApplicationContext().getEnvironment();
        System.out.println("static block:" + environment.getProperty("spring.profiles.active"));
        activeProfile = environment.getProperty("spring.profiles.active");
        serverPort = environment.getProperty("server.port");
    }

    private Environment env;

    public static String read() {
//        System.out.println(env.getProperty("spring.profiles.active"));
        System.out.println("serverPort:" + serverPort);
        System.out.println("activeProfile:" + activeProfile);
        return activeProfile;
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

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }


}
