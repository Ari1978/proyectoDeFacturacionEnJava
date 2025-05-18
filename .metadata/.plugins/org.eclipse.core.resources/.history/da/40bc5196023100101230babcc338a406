package com.coderhouse.models;

<<<<<<< HEAD


import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
=======
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
>>>>>>> cbe5526 ("Commit inicial")
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

<<<<<<< HEAD

=======
>>>>>>> cbe5526 ("Commit inicial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
<<<<<<< HEAD
@Table(name = "Practicas")


public class Practica {
    
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name = "Nombre", nullable = false) 

	    private String nombre;
	    
	    @Column(name = "Descripcion", nullable = false) 

	    private String descripcion;
	    
	    @Column(name = "Precio", nullable = false)
	    private Double precio;

	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	    		name = "paciente_practica",
	    	    joinColumns = @JoinColumn(name = "paciente_id"),
	    	    inverseJoinColumns = @JoinColumn(name = "practica_id")
	    )
	    
	    @JsonIgnore
	    private List<Paciente> pacientes = new ArrayList<>();

	    @ManyToOne(fetch = FetchType.EAGER)
	    private Categoria categoria;

	    public Practica(String nombre, String descripcion, Double precio, List<Paciente> pacientes, Categoria categoria) {
	        this.nombre = nombre;
	        this.descripcion = descripcion;
	        this.precio = precio;
	        this.pacientes = pacientes;
	        this.categoria = categoria;
	    }

	    
	}
=======
@Schema(description = "Modelo de práctica")
@Table(name = "Practicas")
public class Practica {

    @Schema(description = "ID de la práctica", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Schema(description = "Nombre de la práctica", requiredMode = Schema.RequiredMode.REQUIRED, example = "Audiometria")
    @Column(name = "Nombre", nullable = false) 
    private String nombre;
    
    @Schema(description = "Describe a la práctica")
    @Column(name = "Descripcion", nullable = false) 
    private String descripcion;
    
    @Schema(description = "Precio de la práctica", example = "4500")
    @Column(name = "Precio", nullable = false)
    private Double precio;

    @Schema(description = "Categoría de la práctica", example = "Laboratorio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    @Schema(description = "Lista de pacientes asignados a la práctica")
    @ManyToMany
    @JoinTable(
        name = "paciente_practica",
        joinColumns = @JoinColumn(name = "practica_id"),
        inverseJoinColumns = @JoinColumn(name = "paciente_id")
    )
    private List<Paciente> pacientes = new ArrayList<>();

   

    // Constructor con todos los parámetros
    public Practica(String nombre, String descripcion, Double precio, List<Paciente> pacientes, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.pacientes = pacientes != null ? pacientes : new ArrayList<>();
        this.categoria = categoria;
    }
}
>>>>>>> cbe5526 ("Commit inicial")
