package net.beeapm.bytebuddy.hello.interceptor;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by yuan on 2018/4/1.
 */
public class SimplePackageInstanceMethodInterceptor {
    @RuntimeType
    public Object intercept(@This Object obj,
                            @AllArguments Object[] allArguments,
                            @SuperCall Callable<?> zuper,
                            @Origin Method method) throws Throwable {
        System.out.println("---[BEGIN] SimplePackageInstanceMethodInterceptor");
        Object ret = null;
        try {
            System.out.println("    class name = " + obj.getClass().getName());
            System.out.println("    method name = " + method.getName());
            ret = zuper.call();
        } catch (Throwable t) {
            throw t;
        } finally {
            System.out.println("---[END] SimplePackageInstanceMethodInterceptor");
        }
        return ret;
    }
}
