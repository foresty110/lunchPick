package kr.sparta.backend1.lunch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "kr.sparta.backend1.lunch.mapper")
public class BackendBasic1Application {

    public static void main(String[] args) {
        SpringApplication.run(BackendBasic1Application.class, args);
    }

}
