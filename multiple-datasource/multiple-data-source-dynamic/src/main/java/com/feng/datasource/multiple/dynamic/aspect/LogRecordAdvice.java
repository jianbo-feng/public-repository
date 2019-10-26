package com.feng.datasource.multiple.dynamic.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志通知
 */
@Component
@Slf4j
@Aspect
public class LogRecordAdvice {

    /**
     *一 、利用注解的方式
     */
    @Before("@annotation(logRecord)")
    public void before(JoinPoint point, LogRecord logRecord){
        System.out.println("前置增强");
    }

    @AfterReturning(returning = "obj", pointcut="@annotation(logRecord)")
    public void after(JoinPoint point, Object obj, LogRecord logRecord){
        // obj 为接口的返回值（用于记录操作对象）, logRecord 为接口上打得注解
        System.out.println("后置增强");
        String record = logRecord.action();
        System.err.println("LogRecordAdvice.after()=> obj ：" + JSON.toJSONString(obj));
        System.err.println("LogRecordAdvice.after()=> point ：" + JSON.toJSONString(point.getArgs()));
        log.info(record);
    }

    @AfterThrowing("@annotation(logRecord)")
    public void afterThrowing(JoinPoint point, LogRecord logRecord){

    }


    /**
     *二、Pointcut的方式，完成对com.feng.datasource.multiple.dynamic.controller包下所有类增强
     */
    @Pointcut("within(com.feng.datasource.multiple.dynamic.controller..*)")
    public void adminValidatePointcut(){
    }

    @Before("adminValidatePointcut()")
    public void adminValidateBefore(){
        System.out.println("Pointcut的方式，前置增强");
    }

}
