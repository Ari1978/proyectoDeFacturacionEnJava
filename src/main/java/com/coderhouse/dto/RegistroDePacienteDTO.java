package com.coderhouse.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO Registro de paciente a una o varias prácticas")
public class RegistroDePacienteDTO {
	
	@Schema(description = "ID del paciente", example = "1")
	private Long pacienteId;
	
	@Schema(description = "IDs de las prácticas asociadas al paciente", example = "[1, 2, 3]")
	private List<Long> practicaIds;
}
