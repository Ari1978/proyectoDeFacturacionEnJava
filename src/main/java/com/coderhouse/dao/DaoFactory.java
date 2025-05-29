package com.coderhouse.dao;

import com.coderhouse.models.Categoria;
import com.coderhouse.models.Factura;
import com.coderhouse.models.Paciente;
import com.coderhouse.models.Practica;
import com.coderhouse.models.Turno;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.repositories.FacturaRepository;
import com.coderhouse.repositories.PacienteRepository;
import com.coderhouse.repositories.PracticaRepository;
import com.coderhouse.repositories.TurnoRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class DaoFactory {

    private final CategoriaRepository categoriaRepository;
    private final PacienteRepository pacienteRepository;
    private final PracticaRepository practicaRepository;
    private final TurnoRepository turnoRepository;
    private final FacturaRepository facturaRepository;

    public DaoFactory(
            CategoriaRepository categoriaRepository,
            PacienteRepository pacienteRepository,
            PracticaRepository practicaRepository,
            TurnoRepository turnoRepository,
            FacturaRepository facturaRepository
    ) {
        this.categoriaRepository = categoriaRepository;
        this.pacienteRepository = pacienteRepository;
        this.practicaRepository = practicaRepository;
        this.turnoRepository = turnoRepository;
        this.facturaRepository = facturaRepository;
    }

    @Transactional
    public Factura crearFactura(Long pacienteId, List<Long> practicasIds) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + pacienteId));
        List<Practica> practicas = practicaRepository.findAllById(practicasIds);
        if (practicas.isEmpty()) {
            throw new RuntimeException("No se encontraron prácticas válidas para los IDs enviados");
        }

        Factura factura = new Factura();
        factura.setPaciente(paciente);
        factura.setFecha(LocalDate.now());
        factura.setPracticas(practicas);

        
        for (Practica practica : practicas) {
            practica.setFactura(factura);
        }

        factura.calcularTotal();

        
        return facturaRepository.save(factura);
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
        Paciente existente = pacienteRepository.findByDni(paciente.getDni());
        if (existente != null) {
            
            return existente;
        } else {
           
            return pacienteRepository.save(paciente);
        }
    }

 
    public Turno persistirTurno(Turno turno) {
        if (turno == null) throw new IllegalArgumentException("El turno no puede ser null");

        
        Paciente paciente = turno.getPaciente();
        if (paciente != null && paciente.getId() == null) {
            pacienteRepository.save(paciente);
        } else if (paciente != null && paciente.getId() != null) {
            
            paciente = pacienteRepository.findById(paciente.getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            turno.setPaciente(paciente);
        }

        

        return turnoRepository.save(turno);
    }

    @Transactional
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

        pacienteRepository.save(paciente);
        practicaRepository.saveAll(practicas);
    }

   
    public Optional<Categoria> buscarCategoriaPorNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    public Optional<Practica> buscarPracticaPorNombre(String nombre) {
        return practicaRepository.findByNombre(nombre);
    }

    public Optional<Paciente> buscarPacientePorDni(int dni) {
        return pacienteRepository.existsByDni(dni);
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