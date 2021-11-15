package com.lickhunter.spotbot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application")
@PropertySources({
        @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:application.properties", ignoreResourceNotFound = true)
})
public class ApplicationProperty {

    private String key;
    private String secret;
}
