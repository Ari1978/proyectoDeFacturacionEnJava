package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.models.Turno;
import com.coderhouse.service.TurnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/a/turnos")
@Tag(name = "Gestión de turnos", description = "Endpoints para gestionar turnos médicos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Operation(summary = "Obtener un turno por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Turno encontrado correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Turno.class))
        }),
        @ApiResponse(responseCode = "404", description = "Turno no encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/{turnoId}")
    public ResponseEntity<?> getTurnoById(
        @PathVariable
        @Schema(description = "ID del turno a buscar", example = "1")
        Long turnoId) {
        try {
            Turno turno = turnoService.findById(turnoId);
            return ResponseEntity.ok(turno); // 200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    @Operation(summary = "Obtener todos los turnos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de turnos obtenida correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Turno.class, type = "array"))
        }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<Turno>> getAllTurnos() {
        try {
            List<Turno> turnos = turnoService.findAll();
            return ResponseEntity.ok(turnos); // 200
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    @Operation(summary = "Asignar un nuevo turno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Turno asignado correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Turno.class))
        }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/asignar-turno")
    public ResponseEntity<?> asignarTurno(
        @RequestBody
        @Schema(description = "Datos del turno a asignar")
        Turno turno) {
        try {
            Turno turnoAsignado = turnoService.save(turno);
            return ResponseEntity.status(HttpStatus.CREATED).body(turnoAsignado); // 201
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }
}
