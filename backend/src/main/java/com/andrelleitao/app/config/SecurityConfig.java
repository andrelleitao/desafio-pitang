package com.andrelleitao.app.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.andrelleitao.app.security.AuthenticationEntryPointCustom;
import com.andrelleitao.app.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationEntryPointCustom authenticationEntryPointCustom;
	@Autowired
	private JwtAuthFilter authenticationFilter;
	
	@Bean
	AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailsService) throws Exception {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return new ProviderManager(provider);

	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				// API's que não precisam de autenticação.
				.requestMatchers("/api/signin").permitAll()
				.requestMatchers("/api/users/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				/*.requestMatchers("/api-docs/**").permitAll().requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/swagger-ui.html").permitAll().requestMatchers("/delivery/tracking/*").permitAll()
				.requestMatchers("/delivery/editor/*").permitAll().requestMatchers("/actuator/**").permitAll()
				.requestMatchers("/ws/test/**").permitAll()*/

				// Informa todas as solicitações para endereços que não constam em
				// requestMatchers só serão
				// possíveis caso o usuário esteja autenticado.
				.anyRequest().authenticated()).httpBasic(Customizer.withDefaults())

				// Aponta para um entry point customizado. Ele permitirá tratar o fluxo
				// quando o usuário não estiver autenticado.
				.exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPointCustom))

				// Não armazena o estado do usuário.
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Adiciona um filtro para validar o token de cada solicitação.
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.cors(Customizer.withDefaults());

		return http.build();

	}
	
}
