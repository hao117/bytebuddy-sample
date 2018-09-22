package net.beeapm.bytebuddy.hello;

import net.beeapm.bytebuddy.hello.sample.HelloDemo;

public class MainTest010 {
    public static void main(String[] args) throws Exception {
        HelloDemo hi = new HelloDemo();
        System.out.println("------------------------------------------------------------------");
        hi.methodA();
        System.out.println("------------------------------------------------------------------");
        hi.methodB();
        System.out.println("------------------------------------------------------------------");
        hi.methodC();
        System.out.println("------------------------------------------------------------------");
        hi.methodD();
        System.out.println("------------------------------------------------------------------");
        hi.methodE();
    }
}