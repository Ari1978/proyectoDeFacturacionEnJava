package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.models.Categoria;
import com.coderhouse.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Obtener la lista de categorías")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))
        }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping(path = {"/", ""})
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        try {
            List<Categoria> categorias = categoriaService.findAll(); 
            return ResponseEntity.ok(categorias); // 200
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    @Operation(summary = "Obtener una categoría por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría obtenida correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))
        }),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/{categoriaId}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Long categoriaId){
        if (categoriaId <= 0) {
            return ResponseEntity.badRequest().body("El ID de la categoría no es válido"); // Validación de ID
        }
        try {
            Categoria categoria = categoriaService.findById(categoriaId);
            return ResponseEntity.ok(categoria); // 200
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build(); // 404
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    @Operation(summary = "Crear una nueva categoría")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoría registrada correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))
        }),
        @ApiResponse(responseCode = "400", description = "Error al intentar registrar una categoría", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        try {
            Categoria categoriaCreada = categoriaService.save(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada); // 201
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    @Operation(summary = "Editar una categoría por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría editada correctamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))
        }),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping("/{categoriaId}")
    public ResponseEntity<?> updateCategoriaById(@PathVariable Long categoriaId, @RequestBody Categoria categoriaActualizada) {
        if (categoriaId <= 0) {
            return ResponseEntity.badRequest().body("El ID de la categoría no es válido"); 
        }
        try {
            Categoria categoria = categoriaService.update(categoriaId, categoriaActualizada);
            return ResponseEntity.ok(categoria); // 200
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build(); // 404
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    @Operation(summary = "Eliminar una categoría")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long categoriaId) {
        if (categoriaId <= 0) {
            return ResponseEntity.badRequest().build(); // Validación de ID
        }
        try {
            categoriaService.deleteById(categoriaId);
            return ResponseEntity.noContent().build(); // 204
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build(); // 404
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }
}
