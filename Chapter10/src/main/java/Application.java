//10-3 返回cookies信息的get接口开发 (10:51)
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication //代表托管
@ComponentScan("com.course") //代表扫描哪些组件，
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args); //固定写法，同demo
    }
}
