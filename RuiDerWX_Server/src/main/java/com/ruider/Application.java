package com.ruider;

import com.ruider.controller.NeedsManagementController;
import com.ruider.controller.UserController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.ruider.mapper")
@ComponentScan("com.ruider.service")
@ComponentScan(basePackageClasses = UserController.class)
@ComponentScan(basePackageClasses = NeedsManagementController.class)
@ServletComponentScan
@Configuration
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
