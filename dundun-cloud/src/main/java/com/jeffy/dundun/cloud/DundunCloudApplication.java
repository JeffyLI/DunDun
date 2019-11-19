package com.jeffy.dundun.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages={"com.jeffy.dundun.cloud.dao.mapper"})
//@EnableDiscoveryClient
public class DundunCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(DundunCloudApplication.class, args);
	}

}
