package net.beeapm.bytebuddy.hello.sample;

/**
 * Created by yuan on 2018/4/1.
 */
public class HelloDemo {
    public String context = " is a cat";
    public HelloDemo(){
        System.out.println("init");
    }
    public String sayHello(String name){
        String say = name + context;
        System.out.println(say);
        return say;
    }
}
