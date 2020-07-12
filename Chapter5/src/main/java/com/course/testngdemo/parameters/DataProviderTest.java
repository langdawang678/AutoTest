package com.course.testngdemo.parameters;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    //演示最基本的DataProvider
    @DataProvider(name = "ddt")
    public Object[][] dataProdiver() {
        return new Object[][]{
                {"zhangsan", 10},
                {"sili", 20},
                {"wangwu", 30},
        };
    }
    @Test(dataProvider = "ddt")
    public void dataProviderTest(String yourName, int age) {
        System.out.println("yourName=" + yourName + "; age=" + age);
    }


    //如下演示估计测试方法名，选择DataProvider的参数
    @Test(dataProvider = "methoddata")
    public void test1(String yourName, int age) {
        System.out.println("yourName=" + yourName + "; age=" + age);
    }
    @Test(dataProvider = "methoddata")
    public void test2(String yourName, int age) {
        System.out.println("yourName=" + yourName + "; age=" + age);
    }

    @DataProvider(name = "methoddata")
    public Object[][] methodDataTest(Method method) {
        //这里的入参是函数名，引入了反射 import java.lang.reflect.Method;
        Object[][] result = null;
        if (method.getName().equals("test1")) {
            result = new Object[][]{
                    {"chen", 11},
                    {"zhao", 12},
            };
        } else if (method.getName().equals("test2")) {
            result = new Object[][]{
                    {"li", 20},
                    {"wang", 30},
            };
        }
        return result;
    }
}

