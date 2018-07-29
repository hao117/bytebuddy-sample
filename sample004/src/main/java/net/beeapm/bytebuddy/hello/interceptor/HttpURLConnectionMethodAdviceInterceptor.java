package net.beeapm.bytebuddy.hello.interceptor;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * Created by yuan on 2018/4/23.
 */

public class HttpURLConnectionMethodAdviceInterceptor {
    //jdk的类，没法设置inline = false，inlined没法debug
    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin Method m, @Advice.This Object ths) throws Throwable {
        System.out.println("---[BEGIN] HttpURLConnectionMethodAdviceInterceptor");
    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Local("start") long start) throws Throwable {
        System.out.println("---[END] HttpURLConnectionMethodAdviceInterceptor");
    }


}
