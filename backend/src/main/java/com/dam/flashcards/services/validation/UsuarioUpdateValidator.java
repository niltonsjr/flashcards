package com.dam.flashcards.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dam.flashcards.dto.UsuarioUpdateDTO;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.resources.exceptions.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdateValid, UsuarioUpdateDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UsuarioRepository repository;

	@Override
	public void initialize(UsuarioUpdateValid ann) {
	}

	@Override
	public boolean isValid(UsuarioUpdateDTO dto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVars.get("id"));

		List<FieldMessage> list = new ArrayList<>();
		Optional<Usuario> obj = repository.findByEmail(dto.getEmail());
		if (obj.isPresent() && userId != obj.get().getId()) {
			list.add(new FieldMessage("Email", "El correo electrónico ya existe."));
		}

		obj = repository.findByNombreDeUsuario(dto.getNombreDeUsuario());
		if (obj.isPresent() && userId != obj.get().getId()) {
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