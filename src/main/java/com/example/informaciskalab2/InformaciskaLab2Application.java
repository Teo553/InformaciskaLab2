package com.example.informaciskalab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class InformaciskaLab2Application {

    public static void main(String[] args) {
        SpringApplication.run(InformaciskaLab2Application.class, args);
    }

}