package com.course;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@EnableScheduling
//之前10章节的@ComponentScan("com.course") //代表扫描哪些组件；现在不用了，以后都这么写
@SpringBootApplication//代表托管
public class Application {

    private  static ConfigurableApplicationContext context;

    public static void main(String[] args) {

        Application.context = SpringApplication.run(Application.class,args);
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }

}
