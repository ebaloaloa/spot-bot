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
@ConfigurationProperties(prefix = "strategy")
@PropertySources({
        @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:application.properties", ignoreResourceNotFound = true)
})
public class StrategyProperty {
    private Integer rebalanceRatio;
    private Integer rebalanceInterval;
    private Integer holdHours;
    private Integer altRank;
    private Integer galaxyScore;
}
