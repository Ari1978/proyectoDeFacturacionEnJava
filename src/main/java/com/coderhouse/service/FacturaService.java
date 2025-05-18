package com.coderhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coderhouse.models.Factura;
import com.coderhouse.repositories.FacturaRepository;
import com.coderhouse.interfaces.CrudInterface;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService implements CrudInterface<Factura, Long> {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findById(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));
    }

    @Override
    @Transactional
    public Factura save(Factura nuevaFactura) {
        // Calculamos el total de la factura antes de guardarla
        nuevaFactura.calcularTotal();
        return facturaRepository.save(nuevaFactura);
    }

    @Override
    @Transactional
    public Factura update(Long id, Factura facturaActualizada) {
        // Buscamos la factura existente
        Factura facturaExistente = facturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));

        // Actualizamos los campos solo si no son nulos
        if (facturaActualizada.getPaciente() != null) {
            facturaExistente.setPaciente(facturaActualizada.getPaciente());
        }
        if (facturaActualizada.getFecha() != null) {
            facturaExistente.setFecha(facturaActualizada.getFecha());
        }

        // Si hay una actualización de las prácticas o el total se debe recalcular
        if (facturaActualizada.getPracticas() != null) {
            facturaExistente.setPracticas(facturaActualizada.getPracticas());
            facturaExistente.calcularTotal(); // Recalculamos el total después de actualizar las prácticas
        }

        return facturaRepository.save(facturaExistente);
    }

    @Override
    public void deleteById(Long id) {
        if (!facturaRepository.existsById(id)) {
            throw new IllegalArgumentException("Factura no encontrada");
        }
        facturaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return facturaRepository.existsById(id);
    }
}
