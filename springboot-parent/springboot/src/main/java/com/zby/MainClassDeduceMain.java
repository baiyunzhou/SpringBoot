package com.zby;

public class MainClassDeduceMain {

	public static void main(String[] args) {
		Class<?> mainClass = deduceMainApplicationClass();
		System.out.println("mainClass:" + mainClass);

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
		}
		return null;
	}
}
// mainClass:class com.zby.MainClassDeduceMain