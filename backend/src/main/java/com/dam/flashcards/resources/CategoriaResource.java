package com.dam.flashcards.resources;

import java.net.URI;

import com.dam.flashcards.dto.CategoriaDTO;
import com.dam.flashcards.services.CategoriaService;

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

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	/**
	 * Método para obtgener la lista de categorías
	 * 
	 * @param pageable
	 * @return ResponseEntity<Page<CategoriaDTO>> La lista de categorías paginada
	 */
	@GetMapping
	public ResponseEntity<Page<CategoriaDTO>> findAll(Pageable pageable) {
		Page<CategoriaDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Método para obtener una categoría por su ID
	 * 
	 * @param id El ID de la categoría
	 * @return ResponseEntity<CategoriaDTO> Los datos de la categoría
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
		CategoriaDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * Método para añadir una categoría
	 * 
	 * @param dto La categoría a añadir
	 * @return ResponseEntity<CategoriaDTO> Los datos de la categoría añadida
	 */
	@PostMapping
	public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	/**
	 * Método para actualizar una categoría
	 * 
	 * @param id  El ID de la categoría a actualizar
	 * @param dto Los datos de la categoría actualizados
	 * @return ResponseEntity<CategoriaDTO> La categoría tras actualizarla
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * Método para borrar una categoría
	 * 
	 * @param id El ID de la categoría a borrar
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
