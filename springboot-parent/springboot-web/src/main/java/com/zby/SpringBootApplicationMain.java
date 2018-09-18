package com.zby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class SpringBootApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationMain.class, args);
	}

}
