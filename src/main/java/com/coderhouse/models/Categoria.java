package com.coderhouse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;  // <-- IMPORTAR ESTA
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una categoría de prácticas médicas.")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la categoría", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío")  // <-- AÑADIR ESTA LÍNEA
    @Schema(description = "Nombre de la categoría", example = "Laboratorio")
    private String nombre;

    @OneToMany(
        mappedBy = "categoria",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @Schema(description = "Lista de prácticas asociadas a esta categoría")
    private List<Practica> practicas = new ArrayList<>();

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}
