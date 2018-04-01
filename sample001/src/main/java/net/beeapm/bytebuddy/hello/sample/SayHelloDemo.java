package net.beeapm.bytebuddy.hello.sample;

/**
 * Created by yuan on 2018/4/1.
 */
public class SayHelloDemo {
    public String sayHello(String name){
        String say = "Hello " + name + " !";
        System.out.println(say);
        return say;
    }
}
