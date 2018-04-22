package net.beeapm.bytebuddy.hello;

import net.beeapm.bytebuddy.hello.sample.HelloDemo;

/**
 * Created by yuan on 2018/4/1.
 */
public class MainTest003 {
    public static void main(String[] args) {
        HelloDemo hi = new HelloDemo();
        hi.sayHello("Tom");
        System.out.println("====================================================================");
        HelloDemo hi2 = new HelloDemo("good");
        hi2.sayHello("Jack");
    }
}
