package net.beeapm.bytebuddy.hello;

import net.beeapm.bytebuddy.hello.sample.HelloDemo;
import net.beeapm.bytebuddy.hello.sample.HelloUtils;

public class MainTest006 {
    public static void main(String[] args) throws Exception {
        HelloDemo hi = new HelloDemo();
        hi.sayHello("tom");
        System.out.println("\n---------------------------------------------------------------------------------------");
        HelloUtils.print("Tom is a stupid cat");
    }
}