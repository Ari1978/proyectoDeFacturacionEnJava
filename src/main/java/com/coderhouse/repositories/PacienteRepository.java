package com.coderhouse.repositories;

import com.coderhouse.models.Paciente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> existsByDni(int dni);
    boolean existsByTelefono(String telefono);
    Paciente findByDni(int dni);
}