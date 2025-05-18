package com.coderhouse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;



@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI custonOpenAPI () {
		return new OpenAPI()
				.info(new Info()
						.title("API REST Full | Java | Pestaciones Medicas")
						.version("3.0.0")

						.description("Esta API REST, desarrollada en Java con Spring Boot, permite la gestión integral "
                        		+ "pacientes y prestaciones médicas en un sistema médico. Ofrece endpoints para realizar "
                        		+ "operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre ambas entidades. "
                        		+ "A través de estos endpoints, es posible listar, registrar, consultar, modificar y "
                        		+ "eliminar tanto pacientes como preocupacionales.La API está documentada con Swagger/OpenAPI,"
                        		+ "lo que facilita su exploración, asegurando claridad en el uso de los endpoints disponibles.")
						.contact(new Contact()
								.name("Ariel Suarez")
								.email("arielsuarez538@gmail.com")
								.url("https://coderhouse.com.ar"))
						.license(new License()
								.name("Licencia")
								.url("https://github.com/Drako01/java---76340?tab=MIT-1-ov-file"))
						)

						.servers(List.of(
							new Server()
								.url("http://localhost:8080")
								.description("Servidor Local"),
							new Server()
								.url("http://localhost:8090")
								.description("Servidor de Testing")
						));
					
							
	}

}

//http://localhost:8080/swagger-ui/index.html











