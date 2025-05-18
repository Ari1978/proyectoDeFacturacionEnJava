package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.web.ErrorResponse;
>>>>>>> cbe5526 ("Commit inicial")
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD

import com.coderhouse.dto.RegistroDePacienteDTO;
import com.coderhouse.models.Paciente;

import com.coderhouse.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
=======
import com.coderhouse.dto.RegistroDePacienteDTO;
import com.coderhouse.models.Paciente;
import com.coderhouse.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;




@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Gestíon de pacientes", description = "Edpoints para gestionar pacientes")
>>>>>>> cbe5526 ("Commit inicial")

public class PacienteController {
	
	@Autowired
	private PacienteService pacienteService;
	
<<<<<<< HEAD
=======
	
	@Operation(summary = "Obtener las lista de los pacientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Pacientes obtenida correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))
			}),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	
>>>>>>> cbe5526 ("Commit inicial")
	@GetMapping(path = {"/", ""})
	public ResponseEntity<List<Paciente>> getAllPacientes() {
		try {
			  List<Paciente> pacientes = pacienteService.findAll(); 
				  return ResponseEntity.ok(pacientes); // 200
			  } catch (Exception err) {
				  return ResponseEntity.internalServerError().build(); //500
			  }
				  
<<<<<<< HEAD
		
	}
	
=======
	}
	
	
	
	@Operation(summary = "Obtener un paciente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Paciente obtenido correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))
			}),
			@ApiResponse(responseCode = "404", description = "Error al intentar obtener el paciente", content = 
			@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	
>>>>>>> cbe5526 ("Commit inicial")
	@GetMapping("/{pacienteId}")
	public ResponseEntity<?> getPacienteById(@PathVariable Long pacienteId){
		
		try {
			  
			Paciente paciente = pacienteService.findById(pacienteId);
			return ResponseEntity.ok(paciente); // 200
		} catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
<<<<<<< HEAD
=======
	
	
	@Operation(summary = "Registrar un nuevo paciente a una práctica, usando DTO")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Paciente registrado correctamente", content = {
	        @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))
	    }),
	    @ApiResponse(responseCode = "409", description = "Error al intentar registrar un paciente", content = 
	        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
	    @ApiResponse(responseCode = "404", description = "Datos inválidos o entidad no encontrada", content = 
	        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	
>>>>>>> cbe5526 ("Commit inicial")
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarPaciente(@RequestBody RegistroDePacienteDTO dto) {
		try {
			Paciente pacienteCreado = pacienteService.registrarPacienteAPracticas(dto);
<<<<<<< HEAD
			return ResponseEntity.ok(pacienteCreado); //201
=======
			return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCreado); //201
>>>>>>> cbe5526 ("Commit inicial")
			
		}catch (IllegalStateException err) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(err.getMessage()); //409
			
		}catch (IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch (Exception err) {
			return ResponseEntity.internalServerError().build(); //500
		}
	}
	
<<<<<<< HEAD
=======
	
	
	@Operation(summary = "Editar un paciente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Paciente editado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))
			}),
			@ApiResponse(responseCode = "404", description = "Error al intentar editar un paciente", content = 
			@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content
			(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	
>>>>>>> cbe5526 ("Commit inicial")
	@PutMapping("/{pacienteId}")
	public ResponseEntity<?> updatePacienteById(@PathVariable Long pacienteId,@RequestBody Paciente pacienteActualizado){
		
		if(pacienteId == null) {
			return ResponseEntity.badRequest().body("El ID del paciente no puiede ser null");
		}
		try {
			Paciente paciente = pacienteService.update(pacienteId, pacienteActualizado);
			return ResponseEntity.ok(paciente); // 200
			
		}catch (IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
	
	
		}catch (Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		
		}
	

	}
<<<<<<< HEAD
=======
	
	
	
	@Operation(summary = "Eliminar un paciente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Paciente eliminado correctamente", content = {
				@Content() }),
			@ApiResponse(responseCode = "404", description = "Error al intentar eliminar el paciente", content = 
			@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content
			(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	
>>>>>>> cbe5526 ("Commit inicial")
	@DeleteMapping("/{pacienteId}")
	public ResponseEntity<Void> deletePacienteById(@PathVariable Long pacienteId) {
		
		try {
			pacienteService.deleteById(pacienteId); 
			return ResponseEntity.noContent().build(); // 204
		
		}catch (IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404


		}catch (Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
	
		}
	}


}

















