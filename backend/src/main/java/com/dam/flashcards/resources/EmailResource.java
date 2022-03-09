package com.dam.flashcards.resources;

import com.dam.flashcards.dto.EmailDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody EmailDTO dto) {
        return ResponseEntity.noContent().build();
    }
    
}
