package net.beeapm.bytebuddy.hello.interceptor;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by yuan on 2018/4/1.
 */
public class HttpURLConnectionMethodInterceptor {
    @RuntimeType
    public static String intercept(@SuperCall Callable<String> zuper) throws Throwable {
        System.out.println("---[BEGIN] HttpURLConnectionMethodInterceptor");
        String ret = null;
        try {
            ret = zuper.call();
            System.out.println(ret);
        } catch (Throwable t) {
            throw t;
        } finally {
            System.out.println("---[END] HttpURLConnectionMethodInterceptor");
        }
        return ret;
    }
}
