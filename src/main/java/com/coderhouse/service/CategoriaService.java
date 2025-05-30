package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.interfaces.CrudInterface;
import com.coderhouse.models.Categoria;
import com.coderhouse.repositories.CategoriaRepository;


import jakarta.transaction.Transactional;

@Service
public class CategoriaService implements CrudInterface<Categoria, Long> {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> findAll() {
		
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));
	}

	@Override
	@Transactional
	public Categoria save(Categoria nuevaCategoria) {
	    if (nuevaCategoria.getNombre() == null || nuevaCategoria.getNombre().isBlank()) {
	        throw new IllegalArgumentException("El nombre de la categoría no puede ser vacío o nulo");
	    }

	    if (categoriaRepository.existsByNombre(nuevaCategoria.getNombre())) {
	        throw new IllegalArgumentException("Ya existe una categoria con ese nombre");
	    }
	    return categoriaRepository.save(nuevaCategoria);
	}


	@Override
	@Transactional
	public Categoria update(Long id, Categoria categoriaActualizada) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));
		
		if(categoriaActualizada.getNombre() != null && !categoriaActualizada.getNombre().isEmpty()) {
			categoria.setNombre(categoriaActualizada.getNombre());
		}
		
		return categoriaRepository.save(categoria);
	}

	@Override
	public void deleteById(Long id) {
		if(!categoriaRepository.existsById(id)) {
			throw new IllegalArgumentException("Categoria no encontrada");
		}
		categoriaRepository.deleteById(id);
		
		
	}
	@Override
    public boolean existsById(Long id) {
        return categoriaRepository.existsById(id);
    }
}
