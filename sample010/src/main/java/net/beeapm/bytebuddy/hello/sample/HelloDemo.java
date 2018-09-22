package net.beeapm.bytebuddy.hello.sample;


public class HelloDemo {
    public String methodA(){
        System.out.println("------------------->done methodA");
        return "you are right!";
    }

    public Integer methodB(){
        System.out.println("------------------->done methodB");
        return 100;
    }

    public int methodC(){
        System.out.println("------------------->done methodC");
        return 100;
    }

    public boolean methodD(){
        System.out.println("------------------->done methodD");
        return true;
    }

    public int[] methodE(){
        System.out.println("------------------->done methodD");
        return new int[]{1,2};
    }
}
