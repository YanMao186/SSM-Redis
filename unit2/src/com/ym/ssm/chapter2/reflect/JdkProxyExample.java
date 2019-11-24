package com.ym.ssm.chapter2.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler {
    //真实对象
    private Object target = null;
    /**
     * @param target 真实对象
     * @return 代理对象
     * */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass()
                .getClassLoader(),target.getClass()
                .getInterfaces(),this);
    }
    /**
     * @param proxy 代理对象
     * @param method 当前调度方法
     * @param args 当前方法参数
     * @return 代理结果返回
     * @throws Throwable 异常
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法");
        System.out.println("在调度真实对象之前的服务");
        //相当于调用sayHelloWorld方法
        Object obj = method.invoke(target,args);
        System.out.println("在调度真实对象之后的服务");
        return obj;
    }
}
