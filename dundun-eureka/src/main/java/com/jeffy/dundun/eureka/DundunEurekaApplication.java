package com.jeffy.dundun.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DundunEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DundunEurekaApplication.class, args);
	}

}
