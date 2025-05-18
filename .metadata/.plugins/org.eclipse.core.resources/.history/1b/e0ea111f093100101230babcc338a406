package com.coderhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Turno;
import com.coderhouse.models.Paciente;
import com.coderhouse.repositories.TurnoRepository;
import com.coderhouse.repositories.PacienteRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class TurnoService {

    private static final int TOPE_DE_TURNOS_POR_DIA = 10;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    public Turno findById(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
    }

    public Turno save(Turno turno) {
        int cantidadDeTurnosHoy = turnoRepository.countByFecha(turno.getFecha());
        if (cantidadDeTurnosHoy >= TOPE_DE_TURNOS_POR_DIA) {
            throw new RuntimeException("Se alcanzó el tope de turnos para el día: " + turno.getFecha());
        }
        return turnoRepository.save(turno);
    }

    @Transactional
    public Turno asignarTurnoAPaciente(Long pacienteId, Long turnoId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        turno.setPaciente(paciente);
        return turnoRepository.save(turno);
    }
}
