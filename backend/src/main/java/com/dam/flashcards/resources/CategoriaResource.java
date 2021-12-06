package com.dam.flashcards.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {	
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
}
