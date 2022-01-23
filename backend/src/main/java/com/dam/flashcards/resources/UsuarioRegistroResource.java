package com.dam.flashcards.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dam.flashcards.dto.UsuarioDTO;
import com.dam.flashcards.dto.UsuarioRegistroDTO;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.services.UsuarioService;

@RestController
@RequestMapping(value = "/registro")
public class UsuarioRegistroResource {

	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<UsuarioDTO> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO dto) {
		Usuario existe = service.findUsuarioByNombreDeUsuario(dto.getNombreDeUsuario());
		if (existe != null) {
			return ResponseEntity.badRequest().body(dto);
		}
		UsuarioDTO newDto = service.register(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
}
