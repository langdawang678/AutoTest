package com.course.testngdemo.groups;

import org.testng.annotations.Test;

@Test(groups = "12")
public class GroupsOnClass2 {
    public void test2(){
        System.out.println("GroupsOnClass2中的test2方法");
    }
    public void test22(){
        System.out.println("GroupsOnClass2中的test22方法");
    }
}
