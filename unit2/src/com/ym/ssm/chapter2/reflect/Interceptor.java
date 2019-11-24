package com.ym.ssm.chapter2.reflect;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public interface Interceptor {
    /**
     * @param proxy 代理对象
     * @param target 真实对象
     * @param method 方法
     * @param args 运行方法参数
     * */
    public boolean before(Object proxy, Object target, Method method,Object[] args);

    public void around(Object proxy,Object target,Method method,Object[] args);

    public void after(Object proxy,Object target,Method method,Object[] args);
}
