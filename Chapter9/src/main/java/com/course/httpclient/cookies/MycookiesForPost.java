package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MycookiesForPost {

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
        }
    }
    //以上部分来源MyCookiesForGet的前半部分，因为需要获取到cookies

    //9-6添加，以下部分未实践（重点先理解思路）
    @Test(dependsOnMethods = "testGetCookies")
    public void testPostMethod() throws IOException {

        String uri = bundle.getString("test.post.with.cookies");
        //拼接最终的测试地址
        String testUrl = this.url + uri;

        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();

        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testUrl);

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串转化成为json对象
        JSONObject resultJson = new JSONObject(result);


        //获取到结果值
        String success = (String) resultJson.get("huhansan");
        String status = (String) resultJson.get("status");
        //具体的判断返回结果的值
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);

    }
}

