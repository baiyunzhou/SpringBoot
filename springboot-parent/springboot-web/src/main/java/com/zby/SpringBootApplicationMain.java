package com.zby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class SpringBootApplicationMain {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootApplicationMain.class);
		springApplication.run(args);
	}

}
