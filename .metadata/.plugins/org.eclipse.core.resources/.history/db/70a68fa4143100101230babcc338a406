package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coderhouse.models.User;
import com.coderhouse.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

    
	@Autowired
	private UserService userService;
	
	
	@Operation(summary = "Obtener la lista de usuarios")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente", content = {
	        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
	    }),
	    @ApiResponse(responseCode = "404", description = "No se encontraron usuarios", content = 
	        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> getAllUsers(){
		try {
			List<User> users = userService.getAllUsers();
			return ResponseEntity.ok(users); //200
		}catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //404
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build(); //500
		}
	}
	
	@Operation(summary = "Obtener un usuario por ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = {
	        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
	    }),
	    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = 
	        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> getUserById(String id){
		try {
			User user = userService.getUserById(id);
			return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build(); //500
		}
	}
	
	
	@Operation(summary = "Registrar un nuevo usuario")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente", content = {
	        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
	    }),
	    @ApiResponse(responseCode = "404", description = "Datos inválidos o entidad no encontrada", content = 
	        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	
	@PostMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> addUser(@RequestBody User user) {	
		
		try {
			User newUser = userService.addUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(newUser); //201
		}catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //404
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build(); //500
		}
	}

	
	@Operation(summary = "Editar un usuario por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario editado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
			}),
			@ApiResponse(responseCode = "404", description = "Error al intentar editar un usuario", content = 
			@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content
			(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	
	@PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> updateUserById(@PathVariable String id,@RequestBody User user) {	
		
		try {
			user.setId(id);  
			User updateUser = userService.updateUser(user);
			return ResponseEntity.ok(updateUser); //200
		}catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //404
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build(); //500

		}
	}
	
	
	@Operation(summary = "Eliminar un usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente", content = {
				@Content() }),
			@ApiResponse(responseCode = "404", description = "Error al intentar eliminar el usuario", content = 
			@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content
			(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable String id) {	
		
		try {
			userService.deleteUserById(id);  
			return ResponseEntity.noContent(). build(); //204
		}catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //404
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build(); //500

		}
	}
}
	





















