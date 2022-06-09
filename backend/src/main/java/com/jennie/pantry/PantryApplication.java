package com.jennie.pantry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// runs the application

@SpringBootApplication
@EnableWebMvc
public class PantryApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(PantryApplication.class, args);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}