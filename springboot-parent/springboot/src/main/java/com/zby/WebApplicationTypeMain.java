package com.zby;

import org.springframework.context.ApplicationContext;

public class WebApplicationTypeMain {

	public static void main(String[] args) {
		WebApplicationType classpathWwebApplicationType = WebApplicationType.deduceFromClasspath();
		System.out.println("classpathWwebApplicationType:" + classpathWwebApplicationType);
		WebApplicationType applicationContexyWebApplicationType = WebApplicationType.deduceFromApplicationContext(ApplicationContext.class);
		System.out.println("applicationContextWebApplicationType:" + applicationContexyWebApplicationType);
	}

}
// classpathWwebApplicationType:NONE
// applicationContextWebApplicationType:NONE