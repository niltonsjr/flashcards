package com.dam.flashcards.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.dam.flashcards.dto.CategoriaDTO;
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
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AuthService authService;

	/**
	 * Método para buscar una lista paginada de categorías
	 * 
	 * @param pageable
	 * @return Page<CategoriaDTO> La lista de categorías paginada
	 */
	@Transactional(readOnly = true)
	public Page<CategoriaDTO> findAllPaged(Pageable pageable) {
		Usuario usuario = authService.autenticado();
		Page<Categoria> list = repository.findByUsuario(usuario, pageable);
		return list.map(CategoriaDTO::new);
	}

	/**
	 * Método para buscar una categoría por su ID
	 * 
	 * @param id La ID de la categoría
	 * @return CategoriaDTO Los datos de la categoría
	 */
	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		Categoria entity = obj
				.orElseThrow(() -> new ResourceNotFoundException("La categoria no existe en el sistema."));
		return new CategoriaDTO(entity, entity.getTarjetas());
	}

	/**
	 * Método para añadir una categoría
	 * 
	 * @param dto Los datos de la categoría a añadir
	 * @return CategoriaDTO Los datos de la categoría tras añadirla
	 */
	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new CategoriaDTO(entity);
	}

	/**
	 * Método para actualizar una categoría
	 * 
	 * @param id  La ID de la categoría a actualizar
	 * @param dto Los datos de la categoría actualizados
	 * @return CategoriaDTO La categoría tras actualizarla
	 */
	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		Categoria entity;
		try {
			entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new CategoriaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Categoria no encontrada: " + id);
		}

	}

	/**
	 * Método para borrar una categoría
	 * 
	 * @param id La ID de la categoría a borrar
	 */
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Categoria no encontrada: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violación de integridad.");
		}
	}

	/**
	 * Método para copiar los datos de una categoríaDTO a una categoría
	 * 
	 * @param dto    La categoríaDTO que será copiada
	 * @param entity La categoría que recibirá los datos copiados
	 */
	private void copyDtoToEntity(CategoriaDTO dto, Categoria entity) {
		entity.setNombre(dto.getNombre());
		entity.setUsuario(usuarioRepository.getById(dto.getUsuarioId()));

		entity.getTarjetas().clear();
		for (TarjetaDTO tarDto : dto.getTarjetas()) {
			Tarjeta tarjeta = tarjetaRepository.getById(tarDto.getId());
			entity.getTarjetas().add(tarjeta);
		}
	}

}
