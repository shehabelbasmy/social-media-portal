package com.socialmedia.portal.security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties(prefix = "rsa")
@Data
@Configuration
public class RsaKeyProperties {

	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
}
