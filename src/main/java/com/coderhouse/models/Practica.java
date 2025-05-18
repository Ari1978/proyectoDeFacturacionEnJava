package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Practica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY)  // a menudo se usa LAZY aquí
    @JoinColumn(name = "categoria_id")
    @JsonBackReference               // evita ciclo en JSON (lado “hijo”)
    private Categoria categoria;

    @ManyToMany(mappedBy = "practicas")
    private List<Paciente> pacientes;
}
