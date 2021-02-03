package com.signifyd.techdebt;

import com.signifyd.techdebt.dao.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechDebtApp {

    public static void main(String[] args) {
        SpringApplication.run(TechDebtApp.class, args);
    }
}
