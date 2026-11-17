package com.pepito.ConfiguracionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfiguracionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfiguracionServiceApplication.class, args);
	}

}
