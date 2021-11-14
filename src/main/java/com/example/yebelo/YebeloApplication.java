package com.example.yebelo;

import com.example.yebelo.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;

@SpringBootApplication
@EnableAsync
public class YebeloApplication{
    public static void main(String[] args)  {
        SpringApplication.run(YebeloApplication.class, args);
    }
}
