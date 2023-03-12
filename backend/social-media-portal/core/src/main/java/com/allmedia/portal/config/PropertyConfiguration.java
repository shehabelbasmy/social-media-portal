package com.allmedia.portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource({"linkedin.properties","core.properties","facebook.properties","twitter.properties"})
@Configuration
public class PropertyConfiguration {

}
