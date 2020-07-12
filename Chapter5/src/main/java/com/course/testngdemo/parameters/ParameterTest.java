package com.course.testngdemo.parameters;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {
    @Test
    @Parameters({"yourName","age"})
    public void parameterTest(String yourName,int age){
        System.out.println("yourName="+yourName+"; age="+age);
    }
}
