package net.beeapm.bytebuddy.hello.interceptor;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * Created by yuan on 2018/7/29.
 */

/**
 * 注意：实例方法使用@Advice.This注解，静态方法使用@Advice.Origin 两者不能混用
 */
public class InstanceMethodSpendAdviceInterceptor2 {
    @Advice.OnMethodEnter()
    public static void enter(@Advice.Local("startTime") Long startTime, @Advice.Origin Method m, @Advice.This Object ths) throws Throwable {
        System.out.println("---[BEGIN] InstanceMethodSpendAdviceInterceptor2--"+ths.getClass().getName()+"."+m.getName());
        startTime = System.currentTimeMillis();
    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Local("startTime") Long startTime) throws Throwable {
        System.out.println("  ==============>spend="+(System.currentTimeMillis()-startTime));
        System.out.println("---[END] InstanceMethodSpendAdviceInterceptor2");
    }


}
