package com.dam.flashcards.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.services.TarjetaService;

@RestController
@RequestMapping(value = "/tarjetas")
public class TarjetaResource {

	@Autowired
	private TarjetaService service;

	@GetMapping
	public ResponseEntity<Page<TarjetaDTO>> findAll(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "categoriaId", defaultValue = "0") Long categoriaId,
			@RequestParam(value = "lineasPorPagina", defaultValue = "12") Integer lineasPorPagina,
			@RequestParam(value = "direccion", defaultValue = "ASC") String direccion,
			@RequestParam(value = "ordenarPor", defaultValue = "nombre") String ordenarPor) {
		PageRequest pageRequest = PageRequest.of(pagina, lineasPorPagina, Direction.valueOf(direccion), ordenarPor);

		Page<TarjetaDTO> list = service.findAllPaged(categoriaId, pageRequest);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TarjetaDTO> findById(@PathVariable Long id) {
		TarjetaDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<TarjetaDTO> insert(@Valid @RequestBody TarjetaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TarjetaDTO> update(@Valid @PathVariable Long id, @RequestBody TarjetaDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
