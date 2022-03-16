package com.dam.flashcards.resources;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.dam.flashcards.dto.EmailDTO;
import com.dam.flashcards.dto.NuevaContrasenaDTO;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping
public class OlvidoContrasenaResource {

        @Autowired
        private UsuarioService service;

        @Autowired
        private EmailService emailService;

        @PostMapping(value = "/contrasena_olvidada")
        public ResponseEntity<Void> enviarEmailContrasenaOlvidada(@RequestBody UsuarioEmailDTO usuarioEmail,
                        HttpServletRequest request) {
                Usuario usuario = service.findByUsuarioEmail(usuarioEmail.getEmail());
                String token = UUID.randomUUID().toString();
                service.updateResetToken(token, usuario.getEmail());
                String referrer = request.getHeader("referer");
                String resetContrasenaLink = referrer.concat("auth/reset_contrasena?token=").concat(token);
                System.out.println(referrer);
                // String resetContrasenaLink = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
                //                 .path("/auth/reset_contrasena").queryParam("token", token).build().toString();
                EmailDTO emailDTO = new EmailDTO();
                emailDTO.setFromEmail("contacto@niltonsj.es");
                emailDTO.setFromName("FlashCards");
                emailDTO.setReplyTo("contacto@niltonsj.es");
                emailDTO.setTo(usuario.getEmail());
                emailDTO.setSubject("Este es el link para resetear su contraseña.");
                emailDTO.setBody("<p>Hola,</p>"
                                + "<p>Has solicitado resetear su contraseña.</p>"
                                + "<p>Pulse en en enlace abajo para cambiarla:</p>"
                                + "<p><a href=\"" + resetContrasenaLink + "\">Cambiar mi contraseña</a></p>"
                                + "<br>"
                                + "<p>Ignore este correo si se acuerda de su contraseña o si no ha solicitado cambiarla.</p>");
                emailDTO.setContentType("text/html");
                emailService.sendEmail(emailDTO);
                return ResponseEntity.ok().build();
        }

        @PostMapping(value = "/reset_contrasena")
        public ResponseEntity<Void> resetearContrasena(@RequestBody NuevaContrasenaDTO contrasena) {
                Usuario usuario = service.findUsuarioByResetToken(contrasena.getToken());
                service.resetPassword(usuario.getId(), contrasena);
                return ResponseEntity.ok().build();
        }
}
