package com.dam.flashcards.resources;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dam.flashcards.dto.RolDTO;
import com.dam.flashcards.services.RolService;

@RestController
@RequestMapping(value = "/roles")
public class RolResource {

	@Autowired
	private RolService service;

	/**
	 * Método para obtgener la lista de Roles
	 * 
	 * @param pageable
	 * @return ResponseEntity<Page<RolDTO>> La lista de roles paginada
	 */
	@GetMapping
	public ResponseEntity<Page<RolDTO>> findAll(Pageable pageable) {
		Page<RolDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Método para obtener un Rol por ID
	 * 
	 * @param id El ID del Rol
	 * @return ResponseEntity<RolDTO> Los datos del Rol
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<RolDTO> findById(@PathVariable Long id) {
		RolDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * Método para añadir un Rol
	 * 
	 * @param dto Los datos del Rol a añadir
	 * @return ResponseEntity<RolDTO> Los datos del Rol añadiddo
	 */
	@PostMapping
	public ResponseEntity<RolDTO> insert(@RequestBody RolDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	/**
	 * Método para actualizar un Rol
	 * 
	 * @param id  El ID del Rol a actualizar
	 * @param dto Los datos del Rol actualizados
	 * @return ResponseEntity<RolDTO> Los datos del Rol tras actualizarlo
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<RolDTO> update(@PathVariable Long id, @RequestBody RolDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * Método para borrar un Rol
	 * 
	 * @param id El Id del Rol a borrar
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
