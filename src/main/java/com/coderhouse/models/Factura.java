package com.coderhouse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Practica> practicas = new ArrayList<>();

    @Column(name = "total")
    private Double total;

    @Column(name = "fecha")
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