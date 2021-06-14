package com.abc.log;


import com.abc.log.domain.AvaLog;
import com.abc.log.utils.JsonUtils;
import com.abc.log.utils.LogUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogMethodInterceptor implements MethodInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(LogMethodInterceptor.class);

    @Autowired
    LogUtils logUtils;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        logger.debug("methodInterceptor");
        Object ret = methodInvocation.proceed();

        try {
            String requestId = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-Request-Id");

            if (ret != null && ret.getClass() == AvaLog.class) {
                AvaLog log = (AvaLog) ret;

                AvaLog log2 = new AvaLog(log.getId(),log.getMsg(),"log");
                logUtils.addToRedis(requestId + "-" + log.getType(), JsonUtils.toJSon(log2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
}
