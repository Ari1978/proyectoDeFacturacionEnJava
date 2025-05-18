package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
