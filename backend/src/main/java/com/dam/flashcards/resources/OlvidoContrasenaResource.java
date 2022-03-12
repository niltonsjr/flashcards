package com.dam.flashcards.resources;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.dam.flashcards.dto.EmailDTO;
import com.dam.flashcards.dto.UsuarioEmailDTO;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.services.EmailService;
import com.dam.flashcards.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contrasena_olvidada")
public class OlvidoContrasenaResource {

    @Autowired
    private UsuarioService service;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> enviarEmailContrasenaOlvidada(@RequestBody UsuarioEmailDTO usuarioEmail,
            HttpServletRequest request) {
        Usuario usuario = service.findByUsuarioEmail(usuarioEmail.getEmail());
        String token = UUID.randomUUID().toString();
        service.updateResetToken(token, usuario.getEmail());
        String resetContrasenaLink = "http://" + request.getServerName() + ":" + request.getServerPort()
                + "/reset_contrasena?token=" + token;
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setFromEmail("contacto@niltonsj.es");
        emailDTO.setFromName("FlashCards");
        emailDTO.setReplyTo("contacto@niltonsj.es");
        emailDTO.setTo(usuario.getEmail());
        emailDTO.setSubject("Contraseña olvidada");
        emailDTO.setBody(
                "Para resetear su contraseña, por favor, pulse en el link siguiente: <br /> " + resetContrasenaLink);
        emailDTO.setContentType("text/html");
        emailService.sendEmail(emailDTO);
        return ResponseEntity.ok().build();
    }

}
