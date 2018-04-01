package net.beeapm.bytebuddy.hello;

import net.beeapm.bytebuddy.hello.sample.SayHelloDemo;

/**
 * Created by yuan on 2018/4/1.
 */
public class MainTest001 {
    public static void main(String[] args) {
        SayHelloDemo say = new SayHelloDemo();
        say.sayHello("Tom");
    }
}
