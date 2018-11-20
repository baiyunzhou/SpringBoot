package com.zby;

import java.util.List;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.io.support.SpringFactoriesLoader;

public class RetrieveApplicationContextInitializerMain {

	public static void main(String[] args) {
		List<String> applicationContextInitializerNames = SpringFactoriesLoader.loadFactoryNames(ApplicationContextInitializer.class, null);
		System.out.println("读取位置：" + SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION);
		System.out.println("ApplicationContextInitializer:" + applicationContextInitializerNames);

	}

}
// 读取位置：META-INF/spring.factories
// ApplicationContextInitializer:[
// org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer,
// org.springframework.boot.context.ContextIdApplicationContextInitializer,
// org.springframework.boot.context.config.DelegatingApplicationContextInitializer,
// org.springframework.boot.context.embedded.ServerPortInfoApplicationContextInitializer
// ]
