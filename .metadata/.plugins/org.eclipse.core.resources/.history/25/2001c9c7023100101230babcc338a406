package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.CrudInterface;
import com.coderhouse.models.Categoria;
import com.coderhouse.models.Practica;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.repositories.PracticaRepository;

import jakarta.transaction.Transactional;


@Service
public class PracticaService implements CrudInterface<Practica, Long> {

	@Autowired
	private PracticaRepository practicaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Practica> findAll() {
		
		return practicaRepository.findAll();
	}

	@Override
	public Practica findById(Long id) {
		return practicaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Practica no encontrada"));
	}

	@Override
	@Transactional
	public Practica save(Practica nuevaPractica) {
		return practicaRepository.save(nuevaPractica);

	}

	@Override
	@Transactional
	public Practica update(Long id, Practica practicaActualizada) {
		Practica practica = practicaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Practica no encontrada"));
		
		if(practicaActualizada.getNombre() != null && !practicaActualizada.getNombre().isEmpty()) {
			practica.setNombre(practicaActualizada.getNombre());
		}
		if(practicaActualizada.getDescripcion() != null && !practicaActualizada.getDescripcion().isEmpty()) {
			practica.setDescripcion(practicaActualizada.getDescripcion());
		}
		if (practicaActualizada.getPrecio() != null && practicaActualizada.getPrecio() >= 0) {
			practica.setPrecio(practicaActualizada.getPrecio());
		}
		
		
		return practicaRepository.save(practica);
	}

	@Override
	public void deleteById(Long id) {
		if(!practicaRepository.existsById(id)) {
			throw new IllegalArgumentException("Practica no encontrada");
		}
		practicaRepository.deleteById(id);
		
		
	}
	@Override
    public boolean existsById(Long id) {
        return practicaRepository.existsById(id);
    }

	@Transactional
	public Practica asignarCategoriaAPractica(Long practicaId, Long categoriaId) {
	    // Buscar la categoría por su ID
	    Categoria categoria = categoriaRepository.findById(categoriaId)
	            .orElseThrow(() -> new IllegalArgumentException("Categoría inexistente"));

	    // Buscar la práctica por su ID (se usa practicaId, no categoriaId)
	    Practica practica = practicaRepository.findById(practicaId)
	            .orElseThrow(() -> new IllegalArgumentException("Práctica inexistente"));

	    //Validar que la practica ya no tenga esta categoria
	    
	    if(practica.getCategoria() != null && practica.getCategoria().getId().equals(categoriaId) ) {
	    	throw new IllegalStateException(("El curso ya tiene esta categoria asignada"));
	    }
	    
	    
	    // Asignar la categoría a la práctica
	    practica.setCategoria(categoria);

	    // Guardar la práctica actualizada en la base de datos
	    return practicaRepository.save(practica);
	}

	public Practica AsignacionCategoriaPracticaDTO(Long practicaId, Long categoriaId) {
		// TODO Auto-generated method stub
		return null;
	}
<<<<<<< HEAD
}
=======

	
	}
>>>>>>> cbe5526 ("Commit inicial")

























