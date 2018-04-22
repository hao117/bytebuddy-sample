package net.beeapm.bytebuddy.hello.interceptor;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by yuan on 2018/4/1.
 */
public class SimpleConstructedMethodInterceptor2 {
    @RuntimeType
    public Object intercept(@This Object obj,
                            @AllArguments Object[] allArguments) throws Throwable {
        System.out.println("---[BEGIN] SimpleConstructedMethodInterceptor2");
        Object ret = null;
        try {
            System.out.println("    allArguments number = " + allArguments.length);
        } catch (Throwable t) {
            throw t;
        } finally {
            System.out.println("---[END] SimpleConstructedMethodInterceptor2");
        }
        return ret;
    }
}
