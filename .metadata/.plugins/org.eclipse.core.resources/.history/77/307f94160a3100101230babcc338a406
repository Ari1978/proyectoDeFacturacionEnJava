package com.coderhouse.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Modelo de usuarios")
public class User {
	
	@Schema(description = "ID del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private String id;
	
	@Schema(description = "Nombre del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Abel")
	private String nombre;
	
	@Schema(description = "Apellido del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Gonzalez")
	private String apellido;
	
	@Schema(description = "Email del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "example@example.com")
	private String email;
	
	@Schema(description = "Muestra la hora y fecha del registro del paciente")
	private String createdAt; 

}
