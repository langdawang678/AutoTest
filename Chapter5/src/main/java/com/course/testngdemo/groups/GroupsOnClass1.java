package com.course.testngdemo.groups;

import org.testng.annotations.Test;

@Test(groups = "12")
public class GroupsOnClass1 {
    public void test1(){
        System.out.println("GroupsOnClass1中的test1方法");
    }
    public void test11(){
        System.out.println("GroupsOnClass1中的test11方法");
    }
}
