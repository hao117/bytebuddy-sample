package net.beeapm.bytebuddy.hello.sample;

/**
 * Created by yuan on 2018/7/29.
 */
public class HelloDemo {
    public String context = " is a cat!";
    public HelloDemo(){
        System.out.println("init");
    }
    public String sayHello(String name){
        String say = name + context;
        System.out.println("  HelloDemo::sayHello============>"+say);
        try {
            Thread.sleep(10);
        }catch (Exception e){

        }
        return say;
    }
}
