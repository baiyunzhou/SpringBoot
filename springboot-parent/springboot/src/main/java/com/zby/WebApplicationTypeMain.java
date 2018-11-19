package com.zby;

import java.lang.reflect.Method;

import org.springframework.boot.WebApplicationType;
import org.springframework.context.ApplicationContext;

public class WebApplicationTypeMain {

	public static void main(String[] args) throws Exception {
		Method deduceFromClasspath = WebApplicationType.class.getDeclaredMethod("deduceFromClasspath");
		deduceFromClasspath.setAccessible(true);
		WebApplicationType classpathWwebApplicationType = (WebApplicationType) deduceFromClasspath.invoke(null);
		System.out.println("classpathWwebApplicationType:" + classpathWwebApplicationType);
		Method deduceFromApplicationContext = WebApplicationType.class.getDeclaredMethod("deduceFromApplicationContext", Class.class);
		deduceFromApplicationContext.setAccessible(true);
		WebApplicationType applicationContexyWebApplicationType = (WebApplicationType) deduceFromApplicationContext.invoke(null,
				ApplicationContext.class);
		System.out.println("applicationContextWebApplicationType:" + applicationContexyWebApplicationType);
	}

}
// classpathWwebApplicationType:NONE
// applicationContextWebApplicationType:NONE