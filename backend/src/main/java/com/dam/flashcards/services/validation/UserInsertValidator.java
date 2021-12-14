package com.dam.flashcards.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.dam.flashcards.dto.UsuarioInsertDTO;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UsuarioInsertDTO> {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UsuarioInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		
		Usuario usuario = repository.findByEmail(dto.getEmail());
		if(usuario != null) {
			list.add(new FieldMessage("Email", "El correo electrónico ya existe."));
		}
		
		usuario = repository.findByNombreDeUsuario(dto.getNombreDeUsuario());
		if(usuario != null) {
			list.add(new FieldMessage("nombreDeUsuario", "El nombre de usuario ya existe."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getFieldMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}