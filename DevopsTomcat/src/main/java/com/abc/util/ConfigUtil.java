package com.abc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtil {

    private static String serverPort;

    @Autowired
    public void setServerPort(String serverPort){
        ConfigUtil.serverPort = serverPort;
    }

    public static void read(String key) {

    }
}