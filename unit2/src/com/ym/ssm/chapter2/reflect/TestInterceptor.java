package com.ym.ssm.chapter2.reflect;

public class TestInterceptor {
    public static void main(String[] args) {
        test();
        HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),
                "com.ym.ssm.chapter2.reflect.MyInterceptor");
        proxy.sayHelloWorld();
    }
    public static void test() {

        HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),
                "com.ym.ssm.chapter2.reflect.Interceptor1");
        HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxy.bind(proxy1,
                "com.ym.ssm.chapter2.reflect.Interceptor2");
        HelloWorld proxy3 = (HelloWorld) InterceptorJdkProxy.bind(proxy2,
                "com.ym.ssm.chapter2.reflect.Interceptor3");
        proxy3.sayHelloWorld();
    }
}
