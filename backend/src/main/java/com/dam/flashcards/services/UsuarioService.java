package com.dam.flashcards.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam.flashcards.dto.CategoriaDTO;
import com.dam.flashcards.dto.RolDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.dto.UsuarioDTO;
import com.dam.flashcards.dto.UsuarioInsertDTO;
import com.dam.flashcards.dto.UsuarioUpdateDTO;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.CategoriaRepository;
import com.dam.flashcards.repositories.RolRepository;
import com.dam.flashcards.repositories.TarjetaRepository;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.services.exceptions.DatabaseException;
import com.dam.flashcards.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAllPaged(Pageable pageable) {
		Page<Usuario> list = repository.findAll(pageable);
		return list.map(x -> new UsuarioDTO(x));
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("La categoria no existe en el sistema."));
		return new UsuarioDTO(entity, entity.getCategorias(), entity.getTarjetas());
	}

	@Transactional
	public UsuarioDTO insert(UsuarioInsertDTO dto) {
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity.setContrasena(passwordEncoder.encode(dto.getContrasena()));
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}

	@Transactional
	public UsuarioDTO update(Long id, UsuarioUpdateDTO dto) {
		Usuario entity;
		try {
			entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Usuario no encontrada: " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Usuario no encontrada: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violación de integridad.");
		}
	}

	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNombreDeUsuario(dto.getNombreDeUsuario());
		entity.setNombre(dto.getNombre());
		entity.setApellidos(dto.getApellidos());
		entity.setEmail(dto.getEmail());

		entity.getCategorias().clear();
		for (CategoriaDTO catDto : dto.getCategorias()) {
			Categoria categoria = categoriaRepository.getOne(catDto.getId());
			entity.getCategorias().add(categoria);
		}

		entity.getTarjetas().clear();
		for (TarjetaDTO tarDto : dto.getTarjetas()) {
			Tarjeta tarjeta = tarjetaRepository.getOne(tarDto.getId());
			entity.getTarjetas().add(tarjeta);
		}

		entity.getRoles().clear();
		for (RolDTO rolDto : dto.getRoles()) {
			Rol rol = rolRepository.getOne(rolDto.getId());
			entity.getRoles().add(rol);
		}
	}

}
