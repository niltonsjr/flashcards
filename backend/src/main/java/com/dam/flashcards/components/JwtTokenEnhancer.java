package com.dam.flashcards.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Método que devuelve un AccessToken ampliado con información del usuario.
	 * 
	 * @param accessToken    el access token actual con fecha de expiración
	 * @param authentication la información de autenticación incluyendo los detalles
	 *                       del usuario
	 * @return OAuth2AccessToken un nuevo access token con información del usuario
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Optional<Usuario> obj = usuarioRepository.findByNombreDeUsuario(authentication.getName());
		Usuario usuario = obj.orElseThrow();
		Map<String, Object> map = new HashMap<>();
		map.put("nombre", usuario.getNombre());
		map.put("apellidos", usuario.getApellidos());
		map.put("usuarioId", usuario.getId());

		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);

		return accessToken;
	}

}
