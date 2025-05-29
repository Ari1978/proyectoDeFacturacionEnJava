package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una práctica médica")
public class Practica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la práctica", example = "1")
    private Long id;

    @Schema(description = "Nombre de la práctica", example = "Radiografía")
    private String nombre;

    @Schema(description = "Descripción de la práctica", example = "Radiografía de tórax")
    private String descripcion;

    @Schema(description = "Precio de la práctica", example = "450.0")
    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    @Schema(description = "Categoría a la que pertenece la práctica")
    private Categoria categoria;

    @ManyToMany(mappedBy = "practicas")
    @Schema(description = "Lista de pacientes que tienen asociada esta práctica")
    private List<Paciente> pacientes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id")
    @JsonBackReference
    @Schema(description = "Factura a la que está asociada esta práctica")
    private Factura factura;

    public Practica(String nombre, String descripcion, Double precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }
}
