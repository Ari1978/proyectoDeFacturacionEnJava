package com.coderhouse.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class) // Necesario para @CreatedDate
@Schema(description = "Modelo de paciente")
@Table(name = "pacientes")
public class Paciente {

    @Schema(description = "ID del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Matias")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Schema(description = "Apellido del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Martinez")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Schema(description = "Número de documento del paciente", example = "33444555")
    @Column(name = "dni", nullable = false, unique = true)
    private int dni;

    @Schema(description = "Edad del paciente", example = "28")
    @Column(name = "edad")
    private int edad;

    @Schema(description = "Sexo del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Femenino o Masculino")
    @Column(name = "sexo")
    private String sexo;

    @Schema(description = "Número de teléfono del paciente", example = "8887485")
    @Column(name = "telefono", nullable = false, unique = true)
    private String telefono;

    @Schema(description = "Cobertura médica del paciente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Osde")
    @Column(name = "cobertura")
    private String cobertura;

    @Schema(description = "Lista de turnos asignados al paciente")
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Turno> turnos = new ArrayList<>();

    @Schema(description = "Lista de prácticas asignadas al paciente")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "paciente_practica",
        joinColumns = @JoinColumn(name = "paciente_id"),
        inverseJoinColumns = @JoinColumn(name = "practica_id")
    )
    @JsonIgnore
    private List<Practica> practicas = new ArrayList<>();

    @Schema(description = "Muestra la hora y fecha del registro del paciente")
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // Constructor personalizado
    public Paciente(String nombre, String apellido, int dni, int edad, String sexo, String telefono, String cobertura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.sexo = sexo;
        this.cobertura = cobertura;
        this.telefono = telefono;
    }

    public Paciente() {
        // Constructor vacío requerido por JPA
    }

    public List<Long> getPracticaIds() {
        List<Long> ids = new ArrayList<>();
        for (Practica practica : this.practicas) {
            ids.add(practica.getId());
        }
        return ids;
    }

    public List<Practica> getPracticas() {
        return this.practicas;
    }
}