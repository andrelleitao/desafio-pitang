package com.andrelleitao.app.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.enums.SearchOperation;
import com.andrelleitao.app.user.User;
import com.andrelleitao.app.user.UserService;

/**
 * Responsável por gerenciar dados sobre o usuário.
 */
@Component
public class UserInfo {
	@Autowired
	private UserService userService;
	
	/**
	 * Retorna o usuário logado.
	 */
	public User getLoggedUser() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("login", login, SearchOperation.EQUAL));
		List<User> users = userService.findByFilters(filters);
		return users.get(0);
	}
}
