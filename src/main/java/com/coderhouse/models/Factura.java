package com.coderhouse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facturas")
@Schema(description = "Entidad que representa una factura médica")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la factura", example = "1")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    @Schema(description = "Paciente asociado a la factura")
    private Paciente paciente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Schema(description = "Lista de prácticas asociadas a la factura")
    private List<Practica> practicas = new ArrayList<>();

    @Column(name = "total")
    @Schema(description = "Total de la factura", example = "1200.50")
    private Double total;

    @Column(name = "fecha")
    @Schema(description = "Fecha de emisión de la factura", example = "2025-05-28")
    private LocalDate fecha;

    // Método para calcular el total de la factura
    public void calcularTotal() {
        double totalGeneral = 0.0;

        if (practicas != null) {
            for (Practica practica : practicas) {
                if (practica != null && practica.getPrecio() != null) {
                    totalGeneral += practica.getPrecio();
                }
            }
        }
        this.total = totalGeneral;
    }
}
