package com.coderhouse.dao;

import com.coderhouse.models.Categoria;
import com.coderhouse.models.Paciente;
import com.coderhouse.models.Practica;
import com.coderhouse.models.Turno;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.repositories.PacienteRepository;
import com.coderhouse.repositories.PracticaRepository;
import com.coderhouse.repositories.TurnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DaoFactory {

    private final CategoriaRepository categoriaRepository;
    private final PacienteRepository pacienteRepository;
    private final PracticaRepository practicaRepository;
    private final TurnoRepository turnoRepository;

    @Autowired
    public DaoFactory(
            CategoriaRepository categoriaRepository,
            PacienteRepository pacienteRepository,
            PracticaRepository practicaRepository,
            TurnoRepository turnoRepository
    ) {
        this.categoriaRepository = categoriaRepository;
        this.pacienteRepository = pacienteRepository;
        this.practicaRepository = practicaRepository;
        this.turnoRepository = turnoRepository;
    }

    public Categoria persistirCategoria(Categoria categoria) {
        if (categoria == null) throw new IllegalArgumentException("La categoría no puede ser null");
        return categoriaRepository.save(categoria);
    }

    public Practica persistirPractica(Practica practica) {
        if (practica == null) throw new IllegalArgumentException("La práctica no puede ser null");
        return practicaRepository.save(practica);
    }

    public Paciente persistirPaciente(Paciente paciente) {
        if (paciente == null) throw new IllegalArgumentException("El paciente no puede ser null");
        return pacienteRepository.save(paciente);
    }

    public Turno persistirTurno(Turno turno) {
        if (turno == null) throw new IllegalArgumentException("El turno no puede ser null");
        return turnoRepository.save(turno);
    }

    @Transactional // Importante para asegurar atomicidad al modificar varias entidades
    public void asignarPacienteAPractica(Long pacienteId, List<Long> practicasIds) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + pacienteId));

        List<Practica> practicas = practicaRepository.findAllById(practicasIds);

        for (Practica practica : practicas) {
            if (!practica.getPacientes().contains(paciente)) {
                practica.getPacientes().add(paciente);
            }
            if (!paciente.getPracticas().contains(practica)) {
                paciente.getPracticas().add(practica);
            }
        }

        // Generalmente basta con guardar uno de los lados en ManyToMany, pero puedes guardar ambos para asegurarte
        pacienteRepository.save(paciente);
        practicaRepository.saveAll(practicas);
    }

    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }

    public List<Practica> findAllPracticas() {
        return practicaRepository.findAll();
    }

    public List<Paciente> findAllPacientes() {
        return pacienteRepository.findAll();
    }

    public List<Turno> findAllTurnos() {
        return turnoRepository.findAll();
    }
}