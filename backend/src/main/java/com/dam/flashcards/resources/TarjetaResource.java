package com.dam.flashcards.resources;

import java.net.URI;

import javax.validation.Valid;

import com.dam.flashcards.dto.TarjetaBasicaDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.services.TarjetaService;

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
@RequestMapping(value = "/tarjetas")
public class TarjetaResource {

	@Autowired
	private TarjetaService service;

	/**
	 * Método para obtener la lista de Tarjetas paginada en formato reducido
	 * 
	 * @param categoriaId El ID de la categoría si se desea filtrar la lista por
	 *                    categoría
	 * @param texto       Un texto si se desea filtar la lista por Texto
	 * @return ResponseEntity<Page<TarjetaBasicaDTO>> La lista de tarjetas paginada
	 */
	@GetMapping
	public ResponseEntity<Page<TarjetaBasicaDTO>> findAll(Pageable pageable,
			@RequestParam(value = "categoriaId", defaultValue = "0") Long categoriaId,
			@RequestParam(value = "texto", defaultValue = "") String texto) {
		Page<TarjetaBasicaDTO> list = service.findAllPaged(categoriaId, texto, pageable);
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Método para obtener la lista de Tarjetas paginada en formato completo
	 * 
	 * @param categoriaId El ID de la categoría si se desea filtrar la lista por
	 *                    categoría
	 * @param texto       Un texto si se desea filtar la lista por Texto
	 * @return ResponseEntity<Page<TarjetaDTO>> La lista de tarjetas paginada
	 */
	@GetMapping(value = "/completa")
	public ResponseEntity<Page<TarjetaDTO>> findAllComplete(Pageable pageable,
			@RequestParam(value = "categoriaId", defaultValue = "0") Long categoriaId,
			@RequestParam(value = "texto", defaultValue = "") String texto) {
		Page<TarjetaDTO> list = service.findAllCompletePaged(categoriaId, texto, pageable);
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Método para obtener una tarjeta por su ID
	 * 
	 * @param id El ID de la tarjeta a buscar
	 * @return ResponseEntity<TarjetaDTO> Los datos de la tarjeta
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<TarjetaDTO> findById(@PathVariable Long id) {
		TarjetaDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * Método para añadir una tarjeta
	 * 
	 * @param dto Los datos de la tarjeta a añadir
	 * @return ResponseEntity<TarjetaDTO> Los datos de la tarjeta añadida
	 */
	@PostMapping
	public ResponseEntity<TarjetaDTO> insert(@Valid @RequestBody TarjetaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	/**
	 * Método para actualizar una tarjeta
	 * 
	 * @param id  El ID de la tarjeta a actualizar
	 * @param dto Los datos de la tarjeta actualizados
	 * @return ResponseEntity<TarjetaDTO> La tarjeta tras actualizarla
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<TarjetaDTO> update(@Valid @PathVariable Long id, @RequestBody TarjetaDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * Método para borrar una tarjeta
	 * 
	 * @param id El ID de la tarjeta a borrar
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
