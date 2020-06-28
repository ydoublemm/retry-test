package org.example;

/**
 * Created by Enzo Cotter on 2020/6/11.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringBootAppMain {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppMain.class,args);
    }
}
