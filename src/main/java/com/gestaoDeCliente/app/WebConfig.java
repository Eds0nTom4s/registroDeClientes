package com.gestaoDeCliente.app;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**") // Permite CORS para todas as rotas da API
	                .allowedOrigins("http://localhost:3000") // Permite requisições do localhost:3000
	                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite os métodos necessários
	                .allowedHeaders("*") // Permite todos os cabeçalhos
	                .allowCredentials(true); // Permite enviar cookies e autenticação, se necessário
	    }
	
}
