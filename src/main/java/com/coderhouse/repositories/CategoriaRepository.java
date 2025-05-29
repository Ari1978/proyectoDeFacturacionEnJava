package com.coderhouse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Categoria;




public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	boolean existsByNombre(String nombre);

	Optional<Categoria> findByNombre(String nombre);

}
