package com.dam.flashcards.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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

	/**
	 * Método para obtener la lista de usuarios paginada solo para usuarios con Rol
	 * Administrador.
	 * 
	 * @param rolId El ID del Rol si se desea filtrar la lista por Rol
	 * @return Page<UsuarioDTO> La lista de usuarios paginada
	 */
	@PreAuthorize("hasRole('ADMINISTRADOR')")
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAllPaged(Long rolId, Pageable pageable) {
		Rol rol = (rolId == 0) ? null : rolRepository.getById(rolId);
		Page<Usuario> list = repository.findByRoles(rol, pageable);
		return list.map(UsuarioDTO::new);
	}

	/**
	 * Método para buscar un usuario por su ID solo si el usuario buscado es el
	 * propio usuario logado o si tiene rol Administrador
	 * 
	 * @param id El ID del usuario a buscar
	 * @return UsuarioDTO Los datos del usuario
	 */
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		authService.validarUsuarioLogadoOAdministrador(id);
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("El usuario no existe en el sistema."));
		return new UsuarioDTO(entity, entity.getCategorias(), entity.getTarjetas());
	}

	/**
	 * Método para buscar un usuario por ID retornando la información básica del
	 * usuario solo si el usuario buscado es el propio usuario logado o si tiene rol
	 * Administrador
	 * 
	 * @param id El ID del usuario a buscar
	 * @return UsuarioBasicoDTO Los datos del usuario en formato básico
	 */
	@Transactional(readOnly = true)
	public UsuarioBasicoDTO findBasicoById(Long id) {
		authService.validarUsuarioLogadoOAdministrador(id);
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("El usuario no existe en el sistema."));
		return new UsuarioBasicoDTO(entity);
	}

	/**
	 * Método para buscar un usuario por su nombre de usuario.
	 * 
	 * @param nombreDeUsuario El nombre del usuario a buscar
	 * @return Usuario Los datos del usuario
	 */
	@Transactional(readOnly = true)
	public Usuario findUsuarioByNombreDeUsuario(String nombreDeUsuario) {
		Optional<Usuario> obj = repository.findByNombreDeUsuario(nombreDeUsuario);
		return obj.orElseThrow(() -> new ResourceNotFoundException("El usuario no existe en el sistema."));
	}

	/**
	 * Método para actualizar el resetToken de un usuario que se utiliza para poder
	 * reasignar la contraseña desde el método de olvidó su contraseña
	 * 
	 * @param token El token que se desea asignar al usuario
	 * @param email El email del usuario al que se desea asignar el token
	 */
	@Transactional
	public void updateResetToken(String token, String email) {
		Optional<Usuario> obj = repository.findByEmail(email);
		Usuario usuario = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
		usuario.setResetToken(token);
		repository.save(usuario);
	}

	/**
	 * Método para buscar un usuario por su resetToken
	 * 
	 * @param token El token que se desea buscar
	 * @return Usuario Los datos del usuario localizado
	 */
	@Transactional(readOnly = true)
	public Usuario findUsuarioByResetToken(String token) {
		Optional<Usuario> obj = repository.findByResetToken(token);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
	}

	/**
	 * Método para buscar un usuario por su correo electrónico
	 * 
	 * @param email El correo electrónico del usuario
	 * @return Usuario Los datos del usuario localizado
	 */
	@Transactional(readOnly = true)
	public Usuario findByUsuarioEmail(String email) {
		Optional<Usuario> obj = repository.findByEmail(email);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
	}

	/**
	 * Método para añadir un nuevo usuario desde el formulario de registro
	 * 
	 * @param dto Los datos del usuario a añadir
	 * @return UsuarioDTO Los datos del usuario tras añadirlo
	 */
	@Transactional
	public UsuarioDTO register(UsuarioRegistroDTO dto) {
		Usuario entity = new Usuario();
		dto.getRoles().add(new RolDTO(1L, "ROLE_USUARIO"));
		copyDtoToEntity(dto, entity);
		entity.setContrasena(passwordEncoder.encode(dto.getContrasena()));
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}

	/**
	 * Método para añadir un nuevo usuario
	 * 
	 * @param dto Los datos del usuario a añadir
	 * @return UsuarioDTO Los datos del usuario tras añadirlo
	 */
	@Transactional
	public UsuarioDTO insert(UsuarioInsertDTO dto) {
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity.setContrasena(passwordEncoder.encode(dto.getContrasena()));
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}

	/**
	 * Método para actualizar los datos de un usuario
	 * 
	 * @param id  El ID del usuario a actualizar
	 * @param dto Los datos del usuario actualizados
	 * @return UsuarioDTO Los datos del usuario tras actualizarlo
	 */
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

	/**
	 * Método para actualizar la contraseña de un usuario que realiza la
	 * actualización es el usuario es el propio usuario logado o tiene rol
	 * Administrador
	 * 
	 * @param id  El ID del usuario cuya contraseña se quiere actualizar
	 * @param dto La nueva contraseña que se quiere asginar
	 */
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

	/**
	 * Método para actualizar la contraseña de un usuario desde el método de olvidó
	 * su contraseña
	 * 
	 * @param id  el ID del usuario que se quiere actualizar la contraseña
	 * @param dto La nueva contraseña que se quiere asignar
	 */
	@Transactional
	public void resetPassword(Long id, NuevaContrasenaDTO dto) {
		Usuario entity;
		try {
			entity = repository.getById(id);
			entity.setContrasena(passwordEncoder.encode(dto.getNuevaContrasena()));
			// Tras actualizar la contraseña, se asigna el resetToken a null para no poder
			// volver a ser utilizado el mismo correo más de una vez
			entity.setResetToken(null);
			repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Usuario no encontrada: " + id);
		}
	}

	/**
	 * Método para borrar un usuario
	 * 
	 * @param id El ID del usuario a borrar
	 */
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

	/**
	 * Método para copiar un usuarioDTO a una usuario
	 * 
	 * @param dto    El usuarioDTO que será copiada
	 * @param entity El usuario que recibirá los datos copiados
	 */
	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNombreDeUsuario(dto.getNombreDeUsuario());
		entity.setNombre(dto.getNombre());
		entity.setApellidos(dto.getApellidos());
		entity.setEmail(dto.getEmail());
		// Comprueba si el dto tiene alguna categoría asignada antes de copiarlas
		if (!dto.getCategorias().isEmpty()) {
			entity.getCategorias().clear();
			for (CategoriaDTO catDto : dto.getCategorias()) {
				Categoria categoria = categoriaRepository.getById(catDto.getId());
				entity.getCategorias().add(categoria);
			}
		}
		// Comprueba si el dto tiene alguna tarjeta asignada antes de copiarlas
		if (!dto.getTarjetas().isEmpty()) {
			entity.getTarjetas().clear();
			for (TarjetaDTO tarDto : dto.getTarjetas()) {
				Tarjeta tarjeta = tarjetaRepository.getById(tarDto.getId());
				entity.getTarjetas().add(tarjeta);
			}
		}
		// Comprueba si el dto tiene algún rol asignado antes de copiarlas
		if (!dto.getRoles().isEmpty()) {
			entity.getRoles().clear();
			for (RolDTO rolDto : dto.getRoles()) {
				Rol rol = rolRepository.getById(rolDto.getId());
				entity.getRoles().add(rol);
			}
		}
	}

	/**
	 * Método para obtener los datos de un usuario por su nombre de usuario
	 * 
	 * @param username El nombre de usuario del usuario a buscar
	 * @return UserDetails Los datos del usuario
	 * @throws UsernameNotFoundException si el nombre de usuario no existe
	 */
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
