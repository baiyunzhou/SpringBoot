package com.zby;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.diagnostics.FailureAnalyzer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

public class SpringFactoriesLoaderMain {
	public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

	public static void main(String[] args) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// List<String> factoryNames = SpringFactoriesLoader.loadFactoryNames(ApplicationContextInitializer.class, classLoader);
		getFactoryNames(ApplicationContextInitializer.class, classLoader);
		getFactoryNames(ApplicationListener.class, classLoader);
		getFactoryNames(SpringApplicationRunListener.class, classLoader);
		getFactoryNames(FailureAnalyzer.class, classLoader);
		getFactoryNames(EnableAutoConfiguration.class, classLoader);

	}

	public static void getFactoryNames(Class<?> clazz, ClassLoader classLoader) throws Exception {
		System.out.println("------" + clazz.getName());
		Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(FACTORIES_RESOURCE_LOCATION)
				: ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
		List<String> result = new ArrayList<String>();
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			// System.out.println(url);
			Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
			String factoryClassNames = properties.getProperty(clazz.getName());
			// System.out.println(factoryClassNames);
			List<String> classNames = Arrays.asList(StringUtils.commaDelimitedListToStringArray(factoryClassNames));
			classNames.stream().forEach(o -> System.out.println("---" + o));
			result.addAll(classNames);
		}
		System.out.println("==========----------==========");
	}
}
