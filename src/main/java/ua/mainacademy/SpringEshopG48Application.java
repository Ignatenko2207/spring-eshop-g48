package ua.mainacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringEshopG48Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringEshopG48Application.class, args);
    }

}
