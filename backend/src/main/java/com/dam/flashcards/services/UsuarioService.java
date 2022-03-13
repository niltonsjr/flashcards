package com.dam.flashcards.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam.flashcards.dto.CategoriaDTO;
import com.dam.flashcards.dto.NuevaContrasenaDTO;
import com.dam.flashcards.dto.RolDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.dto.UsuarioBasicoDTO;
import com.dam.flashcards.dto.UsuarioDTO;
import com.dam.flashcards.dto.UsuarioInsertDTO;
import com.dam.flashcards.dto.UsuarioRegistroDTO;
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
public class UsuarioService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Autowired
	private AuthService authService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PreAuthorize("hasRole('ADMINISTRADOR')")
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAllPaged(Long rolId, Pageable pageable) {
		Rol rol = (rolId == 0) ? null : rolRepository.getById(rolId);
		Page<Usuario> list = repository.findByRoles(rol, pageable);
		return list.map(UsuarioDTO::new);
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		authService.validarUsuarioLogadoOAdministrador(id);
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("El usuario no existe en el sistema."));
		return new UsuarioDTO(entity, entity.getCategorias(), entity.getTarjetas());
	}

	@Transactional(readOnly = true)
	public UsuarioBasicoDTO findBasicoById(Long id) {
		authService.validarUsuarioLogadoOAdministrador(id);
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("El usuario no existe en el sistema."));
		return new UsuarioBasicoDTO(entity);
	}

	@Transactional(readOnly = true)
	public Usuario findUsuarioByNombreDeUsuario(String nombreDeUsuario) {
		Optional<Usuario> obj = repository.findByNombreDeUsuario(nombreDeUsuario);
		return obj.orElseThrow(() -> new ResourceNotFoundException("El usuario no existe en el sistema."));
	}

	@Transactional
	public void updateResetToken(String token, String email) {
		Optional<Usuario> obj = repository.findByEmail(email);
		Usuario usuario = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
		usuario.setResetToken(token);
		repository.save(usuario);
	}

	@Transactional(readOnly = true)
	public Usuario findUsuarioByResetToken(String token) {
		Optional<Usuario> obj = repository.findByResetToken(token);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
	}

	@Transactional(readOnly = true)
	public Usuario findByUsuarioEmail(String email) {
		Optional<Usuario> obj = repository.findByEmail(email);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
	}

	@Transactional
	public UsuarioDTO register(UsuarioRegistroDTO dto) {
		Usuario entity = new Usuario();
		dto.getRoles().add(new RolDTO(1L, "ROLE_USUARIO"));
		copyDtoToEntity(dto, entity);
		entity.setContrasena(passwordEncoder.encode(dto.getContrasena()));
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
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
		authService.validarUsuarioLogadoOAdministrador(id);
		Usuario entity;
		try {
			entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Usuario no encontrada: " + id);
		}
	}

	@Transactional
	public void updatePassword(Long id, NuevaContrasenaDTO dto) {
		authService.validarUsuarioLogadoOAdministrador(id);
		Usuario entity;
		try {
			entity = repository.getById(id);
			entity.setContrasena(passwordEncoder.encode(dto.getNuevaContrasena()));
			entity.setResetToken(null);
			repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Usuario no encontrada: " + id);
		}
	}

	@Transactional
	public void resetPassword(Long id, NuevaContrasenaDTO dto) {
		Usuario entity;
		try {
			entity = repository.getById(id);
			entity.setContrasena(passwordEncoder.encode(dto.getNuevaContrasena()));
			entity.setResetToken(null);
			repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Usuario no encontrada: " + id);
		}
	}

	public void delete(Long id) {
		Usuario usuario = authService.autenticado();
		if (!usuario.getId().equals(id)) {
			try {
				repository.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException("Usuario no encontrada: " + id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException("Violación de integridad.");
			}
		} else {
			throw new DatabaseException("No es posible borrar el usuario desde el que se está accediendo.");
		}

	}

	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNombreDeUsuario(dto.getNombreDeUsuario());
		entity.setNombre(dto.getNombre());
		entity.setApellidos(dto.getApellidos());
		entity.setEmail(dto.getEmail());

		if (!dto.getCategorias().isEmpty()) {
			entity.getCategorias().clear();
			for (CategoriaDTO catDto : dto.getCategorias()) {
				Categoria categoria = categoriaRepository.getById(catDto.getId());
				entity.getCategorias().add(categoria);
			}
		}

		if (!dto.getTarjetas().isEmpty()) {
			entity.getTarjetas().clear();
			for (TarjetaDTO tarDto : dto.getTarjetas()) {
				Tarjeta tarjeta = tarjetaRepository.getById(tarDto.getId());
				entity.getTarjetas().add(tarjeta);
			}
		}

		if (!dto.getRoles().isEmpty()) {
			entity.getRoles().clear();
			for (RolDTO rolDto : dto.getRoles()) {
				Rol rol = rolRepository.getById(rolDto.getId());
				entity.getRoles().add(rol);
			}
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> obj = repository.findByNombreDeUsuario(username);
		if (!obj.isPresent()) {
			logger.error("Usuario no encontrado: " + username);
			throw new UsernameNotFoundException("Nombre de usuario no encontrado");
		}
		logger.info("Usuario encontrado: " + username);
		return obj.get();
	}

}
