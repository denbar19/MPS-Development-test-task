package com.denisenko.mps.mpsdevelopmenttesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
public class MpsDevelopmentTestTaskApplication {
	public static void main(String[] args) {
		SpringApplicationBuilder b = new SpringApplicationBuilder(MpsDevelopmentTestTaskApplication.class);
		b.headless(false);
		ConfigurableApplicationContext context = b.run(args);
		//SpringApplication.run(MpsDevelopmentTestTaskApplication.class, args);
	}
}
