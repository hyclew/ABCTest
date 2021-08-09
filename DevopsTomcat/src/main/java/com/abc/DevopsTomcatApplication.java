package com.abc;

import com.abc.util.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DevopsTomcatApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DevopsTomcatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DevopsTomcatApplication.class, args);

        Environment environment = SpringContextHolder.getApplicationContext().getEnvironment();
        System.out.println(environment.getProperty("spring.profiles.active"));
    }
}
