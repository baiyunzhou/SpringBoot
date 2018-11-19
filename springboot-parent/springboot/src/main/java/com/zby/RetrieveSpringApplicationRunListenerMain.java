package com.zby;

import java.util.List;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.io.support.SpringFactoriesLoader;

public class RetrieveSpringApplicationRunListenerMain {

	public static void main(String[] args) {
		List<String> applicationListenerNames = SpringFactoriesLoader.loadFactoryNames(SpringApplicationRunListener.class, null);
		System.out.println("读取位置：" + SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION);
		System.out.println("SpringApplicationRunListener:" + applicationListenerNames);

	}
}

// 读取位置：META-INF/spring.factories
// SpringApplicationRunListener:[org.springframework.boot.context.event.EventPublishingRunListener]
