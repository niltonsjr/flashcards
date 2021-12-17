package com.dam.flashcards.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.UsuarioRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioRepository.findByNombreDeUsuario(authentication.getName());
		Map<String, Object> map = new HashMap<>();
		map.put("nombre", usuario.getNombre());
		map.put("apellidos", usuario.getApellidos());
		map.put("usuarioId", usuario.getId());

		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);

		return accessToken;
	}

}
