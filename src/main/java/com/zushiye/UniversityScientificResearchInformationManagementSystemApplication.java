package com.zushiye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.zushiye.mapper")
public class UniversityScientificResearchInformationManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityScientificResearchInformationManagementSystemApplication.class, args);
    }

}
