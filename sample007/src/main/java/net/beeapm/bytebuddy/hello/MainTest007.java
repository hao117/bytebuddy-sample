package net.beeapm.bytebuddy.hello;

import net.beeapm.bytebuddy.hello.sample.HelloDemo;

public class MainTest007 {
    public static void main(String[] args) throws Exception {
        HelloDemo hi = new HelloDemo();
        System.out.println("return result ====="+hi.sayHello("tom"));
        System.out.println("\n---------------------------------------------------------------------------------------");
        HelloDemo.print("Tom is a stupid cat");
    }
}