package com.coderhouse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Practica;

public interface PracticaRepository extends JpaRepository<Practica, Long> {

	Optional<Practica> findByNombre(String nombre);

}
