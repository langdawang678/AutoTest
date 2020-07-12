package com.course.testngdemo.groups;

import org.testng.annotations.*;

public class GroupsOnMethod {
    @Test(groups = {"1","123"})
    public void testCase1(){
        System.out.println("测试方法1");
    }
    @Test(groups = {"2","123"})
    public void testCase2(){
        System.out.println("测试方法2");
    }
    @Test(groups = {"3","123"})
    public void testCase3(){
        System.out.println("测试方法3");
    }
    @Test(groups = "45")
    public void testCase4(){
        System.out.println("测试方法4");
    }
    @Test(groups = "45")
    public void testCase5(){
        System.out.println("测试方法5");
    }
    @Test
    public void testCase6(){
        System.out.println("测试方法6");
    }

    @BeforeGroups("45")
    public void beforeGroups45(){
        System.out.println("在groups45前运行");
    }

    @AfterGroups("45")
    public void afterGroups45(){
        System.out.println("在groups后45运行");
    }
    @BeforeGroups("1")
    public void beforeGroups1(){
        System.out.println("在groups1前运行");
    }

    @AfterGroups("1")
    public void afterGroups1(){
        System.out.println("在groups1后运行");
    }
}
