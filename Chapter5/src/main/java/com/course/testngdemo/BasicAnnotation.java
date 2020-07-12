package com.course.testngdemo;

import org.testng.*;
//import org.testng.annotations.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//先后顺序：Suite,Test,Class,Groups,Method

public class BasicAnnotation {
    @Test(groups = "group1")
    public void test1(){
        System.out.println("    do test1() from group1...");
        Assert.assertTrue(true);}

    @Test(groups="group1")
    public void test11()
    { System.out.println("    do test11() from group1...");
        Assert.assertTrue(true); }

    @Test(groups="group2")
    public void test2()
    {System.out.println("    do test2() from group2....");
        Assert.assertTrue(true); }

    @BeforeTest
    public void beforeTest()
    {System.out.println("2BeforeTest");}

    @AfterTest
    public void afterTest()
    {System.out.println("2AfterTest");}

    @BeforeClass
    public void beforeClass()
    {System.out.println("3BeforeClass");}

    @AfterClass
    public void afterClass()
    {System.out.println("3AfterClass"); }

    @BeforeSuite
    public void beforeSuite()
    {System.out.println("1BeforeSuite"); }

    @AfterSuite
    public void afterSuite()
    {System.out.println("1AfterSuite"); }

    //只对group1有效，即test1和test11
    @BeforeGroups(groups="group1")
    public void beforeGroups()
    {System.out.println("4BeforeGroups(groups=\"group1\")"); }

    //只对group1有效，即test1和test11
    @AfterGroups(groups="group1")
    public void afterGroups()
    {System.out.println("4AfterGroups(groups=\"group1\")"); }

    @BeforeMethod
    public void beforeMethod()
    {System.out.println("  5BeforeMethod"); }

    @AfterMethod
    public void afterMethod()
    {System.out.println("  5AfterMethod"); }
}