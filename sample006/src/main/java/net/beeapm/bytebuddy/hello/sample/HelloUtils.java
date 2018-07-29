package net.beeapm.bytebuddy.hello.sample;

/**
 * Created by yuan on 2018/7/29.
 */
public class HelloUtils {
    public static void print(String context){
        try {
            Thread.sleep(15);
        }catch (Exception e){

        }
        System.out.println("  HelloUtils::print=======================>"+context);
    }
}
