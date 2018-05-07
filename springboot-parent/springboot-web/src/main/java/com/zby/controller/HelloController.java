package com.zby.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	// localhost/?name=ZBY
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String hello(String name) {
		return "Hello," + name + "!";
	}
}
