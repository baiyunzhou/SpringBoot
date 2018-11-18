package com.zby;

import org.springframework.boot.Banner;
import org.springframework.boot.ImageBanner;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class BannerMain {

	public static void main(String[] args) {
		Resource resource = new DefaultResourceLoader().getResource("classpath:fo.jpg");
		Banner banner = new ImageBanner(resource);
		banner.printBanner(new StandardEnvironment(), null, System.out);
	}

}
