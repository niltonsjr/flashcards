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

import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.repositories.CategoriaRepository;
import com.dam.flashcards.repositories.TarjetaRepository;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.services.exceptions.DatabaseException;
import com.dam.flashcards.services.exceptions.ResourceNotFoundException;

@Service
public class TarjetaService {

	@Autowired
	private TarjetaRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public Page<TarjetaDTO> findAllPaged(Pageable pageable) {
		Page<Tarjeta> list = repository.findAll(pageable);
		return list.map(x -> new TarjetaDTO(x));
	}

	@Transactional(readOnly = true)
	public TarjetaDTO findById(Long id) {
		Optional<Tarjeta> obj = repository.findById(id);
		Tarjeta entity = obj
				.orElseThrow(() -> new ResourceNotFoundException("La categoria no existe en el sistema."));
		return new TarjetaDTO(entity);
	}

	@Transactional
	public TarjetaDTO insert(TarjetaDTO dto) {
		Tarjeta entity = new Tarjeta();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new TarjetaDTO(entity);
	}

	@Transactional
	public TarjetaDTO update(Long id, TarjetaDTO dto) {
		Tarjeta entity;
		try {
			entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new TarjetaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Tarjeta no encontrada: " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Tarjeta no encontrada: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violación de integridad.");
		}
	}
	
	private void copyDtoToEntity(TarjetaDTO dto, Tarjeta entity) {
		entity.setFrontal(dto.getFrontal());
		entity.setTrasera(dto.getTrasera());
		entity.setFechaUltimaRespuesta(dto.getFechaUltimaRespuesta());
		entity.setConocida(dto.getConocida());
		entity.setTotalConocidas(dto.getTotalConocidas());
		entity.setTotalNoConocidas(dto.getTotalNoConocidas());
		entity.setCategoria(categoriaRepository.getOne(dto.getCategoria().getId()));
		entity.setUsuario(usuarioRepository.getOne(dto.getUsuario().getId()));
	}

}
