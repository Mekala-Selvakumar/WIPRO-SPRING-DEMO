package com.learn.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableDiscoveryClient
public class ApigatewayserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApigatewayserviceApplication.class, args);
	}
	@Bean
	public RouteLocator  gatewayRouter(RouteLocatorBuilder  builder) {
		return builder.routes()
				.route(r->r.path("/first/**").uri("lb://FIRSTSERVICE"))
				.route(r->r.path("/second/**").uri("lb://SECONDSERVICE"))
				.build();
	}
	
//	@Bean
//	public  RouterFunction<ServerResponse> gatewayRoutes(){
//		return  route("route-1")
//				.GET("/first/**",http())
//				.before(uri("lb://FIRSTSERVICE"))
//				
//				.GET("/second/**",http())
//				.before(uri("lb://SECONDSERVICE"))
//				
//				.build();
//
//   }

}
