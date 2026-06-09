package com.learn.productapp.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

//@OpenAPIDefinition
@OpenAPIDefinition(info =@Info(title ="Product API Definition", 
								version="v1",
								description = "API Documentation for the Product Service",
								termsOfService = "https://example.com/termms",
contact = @Contact(
		name="Product Support Team",
		email = "support@productservice.com",
		url = "https://productservice.com"),
license=@License(
		name="Apache 2.0" , 
		url ="http://www.apache.org/lienses/LICENSE-2.0.html"
)),
servers = {
		@Server(url="http://localhost:9095",description="Local Deelopment Serve"),
		@Server(url="https://api.productservice.com",
		description = "Production Server")
},
security = @SecurityRequirement(name="bearerAuth")
)
@Configuration
public class ApiConfig {
}
