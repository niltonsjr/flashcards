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

import com.dam.flashcards.dto.CategoriaDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.repositories.CategoriaRepository;
import com.dam.flashcards.repositories.TarjetaRepository;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.services.exceptions.DatabaseException;
import com.dam.flashcards.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public Page<CategoriaDTO> findAllPaged(Pageable pageable) {
		Page<Categoria> list = repository.findAll(pageable);
		return list.map(x -> new CategoriaDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		Categoria entity = obj
				.orElseThrow(() -> new ResourceNotFoundException("La categoria no existe en el sistema."));
		return new CategoriaDTO(entity, entity.getTarjetas());
	}

	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new CategoriaDTO(entity);
	}

	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		Categoria entity;
		try {
			entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new CategoriaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Categoria no encontrada: " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Categoria no encontrada: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violación de integridad.");
		}
	}

	private void copyDtoToEntity(CategoriaDTO dto, Categoria entity) {
		entity.setNombre(dto.getNombre());
		entity.setUsuario(usuarioRepository.getOne(dto.getUsuarioId()));

		entity.getTarjetas().clear();
		for (TarjetaDTO tarDto : dto.getTarjetas()) {
			Tarjeta tarjeta = tarjetaRepository.getOne(tarDto.getId());
			entity.getTarjetas().add(tarjeta);
		}
	}

}
