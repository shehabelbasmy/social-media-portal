package com.allmedia.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
@PropertySource({"linkedin.properties","core.properties","facebook.properties","twitter.properties"})
public class SocialMediaPortal {
	
	public static void main(String[] args) {
		SpringApplication.run(SocialMediaPortal.class, args);
	}
	
}
