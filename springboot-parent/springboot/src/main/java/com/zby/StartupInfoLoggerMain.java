package com.zby;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.concurrent.Callable;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

public class StartupInfoLoggerMain {
	private final Class<?> sourceClass = StartupInfoLoggerMain.class;

	private StringBuilder getStartupMessage() {
		StringBuilder message = new StringBuilder();
		message.append("Starting ");
		message.append(getApplicationName());
		message.append(getVersion(StartupInfoLoggerMain.class));
		message.append(getOn());
		message.append(getPid());
		message.append(getContext());
		return message;
	}

	private StringBuilder getRunningMessage() {
		StringBuilder message = new StringBuilder();
		message.append("Running with Spring Boot");
		message.append(getVersion(getClass()));
		message.append(", Spring");
		message.append(getVersion(ApplicationContext.class));
		return message;
	}

	private StringBuilder getStartedMessage(StopWatch stopWatch) {
		StringBuilder message = new StringBuilder();
		message.append("Started ");
		message.append(getApplicationName());
		message.append(" in ");
		message.append(stopWatch.getTotalTimeSeconds());
		try {
			double uptime = ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0;
			message.append(" seconds (JVM running for " + uptime + ")");
		} catch (Throwable ex) {
		}
		return message;
	}

	private String getApplicationName() {
		return (this.sourceClass != null) ? ClassUtils.getShortName(this.sourceClass) : "application";
	}

	private String getVersion(Class<?> source) {
		return getValue(" v", () -> source.getPackage().getImplementationVersion(), "");
	}

	private String getOn() {
		return getValue(" on ", () -> InetAddress.getLocalHost().getHostName());
	}

	private String getPid() {
		return getValue(" with PID ", () -> new ApplicationPid().toString());
	}

	private String getContext() {
		String startedBy = getValue("started by ", () -> System.getProperty("user.name"));
		String in = getValue("in ", () -> System.getProperty("user.dir"));
		ApplicationHome home = new ApplicationHome(sourceClass);
		String path = (home.getSource() != null) ? home.getSource().getAbsolutePath() : "";
		if (startedBy == null && path == null) {
			return "";
		}
		if (StringUtils.hasLength(startedBy) && StringUtils.hasLength(path)) {
			startedBy = " " + startedBy;
		}
		if (StringUtils.hasLength(in) && StringUtils.hasLength(startedBy)) {
			in = " " + in;
		}
		return " (" + path + startedBy + in + ")";
	}

	private String getValue(String prefix, Callable<Object> call) {
		return getValue(prefix, call, "");
	}

	private String getValue(String prefix, Callable<Object> call, String defaultValue) {
		try {
			Object value = call.call();
			if (value != null && StringUtils.hasLength(value.toString())) {
				return prefix + value;
			}
		} catch (Exception ex) {
			// Swallow and continue
		}
		return defaultValue;
	}

	public static void main(String[] args) {
		StartupInfoLoggerMain startupInfoLoggerMain = new StartupInfoLoggerMain();
		System.out.println("" + startupInfoLoggerMain.getStartupMessage());
		System.out.println("" + startupInfoLoggerMain.getRunningMessage());
	}
}
