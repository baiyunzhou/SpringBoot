package com.zby;

import java.util.List;

import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.core.io.support.SpringFactoriesLoader;

public class RetrieveSpringBootExceptionReporterMain {

	public static void main(String[] args) {
		List<String> springBootExceptionReporterNames = SpringFactoriesLoader.loadFactoryNames(SpringBootExceptionReporter.class, null);
		System.out.println("读取位置：" + SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION);
		System.out.println("SpringBootExceptionReporter:" + springBootExceptionReporterNames);
	}
}
// 读取位置：META-INF/spring.factories
// SpringBootExceptionReporter:[org.springframework.boot.diagnostics.FailureAnalyzers]