package com.dam.flashcards.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.dam.flashcards.dto.TarjetaBasicaDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.CategoriaRepository;
import com.dam.flashcards.repositories.TarjetaRepository;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.services.exceptions.DatabaseException;
import com.dam.flashcards.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarjetaService {

	@Autowired
	private TarjetaRepository repository;

	@Autowired
	private AuthService authService;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public Page<TarjetaBasicaDTO> findAllPaged(Long categoriaId, String texto, Pageable pageable) {
		Usuario usuario = authService.autenticado();
		Categoria categoria = (categoriaId == 0) ? null : categoriaRepository.getById(categoriaId);
		Page<Tarjeta> list = repository.findByUsuarioAndCategoria(usuario, categoria, texto, pageable);
		return list.map(TarjetaBasicaDTO::new);
	}

	@Transactional(readOnly = true)
	public TarjetaDTO findById(Long id) {
		Optional<Tarjeta> obj = repository.findById(id);
		Tarjeta entity = obj.orElseThrow(() -> new ResourceNotFoundException("La tarjeta no existe en el sistema."));
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
			entity = repository.getById(id);
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
		entity.setCategoria(categoriaRepository.getById(dto.getCategoria().getId()));
		entity.setUsuario(usuarioRepository.getById(dto.getUsuarioId()));
	}

}
