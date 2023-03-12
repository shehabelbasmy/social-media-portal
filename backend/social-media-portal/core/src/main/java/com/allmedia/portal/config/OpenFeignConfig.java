package com.allmedia.portal.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.allmedia.portal.*")
@Configuration
public class OpenFeignConfig {

}
