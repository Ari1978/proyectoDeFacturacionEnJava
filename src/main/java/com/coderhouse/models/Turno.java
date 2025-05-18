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

@AllArgsConstructor
@NoArgsConstructor // Lombok: Genera un constructor sin parámetros
@Data // Lombok: Genera los getters, setters, toString, hashCode y equals
@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY) // Quitar CascadeType.ALL aquí
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY) // Quitar CascadeType.ALL aquí
    @JoinColumn(name = "practica_id", nullable = false)
    private Practica practica;

    // Constructor con parámetros para mayor facilidad de uso
    public Turno(Paciente paciente, Practica practica, LocalDate fecha) {
        this.paciente = paciente;
        this.practica = practica;
        this.fecha = fecha;
    }

}