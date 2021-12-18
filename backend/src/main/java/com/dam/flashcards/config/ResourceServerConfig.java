package com.dam.flashcards.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private JwtTokenStore tokenStore;

	private static final String[] PUBLIC = { "oauth/token", "/h2-console/**" };

	private static final String[] USUARIO_O_ADMINISTRADOR = { "/tarjetas/**", "/categorias/**" };

	private static final String[] ADMINISTRADOR = { "/usuarios/**", "/roles/**" };

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// para liberar acceso a la consola de H2 en test
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.authorizeRequests().antMatchers(PUBLIC).permitAll().antMatchers(USUARIO_O_ADMINISTRADOR).permitAll()
				.antMatchers(USUARIO_O_ADMINISTRADOR).hasAnyRole("USUARIO", "ADMINISTRADOR").antMatchers(ADMINISTRADOR)
				.hasRole("ADMINISTRADOR").anyRequest().authenticated();
	}

}
