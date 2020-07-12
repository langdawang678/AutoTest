package com.course.testngdemo;

import org.testng.annotations.Test;

public class ExpectedException {

    //这是一个失败的case，因为没有抛出预期的异常
    @Test(expectedExceptions = RuntimeException.class)
    public void test1(){
        System.out.println("//这是一个失败的case，因为没有抛出预期的异常");
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void test2(){
        System.out.println("捕获到异常，case通过");
        throw new RuntimeException();
    }
}
