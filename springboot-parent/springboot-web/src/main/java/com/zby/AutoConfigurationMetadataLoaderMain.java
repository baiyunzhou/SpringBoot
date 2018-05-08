package com.zby;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * @author zby
 * @description EnableAutoConfiguration注解使用了Import(EnableAutoConfigurationImportSelector.class)注解，加载了以下东西
 *
 */
public class AutoConfigurationMetadataLoaderMain {

	protected static final String PATH = "META-INF/" + "spring-autoconfigure-metadata.properties";

	public static void main(String[] args) {
		loadMetadata(null, PATH).entrySet().stream().forEach(o -> System.out.println("---" + o));
	}

	static Properties loadMetadata(ClassLoader classLoader, String path) {
		try {
			Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(path) : ClassLoader.getSystemResources(path));
			Properties properties = new Properties();
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				System.out.println(url);
				properties.putAll(PropertiesLoaderUtils.loadProperties(new UrlResource(url)));
			}
			return properties;
		} catch (IOException ex) {
			throw new IllegalArgumentException("Unable to load @ConditionalOnClass location [" + path + "]", ex);
		}
	}

}
