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
    public static void enter(@Advice.Local("startTime") Long startTime,
                             @Advice.Origin Method m,
                             @Advice.This Object ths,
                             @Advice.AllArguments Object[] allArgs,
                             @Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName,
                             @Advice.Origin("#s") String signature,
                             @Advice.FieldValue("context") String context
    ) throws Throwable {
        System.out.println("---[BEGIN] InstanceMethodSpendAdviceInterceptor");
        System.out.println(InstanceMethodSpendAdviceInterceptor.class.getName());
        startTime = System.currentTimeMillis();
        System.out.println("  --------"+ths.toString());
        System.out.println("  --------className="+className);
        System.out.println("  --------methodName="+methodName);
        System.out.println("  --------signature="+signature);
        System.out.println("  =================================context==>"+context);
    }

    /**
     * 获取异常，需要添加onThrowable = Throwable.class 和@Advice.Thrown Throwable t，如果没有异常t==null
     * @param startTime
     * @param t
     */
    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Local("startTime") Long startTime, @Advice.Return(readOnly = false) String result, @Advice.Thrown Throwable t){
        if(t != null){
            t.printStackTrace();
        }
        System.out.println("------------"+result);
        result = result + " @advice";
        System.out.println("  ==============>spend="+(System.currentTimeMillis()-startTime));
        System.out.println("---[END] InstanceMethodSpendAdviceInterceptor");
        //return  result;
    }


}
