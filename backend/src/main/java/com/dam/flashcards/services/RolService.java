package com.dam.flashcards.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam.flashcards.dto.RolDTO;
import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.repositories.RolRepository;
import com.dam.flashcards.services.exceptions.DatabaseException;
import com.dam.flashcards.services.exceptions.ResourceNotFoundException;

@Service
public class RolService {

	@Autowired
	private RolRepository repository;

	@Transactional(readOnly = true)
	public Page<RolDTO> findAllPaged(Pageable pageable) {
		Page<Rol> list = repository.findAll(pageable);
		return list.map(x -> new RolDTO(x));
	}

	@Transactional(readOnly = true)
	public RolDTO findById(Long id) {
		Optional<Rol> obj = repository.findById(id);
		Rol entity = obj
				.orElseThrow(() -> new ResourceNotFoundException("El rol no existe en el sistema."));
		return new RolDTO(entity);
	}
	
	@Transactional
	public RolDTO insert(RolDTO dto) {
		Rol entity = new Rol();
		entity.setNombre(dto.getNombre());
		entity = repository.save(entity);
		return new RolDTO(entity);
	}

	@Transactional
	public RolDTO update(Long id, RolDTO dto) {
		Rol entity;
		try {
			entity = repository.getById(id);
			entity.setNombre(dto.getNombre());
			entity = repository.save(entity);
			return new RolDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Rol no encontrada: " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Rol no encontrada: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violación de integridad.");
		}
	}

}
