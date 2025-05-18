package com.coderhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.TimeResponseDTO;
import com.coderhouse.services.FechaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/fecha")
public class FechaController {
	
	@Autowired
	private FechaService fs;
	
	private int contadorDeInvocaciones = 0;
			
	private String ultimaFechaMostrada = "N/A";
	
	
	@Operation(summary = "Obtener la fecha y hora actual del sistema")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Fecha y hora obtenidas correctamente", content = {
	        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
	    }),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	
	@GetMapping
	public ResponseEntity<String> obtenerfechaActual(){
		contadorDeInvocaciones++;
		TimeResponseDTO fechaActual = fs.obtenerFechaYHoraActuales();
		
		String message = String.format(
				"Fecha actual: %s %s %d/nHora: %s/nNúmero de Invocaciones: %d/Ultima fecha Mostrada: %s",
				
				fechaActual.getDayOfWeek(),
				fechaActual.getMonth(),
				fechaActual.getDay(),
				fechaActual.getYear(),
				fechaActual.getTime(),
				contadorDeInvocaciones,
				ultimaFechaMostrada);
		
		ultimaFechaMostrada = String.format(
				"%s %s %d, %d %s",
				fechaActual.getDayOfWeek(),
				fechaActual.getMonth(),
				fechaActual.getDay(),
				fechaActual.getYear(),
				fechaActual.getTime()
				);
		return ResponseEntity.ok(message);
	}

}

















