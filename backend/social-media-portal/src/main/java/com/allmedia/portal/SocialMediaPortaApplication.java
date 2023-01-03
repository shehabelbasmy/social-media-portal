package com.allmedia.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SocialMediaPortaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaPortaApplication.class, args);
	}

}
