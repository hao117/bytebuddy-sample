package net.beeapm.bytebuddy.hello;

import sun.net.www.protocol.http.HttpURLConnection;

import java.net.URL;

public class MainTest005 {
    public static void main(String[] args) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://www.baidu.com").openConnection();
        System.out.println("===========1======"+urlConnection.getRequestMethod());
    }
}