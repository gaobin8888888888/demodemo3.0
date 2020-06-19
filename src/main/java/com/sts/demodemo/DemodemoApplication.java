package com.sts.demodemo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sts.demodemo"})
@MapperScan("com.sts.demodemo.dao")
public class DemodemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemodemoApplication.class, args);
    }

}
