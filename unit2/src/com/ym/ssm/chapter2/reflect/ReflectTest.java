package com.ym.ssm.chapter2.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) {
        ReflectTest r = new ReflectTest();
        r.reflect();

    }
    public Object reflect() {
        ReflectServiceImpl object = null;
        try {
            object = (ReflectServiceImpl) Class.forName("com.ym.ssm.chapter2.reflect.ReflectServiceImpl")
                    .newInstance();
            Method method = object.getClass().getMethod("sayHello", String.class);
            method.invoke(object, "张三");
        } catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
            ex.printStackTrace();
        }
        return object;
    }

}
