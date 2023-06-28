package com.cursoudemy.microservicios.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate() {
		//Es un cliente HTTP para poder acceder a recursos que estan en otros microservicios
		return new RestTemplate();
	}
}
