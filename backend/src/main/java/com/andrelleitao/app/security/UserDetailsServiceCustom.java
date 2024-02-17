package com.andrelleitao.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.enums.SearchOperation;
import com.andrelleitao.app.user.UserService;

/**
 * A interface UserDetailsService é usada para recuperar dados relacionados ao usuário. Ele tem um método 
 * chamado loadUserByUsername() que pode ser substituído para personalizar o processo 
 * de localizar o usuário. Ele é usado pelo DaoAuthenticationProvider para carregar detalhes 
 * sobre o usuário durante a autenticação.
 */
@Component
public class UserDetailsServiceCustom implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("login", username, SearchOperation.EQUAL));		
		List<com.andrelleitao.app.user.User> users = userService.findByFilters(filters);
		
		String password = null;
		if(users != null && !users.isEmpty()) {			
			password = users.get(0).getPassword();
		} else {
			throw new UsernameNotFoundException("Login not found");
		}
		
		return new User(username, new BCryptPasswordEncoder().encode(password), new ArrayList<>());
	}
	
}
