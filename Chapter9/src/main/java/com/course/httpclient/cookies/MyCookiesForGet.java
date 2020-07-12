/**9-5携带cookies信息访问get请求
 * 9-4获取cookies信息（response含cookies）
9-3章节配置优化方法（把url从代码里分离出来）
一、运行测试前，启动localhost的moco服务器：
    java -jar ./moco-runner-0.11.0-standalone.jar http -p 8899 -c startupWithCookies.json
二.
 基于9-2的服务端配置：startupWithCookies.json，
 配置了D:\Auto\Chapter9\src\main\resources\application.properties
 （还是请求地址都是uri=/getCookies）
 用ResourceBundle读取配置信息
 （import java.util.ResourceBundle;//读取配置文件的工具类）
 */
package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;//读取配置文件的工具类

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store; //9-5增加,存储cookie信息
    @BeforeTest
    public void test1() {
        bundle = ResourceBundle.getBundle("application");
        url = bundle.getString("test.url"); //test.url=http://localhost:8899
    }

    @Test
    public void testGetCookies () throws IOException {
        //从配置文件中拼接测试的url
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url+uri;

        //测试逻辑代码书写
        HttpGet get =new HttpGet(testUrl);
//        HttpClient client = new DefaultHttpClient();  //9-4修改
        DefaultHttpClient client = new DefaultHttpClient();    //9-4修改，HttpClient的对象没有getCookieStore()
        HttpResponse response = client.execute(get);  //返回=客户端执行get

        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);

        //9-4新增
        //获取cookies信息
        //CookieStore.store = client.getCookieStore(); //9-5修改
        this.store = client.getCookieStore(); //9-5 新增private CookieStore store;后修改为当前
        List<Cookie> cookieList = store.getCookies(); //getCookies返回的是List，因为会有很多，现在只配了1个
        for (Cookie cookie:cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name:" + name + "; value:" + value);
            //？这里如何在不删除json里的配置情况下，同时输出login 和status
            /*
                   "response":{
                   "cookies":{
                   "login":"true",
                   "status":"2000"
                  }
             */
        }
    }

    //9-5新增
    //把9-4拿到的cookies，带入这个/get/with/cookies请求中（startupWithCookies.json定义了该请求需要携带cookies才能访问成功）
    @Test(dependsOnMethods = "testGetCookies")
    public  void  testGetWithCookies() throws IOException {
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl = this.url+uri;
        HttpGet get =new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookies信息
        client.setCookieStore(store);

        HttpResponse response = client.execute(get);
        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+ statusCode);

        if (statusCode==200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }


    }
}
