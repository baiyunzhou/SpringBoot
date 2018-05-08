package com.zby;

public class DeduceMainApplicationClassMain {

	public static void main(String[] args) {
		System.out.println(deduceMainApplicationClass());

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
