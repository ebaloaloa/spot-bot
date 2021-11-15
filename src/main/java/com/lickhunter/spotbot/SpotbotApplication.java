package com.lickhunter.spotbot;

import com.lickhunter.spotbot.controllers.ApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpotbotApplication {

	@Autowired
	private ApplicationController applicationController;
	public static void main(String[] args) {
		SpringApplication.run(SpotbotApplication.class, args);
	}
}
