package com.zby;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.ConversionService;

public class ApplicationConversionServiceMain {

	public static void main(String[] args) {
		ConversionService conversionService = ApplicationConversionService.getSharedInstance();
		boolean stringToNumber = conversionService.canConvert(String.class, Number.class);
		System.out.println("stringToNumber:" + stringToNumber);
		Integer integer = conversionService.convert("2018", Integer.class);
		System.out.println(integer);
	}

}
// stringToNumber:true
// 2018
