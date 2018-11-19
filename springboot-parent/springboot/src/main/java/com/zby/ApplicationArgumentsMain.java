package com.zby;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

public class ApplicationArgumentsMain {

	public static void main(String[] args) {
		String[] strs = new String[] { "--foo=bar", "--foo=baz", "--debug", "abc", "def" };
		ApplicationArguments applicationArguments = new DefaultApplicationArguments(strs);
		String[] sourceArgs = applicationArguments.getSourceArgs();
		System.out.println("sourceArgs:" + Arrays.toString(sourceArgs));
		// Option args 以--开头的参数
		Set<String> optionNames = applicationArguments.getOptionNames();
		System.out.println("optionNames:" + optionNames);
		for (String optionName : optionNames) {
			System.out.println(optionName + ":" + applicationArguments.getOptionValues(optionName));
		}
		// Non Option args 不是以--开头的参数
		List<String> nonOptionArgs = applicationArguments.getNonOptionArgs();
		System.out.println("nonOptionArgs:" + nonOptionArgs);
		System.out.println(applicationArguments.containsOption("foo"));
		System.out.println(applicationArguments.containsOption("abc"));
		System.out.println(applicationArguments.containsOption("zby"));
	}
}
// sourceArgs:[--foo=bar, --foo=baz, --debug, abc, def]
// optionNames:[debug, foo]
// debug:[]
// foo:[bar, baz]
// nonOptionArgs:[abc, def]
// true
// false
// false
