package com.gestaoDeCliente.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "http://localhost:3000")  // Configura CORS globalmente
public class WebConfig{
	
}
