package com.dam.flashcards.resources;

import java.net.URI;

import javax.validation.Valid;

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

import com.dam.flashcards.dto.NuevaContrasenaDTO;
import com.dam.flashcards.dto.UsuarioBasicoDTO;
import com.dam.flashcards.dto.UsuarioDTO;
import com.dam.flashcards.dto.UsuarioInsertDTO;
import com.dam.flashcards.dto.UsuarioUpdateDTO;
import com.dam.flashcards.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
		Page<UsuarioDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/basico/{id}")
	public ResponseEntity<UsuarioBasicoDTO> findBasicoById(@PathVariable Long id) {
		UsuarioBasicoDTO dto = service.findBasicoById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioInsertDTO dto) {
		UsuarioDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO dto) {
		UsuarioDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);		
	}
	
	@PutMapping(value = "/nuevacontrasena/{id}")
	public ResponseEntity<Void> updateContrasena(@PathVariable Long id, @RequestBody NuevaContrasenaDTO dto) {
		service.updatePassword(id, dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
