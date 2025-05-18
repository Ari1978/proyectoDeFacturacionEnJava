package com.coderhouse.controller;

import com.coderhouse.models.Factura;
import com.coderhouse.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // Obtener todas las facturas
    @GetMapping("/")
    public ResponseEntity<List<Factura>> getAllFacturas() {
        try {
            List<Factura> facturas = facturaService.findAll();
            return ResponseEntity.ok(facturas); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    // Obtener una factura por su ID
    @GetMapping("/{facturaId}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long facturaId) {
        try {
            Factura factura = facturaService.findById(facturaId);
            if (factura != null) {
                return ResponseEntity.ok(factura); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    // Crear una nueva factura
    @PostMapping("/")
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        try {
            Factura facturaCreada = facturaService.save(factura);
            return ResponseEntity.status(HttpStatus.CREATED).body(facturaCreada); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    // Actualizar una factura existente
    @PutMapping("/{facturaId}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long facturaId, @RequestBody Factura facturaActualizada) {
        try {
            Factura factura = facturaService.update(facturaId, facturaActualizada);
            if (factura != null) {
                return ResponseEntity.ok(factura); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    // Eliminar una factura por su ID
    @DeleteMapping("/{facturaId}")
    public ResponseEntity<?> deleteFactura(@PathVariable Long facturaId) {
        try {
            facturaService.deleteById(facturaId); 
            return ResponseEntity.noContent().build(); // 204 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Factura no encontrada con ID: " + facturaId); // 404 
        }
    }
}
