package com.course.testngdemo.groups;

import org.testng.annotations.Test;

@Test(groups = "3")
public class GroupsOnClass3 {
    public void test3(){
        System.out.println("GroupsOnClass3中的test3方法");
    }
    public void test33(){
        System.out.println("GroupsOnClass3中的test33方法");
    }
}
