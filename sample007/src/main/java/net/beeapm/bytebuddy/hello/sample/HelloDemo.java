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
        //int a = 1/0;
        try {
            Thread.sleep(10);
        }catch (Exception e){

        }
        return say;
    }

    public String sayHello(){
        System.out.println("---------------------------no params-------->sayHello");
        return null;
    }

    public static void print(String context){
        try {
            context = context.concat("--555---");
            Thread.sleep(15);
        }catch (Exception e){

        }
        System.out.println("  HelloDemo::print=======================>"+context);
    }

    public static void main(String[] args) {
        String str = "tommmm";
        HelloDemo.print(str);
        System.out.println(str);
    }
}
