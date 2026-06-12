package com.learn.eurekaserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaserverappApplication.class, args);
	}

}
