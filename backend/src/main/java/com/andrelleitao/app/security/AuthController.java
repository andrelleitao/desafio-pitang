package com.andrelleitao.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.enums.SearchOperation;
import com.andrelleitao.app.security.dto.AuthRequestDTO;
import com.andrelleitao.app.security.dto.AuthResponseDTO;
import com.andrelleitao.app.user.User;
import com.andrelleitao.app.user.UserService;
import com.andrelleitao.app.util.EncryptUtils;

/**
 * Api responsável pela realização do login.
 */
@RestController
@RequestMapping("api")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;	
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;

	@PostMapping(value = "/signin")
	public ResponseEntity<AuthResponseDTO> auth(@RequestBody AuthRequestDTO entity) throws CustomMessageException {
		String password = entity.getPassword();
		
		// Identifica o usuário para recuperar o salt da senha.
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("login", entity.getLogin(), SearchOperation.EQUAL));
		List<User> users = userService.findByFilters(filters);		
		if(users != null && !users.isEmpty()) {
			password = EncryptUtils.getEncryptedSha256(password);
			password = EncryptUtils.getEncryptedSha256(password, users.get(0).getPasswordSalt());
		}
		
		UserDetails userDetails = auth(entity.getLogin(), password);		
		String generatedToken = jwtService.generateToken(userDetails.getUsername());
		
		// Registra a data e hora do login.
		userService.setLastLogin(users.get(0).getId());
		
		return ResponseEntity.ok(new AuthResponseDTO(generatedToken));
	}
	
	private UserDetails auth(String login, String password) throws CustomMessageException {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login, password));			
			return (UserDetails) authentication.getPrincipal();
		} catch (DisabledException e) {
			throw new CustomMessageException(e.getMessage());
		} catch (BadCredentialsException e) {			
			throw new CustomMessageException("Invalid login or password");
		}
	}
	
}
