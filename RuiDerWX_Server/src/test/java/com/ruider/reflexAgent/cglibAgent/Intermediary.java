package com.ruider.reflexAgent.cglibAgent;

/*
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Intermediary implements MethodInterceptor {
    @Override
    public Object intercept (Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable{
        System.out.println("房东正在发放出租消息");
        Object interceptor = methodProxy.invokeSuper(proxy,args);
        System.out.println("房东已经发出消息");
        return interceptor;
    }
}
*/