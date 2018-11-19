package com.zby;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class SpringApplicationMain {

	public static void main(String[] args) {
		// SpringApplication.run(SpringBootMain.class, args);等同于下面的方法
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplication((ResourceLoader) null,
				SpringApplicationMain.class).run(args);
		System.out.println(configurableApplicationContext);
	}

}