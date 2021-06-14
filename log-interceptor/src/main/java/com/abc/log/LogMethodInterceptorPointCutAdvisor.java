package com.abc.log;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

@Configuration
public class LogMethodInterceptorPointCutAdvisor extends AbstractPointcutAdvisor {

    @Autowired
    private LogMethodInterceptor interceptor;

    private Pointcut pointcut;

//    private final StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {
//        @Override
//        public boolean matches(Method method, Class<?> targetClass) {
//            return method.isAnnotationPresent(LogAndWarn.class) || targetClass.isAnnotationPresent(LogAndWarn.class);
//        }
//    };


    @PostConstruct
    void init() {
        pointcut = new AnnotationMatchingPointcut(Controller.class, ResponseBody.class);
        //interceptor.setOrder(interceptor.getOrder());
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return interceptor;
    }
}
