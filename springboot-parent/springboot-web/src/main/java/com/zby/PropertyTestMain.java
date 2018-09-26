package com.zby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import com.zby.properties.User;

@EnableConfigurationProperties(value = User.class)
@SpringBootApplication
public class PropertyTestMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(PropertyTestMain.class, args);
		User user = configurableApplicationContext.getBean(User.class);
		System.out.println(user);
	}

}
