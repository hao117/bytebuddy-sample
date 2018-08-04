package net.beeapm.bytebuddy.hello;

import net.beeapm.bytebuddy.hello.sample.HelloDemo;
import net.beeapm.bytebuddy.hello.sample.HelloDemo2;
import net.beeapm.bytebuddy.hello.sample.HelloUtils;

public class MainTest006 {
    public static void main(String[] args) throws Exception {
        HelloDemo hi = new HelloDemo();
        HelloDemo2 hi2 = new HelloDemo2();
        hi.sayHello("tom");
        hi2.sayGood("cat");
        System.out.println("\n---------------------------------------------------------------------------------------");
        HelloUtils.print("Tom is a stupid cat");
    }
}