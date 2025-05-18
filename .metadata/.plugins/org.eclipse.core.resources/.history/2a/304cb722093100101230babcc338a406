package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.RegistroDePacienteDTO;
import com.coderhouse.interfaces.CrudInterface;
import com.coderhouse.models.Paciente;
import com.coderhouse.models.Practica;
import com.coderhouse.repositories.PacienteRepository;
import com.coderhouse.repositories.PracticaRepository;

import jakarta.transaction.Transactional;


@Service
public class PacienteService implements CrudInterface<Paciente, Long> {
	
	
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private PracticaRepository practicaRepository;
	
	@Override
	public List<Paciente> findAll() {
		return pacienteRepository.findAll();
	}

	@Override
	public Paciente findById(Long id) {
		return pacienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
	}

	@Override
	@Transactional
	public Paciente save(Paciente nuevoPaciente) {
		return pacienteRepository.save(nuevoPaciente);

	}

	@Override
	@Transactional
	public Paciente update(Long id, Paciente pacienteActualizado) {
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
		
		if(pacienteActualizado.getNombre() != null && !pacienteActualizado.getNombre().isEmpty()) {
			paciente.setNombre(pacienteActualizado.getNombre());
		}
		if(pacienteActualizado.getApellido() != null && !pacienteActualizado.getApellido().isEmpty()) {
			paciente.setApellido(pacienteActualizado.getApellido());
		}
		if(pacienteActualizado.getDni()  > 0) {
			paciente.setDni(pacienteActualizado.getDni());
		}
		if(pacienteActualizado.getEdad() > 0) {
			paciente.setEdad(pacienteActualizado.getEdad());
		}
		if(pacienteActualizado.getSexo() != null && !pacienteActualizado.getSexo().isEmpty()) {
			paciente.setSexo(pacienteActualizado.getSexo());
		}
		if(pacienteActualizado.getTelefono() > 0) {
			paciente.setTelefono(pacienteActualizado.getTelefono());
		}
		if(pacienteActualizado.getCobertura() != null && !pacienteActualizado.getCobertura().isEmpty()) {
			paciente.setCobertura(pacienteActualizado.getCobertura());
		}
		
		
		return pacienteRepository.save(paciente);
	}

	@Override
	public void deleteById(Long id) {
		if(!pacienteRepository.existsById(id)) {
			throw new IllegalArgumentException("Paciente no encontrado");
		}
		pacienteRepository.deleteById(id);
		
		
	}
	@Override
    public boolean existsById(Long id) {
        return pacienteRepository.existsById(id);
    }


@Transactional
public Paciente registrarPacienteAPracticas(RegistroDePacienteDTO dto) {
	Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
			.orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
	for (Long practicaId : dto.getPracticaIds()) {
		Practica practica = practicaRepository.findById(practicaId)
		.orElseThrow(() -> new IllegalArgumentException("Pracxtica no encontrada"));
		
		// Verificar si el paciente ya está registrado con la práctica
		if (paciente.getPracticas().contains(practica)) {
		    throw new IllegalStateException("El paciente ya está registrado con la práctica con ID: " + practica.getId());
		}

		paciente.getPracticas().add(practica);
		practica.getPacientes().add(paciente);
		
		practicaRepository.save(practica);


		}
		return pacienteRepository.save(paciente);
	}
}


















