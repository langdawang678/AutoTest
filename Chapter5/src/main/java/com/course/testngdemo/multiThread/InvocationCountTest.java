package com.course.testngdemo.multiThread;

import org.testng.annotations.Test;

public class InvocationCountTest {

    @Test(invocationCount = 10,threadPoolSize = 3)
    //invocationCount默认是1，不加 threadPoolSize =3时，打印的线程id是同一个，因为线程池是一个，
    //顺序：线程工作有先后 ，无法控制线程执行顺序。
    //好处：用多线程为了调高case执行效率，不是为了性能测试。
    //注意：不要用跨线程的变量去关联，否则就加锁，增加复杂度。
    public void invocationCountTest(){
        System.out.println("1");
        System.out.printf("thread id : %s%n",Thread.currentThread().getId());
    }
}
