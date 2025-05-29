package com.coderhouse.controller;

import com.coderhouse.models.Factura;
import com.coderhouse.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@Tag(name = "Gestión de facturas", description = "Endpoints para gestionar facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Operation(summary = "Obtener todas las facturas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Factura.class, type = "array"))
        }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<Factura>> getAllFacturas() {
        try {
            List<Factura> facturas = facturaService.findAll();
            return ResponseEntity.ok(facturas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtener una factura por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Factura encontrada correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Factura.class))
        }),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/{facturaId}")
    public ResponseEntity<Factura> getFacturaById(
            @PathVariable
            @Schema(description = "ID de la factura", example = "1")
            Long facturaId) {
        try {
            Factura factura = facturaService.findById(facturaId);
            if (factura != null) {
                return ResponseEntity.ok(factura);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Crear una nueva factura")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Factura creada correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Factura.class))
        }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Factura> createFactura(
            @RequestBody
            @Schema(description = "Datos de la factura a crear")
            Factura factura) {
        try {
            Factura facturaCreada = facturaService.save(factura);
            return ResponseEntity.status(HttpStatus.CREATED).body(facturaCreada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Actualizar una factura existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Factura actualizada correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Factura.class))
        }),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping("/{facturaId}")
    public ResponseEntity<Factura> updateFactura(
            @PathVariable
            @Schema(description = "ID de la factura a actualizar", example = "1")
            Long facturaId,
            @RequestBody
            @Schema(description = "Datos actualizados de la factura")
            Factura facturaActualizada) {
        try {
            Factura factura = facturaService.update(facturaId, facturaActualizada);
            if (factura != null) {
                return ResponseEntity.ok(factura);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Eliminar una factura por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Factura eliminada correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @DeleteMapping("/{facturaId}")
    public ResponseEntity<?> deleteFactura(
            @PathVariable
            @Schema(description = "ID de la factura a eliminar", example = "1")
            Long facturaId) {
        try {
            facturaService.deleteById(facturaId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Factura no encontrada con ID: " + facturaId);
        }
    }
}
