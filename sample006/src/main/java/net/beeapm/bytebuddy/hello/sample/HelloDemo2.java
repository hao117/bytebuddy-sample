package net.beeapm.bytebuddy.hello.sample;

/**
 * Created by yuan on 2018/7/29.
 */
public class HelloDemo2 extends HelloDemo{
    public String context = " is a cat!";
    public HelloDemo2(){
        System.out.println("init HelloDemo2");
    }
    public String sayGood(String name){
        String say = name + context;
        System.out.println("  HelloDemo::sayGood============>"+say);
        return say;
    }
}
