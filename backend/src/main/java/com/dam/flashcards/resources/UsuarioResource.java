package com.dam.flashcards.resources;

import java.net.URI;

import javax.validation.Valid;

import com.dam.flashcards.dto.NuevaContrasenaDTO;
import com.dam.flashcards.dto.UsuarioBasicoDTO;
import com.dam.flashcards.dto.UsuarioDTO;
import com.dam.flashcards.dto.UsuarioInsertDTO;
import com.dam.flashcards.dto.UsuarioUpdateDTO;
import com.dam.flashcards.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	/**
	 * Método para obtener la lista de usuarios paginada
	 * 
	 * @param rolId El ID del Rol si se desea filtrar la lista por Rol
	 * @return ResponseEntity<Page<UsuarioDTO>> La lista de usuarios paginada
	 */
	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable,
			@RequestParam(value = "rolId", defaultValue = "1") Long rolId) {
		Page<UsuarioDTO> list = service.findAllPaged(rolId, pageable);
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Método para buscar un usuario por su ID
	 * 
	 * @param id El ID del usuario a buscar
	 * @return ResponseEntity<UsuarioDTO> Los datos del usuario
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * Método para buscar un usuario por ID retornando la información básica del
	 * usuario
	 * 
	 * @param id El ID del usuario a buscar
	 * @return ResponseEntity<UsuarioBasicoDTO> Los datos del usuario en formato
	 *         básico
	 */
	@GetMapping(value = "/basico/{id}")
	public ResponseEntity<UsuarioBasicoDTO> findBasicoById(@PathVariable Long id) {
		UsuarioBasicoDTO dto = service.findBasicoById(id);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * Método para añadir un nuevo usuario
	 * 
	 * @param dto Los datos del usuario a añadir
	 * @return ResponseEntity<UsuarioDTO> Los datos del usuario tras añadirlo
	 */
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioInsertDTO dto) {
		UsuarioDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	/**
	 * Método para actualizar los datos de un usuario
	 * 
	 * @param id  El ID del usuario a actualizar
	 * @param dto Los datos del usuario actualizados
	 * @return ResponseEntity<UsuarioDTO> Los datos del usuario tras actualizarlo
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO dto) {
		UsuarioDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

	/**
	 * Método para actualizar la contraseña de un usuario
	 * 
	 * @param id  El ID del usuario cuya contraseña de actualizará
	 * @param dto La nueva contraseña
	 * @return ResponseEntity<Void>
	 */
	@PutMapping(value = "/nuevacontrasena/{id}")
	public ResponseEntity<Void> updateContrasena(@PathVariable Long id, @RequestBody NuevaContrasenaDTO dto) {
		service.updatePassword(id, dto);
		return ResponseEntity.ok().build();
	}

	/**
	 * Método para borrar un usuario
	 * 
	 * @param id El ID del usuario a borrar
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
