package com.coderhouse.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "turnos")
@Schema(description = "Entidad que representa un turno médico")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del turno", example = "1")
    private Long id;

    @Schema(description = "Fecha del turno", example = "2025-06-01")
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @Schema(description = "Paciente que tiene asignado el turno")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practica_id", nullable = false)
    @Schema(description = "Práctica médica asignada al turno")
    private Practica practica;

    public Turno(Paciente paciente, Practica practica, LocalDate fecha) {
        this.paciente = paciente;
        this.practica = practica;
        this.fecha = fecha;
    }

}
