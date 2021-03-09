package com.siyi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.annotation.Controller;

import java.lang.reflect.Method;

@Slf4j
@Aspect(pointcut = "within(com.siyi.controller.frontend.*)")
@Order(10)
public class ControllerInfoRecordAspect extends DefaultAspect {
    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("ControllerInfoRecordAspect --- before");
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        log.info("ControllerInfoRecordAspect --- afterReturning");
        return returnValue;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {
        log.info("ControllerInfoRecordAspect --- afterThrowing");
    }
}
