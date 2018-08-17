package net.beeapm.bytebuddy.hello.interceptor;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * Created by yuan on 2018/7/29.
 */

/**
 * 注意：实例方法使用@Advice.This注解，静态方法使用@Advice.Origin 两者不能混用
 */
public class InstanceMethodSpendAdviceInterceptor {
    @Advice.OnMethodEnter()
    public static void enter(@Advice.AllArguments Object[] allArgs,
                             @Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName,
                             @Advice.Origin("#s") String signature
                             ) throws Throwable {
        System.out.println("---[BEGIN] InstanceMethodSpendAdviceInterceptor");
        System.out.println("  --------className="+className);
        System.out.println("  --------methodName="+methodName);
        System.out.println("  --------signature="+signature);
    }




}
