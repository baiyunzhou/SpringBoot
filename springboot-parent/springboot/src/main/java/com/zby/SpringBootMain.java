package com.zby;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.StopWatch;

public class SpringBootMain {

	public static void main(String[] args) {
		// SpringApplication.run(SpringBootMain.class, args);等同于下面的方法
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplication((ResourceLoader) null, SpringBootMain.class)
				.run(args);
		System.out.println(configurableApplicationContext);
		// SpringApplication构造器要干的事
		System.out.println("webApplicationType:" + WebApplicationType.deduceFromClasspath());
		System.out.println(
				"ApplicationContextInitializer:" + SpringFactoriesLoader.loadFactoryNames(ApplicationContextInitializer.class, null));
		System.out.println("ApplicationListener:" + SpringFactoriesLoader.loadFactoryNames(ApplicationListener.class, null));
		System.out.println("deduceMainApplicationClass:" + deduceMainApplicationClass());
		// --end
		// run()方法要干的事
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
		System.setProperty("java.awt.headless", System.getProperty("java.awt.headless", Boolean.toString(true)));
		// SpringApplicationRunListener实现类必须提供方法类型为SpringApplication, String[]的构造方法
		System.out.println(
				"SpringApplicationRunListener:" + SpringFactoriesLoader.loadFactoryNames(SpringApplicationRunListener.class, null));
		// SpringApplicationRunListeners listeners = getRunListeners(args);
		// listeners.starting();
		// 封装参数
		// ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
		context = new AnnotationConfigApplicationContext();
		// SpringBootExceptionReporter实现类必须提供方法类型为ConfigurableApplicationContext的构造方法
		System.out
				.println("SpringBootExceptionReporter:" + SpringFactoriesLoader.loadFactoryNames(SpringBootExceptionReporter.class, null));
		// 把初始化出来的SpringBootExceptionReporter放到exceptionReporters
		exceptionReporters.add(null);
		// 准备上下文
		context.setEnvironment(new StandardEnvironment());
		// 调用ApplicationContextInitializer的init
		// listeners.contextPrepared(context);
		// 使用BeanDefinitionLoader加载spring元数据---------!!!!!这才是重点嘛
		//		BeanDefinitionLoader(BeanDefinitionRegistry registry, Object... sources) {
		//			Assert.notNull(registry, "Registry must not be null");
		//			Assert.notEmpty(sources, "Sources must not be empty");
		//			this.sources = sources;
		//			this.annotatedReader = new AnnotatedBeanDefinitionReader(registry);
		//			this.xmlReader = new XmlBeanDefinitionReader(registry);
		//			if (isGroovyPresent()) {
		//				this.groovyReader = new GroovyBeanDefinitionReader(registry);
		//			}
		//			this.scanner = new ClassPathBeanDefinitionScanner(registry);
		//			this.scanner.addExcludeFilter(new ClassExcludeFilter(sources));
		//		}
		// listeners.contextLoaded(context);
		// 刷新容器
		// 注册关闭容器的回调
		stopWatch.stop();
		// listeners.started(context);
		// 从容器中获取ApplicationRunner、CommandLineRunner实现类并执行
		// listeners.running(context);
		context.close();

	}

	private static Class<?> deduceMainApplicationClass() {
		try {
			StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
			for (StackTraceElement stackTraceElement : stackTrace) {
				if ("main".equals(stackTraceElement.getMethodName())) {
					return Class.forName(stackTraceElement.getClassName());
				}
			}
		} catch (ClassNotFoundException ex) {
			// Swallow and continue
		}
		return null;
	}
}

enum WebApplicationType {

	/**
	 * The application should not run as a web application and should not start an embedded web server.
	 */
	NONE,

	/**
	 * The application should run as a servlet-based web application and should start an embedded servlet web server.
	 */
	SERVLET,

	/**
	 * The application should run as a reactive web application and should start an embedded reactive web server.
	 */
	REACTIVE;

	private static final String[] SERVLET_INDICATOR_CLASSES = { "javax.servlet.Servlet",
			"org.springframework.web.context.ConfigurableWebApplicationContext" };

	private static final String WEBMVC_INDICATOR_CLASS = "org.springframework." + "web.servlet.DispatcherServlet";

	private static final String WEBFLUX_INDICATOR_CLASS = "org." + "springframework.web.reactive.DispatcherHandler";

	private static final String JERSEY_INDICATOR_CLASS = "org.glassfish.jersey.servlet.ServletContainer";

	private static final String SERVLET_APPLICATION_CONTEXT_CLASS = "org.springframework.web.context.WebApplicationContext";

	private static final String REACTIVE_APPLICATION_CONTEXT_CLASS = "org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext";

	static WebApplicationType deduceFromClasspath() {
		if (ClassUtils.isPresent(WEBFLUX_INDICATOR_CLASS, null) && !ClassUtils.isPresent(WEBMVC_INDICATOR_CLASS, null)
				&& !ClassUtils.isPresent(JERSEY_INDICATOR_CLASS, null)) {
			return WebApplicationType.REACTIVE;
		}
		for (String className : SERVLET_INDICATOR_CLASSES) {
			if (!ClassUtils.isPresent(className, null)) {
				return WebApplicationType.NONE;
			}
		}
		return WebApplicationType.SERVLET;
	}

	static WebApplicationType deduceFromApplicationContext(Class<?> applicationContextClass) {
		if (isAssignable(SERVLET_APPLICATION_CONTEXT_CLASS, applicationContextClass)) {
			return WebApplicationType.SERVLET;
		}
		if (isAssignable(REACTIVE_APPLICATION_CONTEXT_CLASS, applicationContextClass)) {
			return WebApplicationType.REACTIVE;
		}
		return WebApplicationType.NONE;
	}

	private static boolean isAssignable(String target, Class<?> type) {
		try {
			return ClassUtils.resolveClassName(target, null).isAssignableFrom(type);
		} catch (Throwable ex) {
			return false;
		}
	}

}