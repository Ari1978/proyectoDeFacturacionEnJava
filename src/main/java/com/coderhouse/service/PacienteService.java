package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Paciente;
import com.coderhouse.models.Practica;
import com.coderhouse.dto.RegistroDePacienteDTO;
import com.coderhouse.repositories.PacienteRepository;
import com.coderhouse.repositories.PracticaRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PracticaRepository practicaRepository;


    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
    }

    @Transactional
    public Paciente save(Paciente nuevoPaciente) {
        return pacienteRepository.save(nuevoPaciente);
    }

    @Transactional
    public Paciente update(Long id, Paciente pacienteActualizado) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));

        if (pacienteActualizado.getNombre() != null && !pacienteActualizado.getNombre().isEmpty()) {
            paciente.setNombre(pacienteActualizado.getNombre());
        }
        if (pacienteActualizado.getApellido() != null && !pacienteActualizado.getApellido().isEmpty()) {
            paciente.setApellido(pacienteActualizado.getApellido());
        }
        

        return pacienteRepository.save(paciente);
    }

    public void deleteById(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return pacienteRepository.existsById(id);
    }

   

    public List<Practica> findAllPracticas() {
        return practicaRepository.findAll();
    }

    public Practica findPracticaById(Long id) {
        return practicaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Práctica no encontrada"));
    }

    @Transactional
    public Practica savePractica(Practica nuevaPractica) {
        return practicaRepository.save(nuevaPractica);
    }

    @Transactional
    public Practica updatePractica(Long id, Practica practicaActualizada) {
        Practica practica = practicaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Práctica no encontrada"));

        if (practicaActualizada.getNombre() != null && !practicaActualizada.getNombre().isEmpty()) {
            practica.setNombre(practicaActualizada.getNombre());
        }
        if (practicaActualizada.getDescripcion() != null && !practicaActualizada.getDescripcion().isEmpty()) {
            practica.setDescripcion(practicaActualizada.getDescripcion());
        }
        if (practicaActualizada.getPrecio() != null && practicaActualizada.getPrecio() >= 0) {
            practica.setPrecio(practicaActualizada.getPrecio());
        }

        return practicaRepository.save(practica);
    }

    public void deletePracticaById(Long id) {
        if (!practicaRepository.existsById(id)) {
            throw new IllegalArgumentException("Práctica no encontrada");
        }
        practicaRepository.deleteById(id);
    }

    
    @Transactional
    public Paciente registrarPacienteAPracticas(RegistroDePacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));

        for (Long practicaId : dto.getPracticaIds()) {
            Practica practica = practicaRepository.findById(practicaId)
                    .orElseThrow(() -> new IllegalArgumentException("Práctica no encontrada"));

            if (paciente.getPracticas().contains(practica)) {
                throw new IllegalStateException("El paciente ya está registrado con la práctica con ID: " + practica.getId());
            }

            paciente.getPracticas().add(practica);
            practica.getPacientes().add(paciente);
        }

        practicaRepository.saveAll(paciente.getPracticas());
        return pacienteRepository.save(paciente);
    }
}
