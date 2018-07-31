package net.beeapm.bytebuddy.hello.interceptor;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * Created by yuan on 2018/7/29.
 */

/**
 *  注意：实例方法使用@Advice.This注解，静态方法使用@Advice.Origin 两者不能混用
 */
public class StaticMethodSpendAdviceInterceptor {
    @Advice.OnMethodEnter()
    public static void enter(@Advice.Local("startTime") Long startTime,@Advice.Origin Method m, @Advice.Origin Class clz) throws Throwable {
        System.out.println("---[BEGIN] InstanceMethodSpendAdviceInterceptor");
        System.out.println("  ----------"+clz.getName());
        startTime = System.currentTimeMillis();
    }

    /**
     * 获取异常，需要添加onThrowable = Throwable.class 和@Advice.Thrown Throwable t，如果没有异常t==null
     * @param startTime
     * @throws Throwable
     */
    @Advice.OnMethodExit()
    public static void exit(@Advice.Local("startTime") Long startTime) throws Throwable {
        System.out.println("  ==============>spend="+(System.currentTimeMillis()-startTime));
        System.out.println("---[END] InstanceMethodSpendAdviceInterceptor");
    }

}
