package com.jeffy.dundun.design;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@MapperScan(basePackages={"com.jeffy.dundun.design.dao.mapper"})
@EnableDiscoveryClient
public class DundunDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(DundunDesignApplication.class, args);
	}

}
