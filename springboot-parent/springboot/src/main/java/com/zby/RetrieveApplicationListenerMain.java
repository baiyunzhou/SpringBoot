package com.zby;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.core.io.support.SpringFactoriesLoader;

public class RetrieveApplicationListenerMain {

	public static void main(String[] args) {
		List<String> applicationListenerNames = SpringFactoriesLoader.loadFactoryNames(ApplicationListener.class, null);
		System.out.println("读取位置：" + SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION);
		System.out.println("ApplicationListener:" + applicationListenerNames);

	}
}

// 读取位置：META-INF/spring.factories
// ApplicationListener:[
// org.springframework.boot.ClearCachesApplicationListener,
// org.springframework.boot.builder.ParentContextCloserApplicationListener,
// org.springframework.boot.context.FileEncodingApplicationListener,
// org.springframework.boot.context.config.AnsiOutputApplicationListener,
// org.springframework.boot.context.config.ConfigFileApplicationListener,
// org.springframework.boot.context.config.DelegatingApplicationListener,
// org.springframework.boot.context.logging.ClasspathLoggingApplicationListener,
// org.springframework.boot.context.logging.LoggingApplicationListener,
// org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener
// ]
