package net.beeapm.bytebuddy.hello.interceptor;

import net.beeapm.bytebuddy.hello.sample.MyHandler;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

import java.lang.reflect.Method;

/**
 * Created by yuan on 2018/7/29.
 */

/**
 * 注意：实例方法使用@Advice.This注解，静态方法使用@Advice.Origin 两者不能混用
 */
public class MyAdvice {
    @Advice.OnMethodEnter()
    public static void exit(
            @Advice.Local("handler") MyHandler handler,
            @Advice.Origin("#t") String className,
            @Advice.Origin("#m") String methodName,
            @Advice.AllArguments Object[] allParams
            ) {
        System.out.println("  ---MyAdvice-----methodName="+methodName);
        handler = new MyHandler();
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(
            @Advice.Local("handler") MyHandler handler,
            @Advice.Origin("#t") String className,
            @Advice.Origin("#m") String methodName,
            @Advice.AllArguments Object[] allParams,
            @Advice.Thrown Throwable t,
            @Advice.Return Object returnValue) {
        System.out.println("  ----MyAdvice----returnValue="+returnValue);
    }




}
