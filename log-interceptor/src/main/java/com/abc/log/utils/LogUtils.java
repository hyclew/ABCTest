package com.abc.log.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class LogUtils {

    private static int REDIS_EXPIRED_TIME = 24 * 60 * 60;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public String show(String s) {
        System.out.println(s + ".log");
        int min = Math.min(3, 4);
        return "abc";
    }

    public void addToRedis(String id, String msg) {
        stringRedisTemplate.opsForValue().set(id, msg, REDIS_EXPIRED_TIME, TimeUnit.SECONDS);
    }

    public void compareOnce() {
        String key1 = stringRedisTemplate.randomKey();
        String[] split = key1.split("-");
        String id = split[0];
        String type = split[1];

        String key2 = "";

        String json1, json2;
        json1 = stringRedisTemplate.opsForValue().get(key1);

        if ("GL".equals(type)) {
            key2 = id + "-" + "SP";
        } else {
            key2 = id + "-" + "GL";
        }

        json2 = stringRedisTemplate.opsForValue().get(key2);

        if (StringUtils.isEmpty(json1) || StringUtils.isEmpty(json2)) {
            return;
        }

        // 比较 json1 json2
        System.out.println(key1 + ":" + json1);
        System.out.println(key2 + ":" + json2);
        boolean result = CompareUtils.compare2Json(json1, json2);
        if (result) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
