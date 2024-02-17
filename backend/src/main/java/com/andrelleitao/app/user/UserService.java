package com.andrelleitao.app.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrelleitao.app.car.Car;
import com.andrelleitao.app.car.CarService;
import com.andrelleitao.app.core.audited.BaseAuditedService;
import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.enums.SearchOperation;
import com.andrelleitao.app.util.EncryptUtils;

@Service
public class UserService extends BaseAuditedService<Long, User, UserSpecification, UserRepository> {
	
	@Autowired
	private CarService carService;
	
	@Override
	public User save(User entity) throws CustomMessageException {
		
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("email", entity.getEmail(), SearchOperation.EQUAL));
		List<User> users = findByFilters(filters);
		
		// Verifica se o email já existe no banco.
		if(users != null && !users.isEmpty()) {
			User user = users.get(0);
			if (user.getId() != entity.getId()) {
				throw new CustomMessageException("Email already exists");
			}
		}
		
		// Verifica se o login já existe no banco.
		filters = new ArrayList<>();
		filters.add(new Filter("login", entity.getLogin(), SearchOperation.EQUAL));
		users = findByFilters(filters);
		
		// Verifica se a placa já existe para para um ID diferente.
		if(users != null && !users.isEmpty()) {
			User user = users.get(0);
			if (user.getId() != entity.getId()) {
				throw new CustomMessageException("Login already exists");
			}
		}
		
		// Gera uma senha criptografada para o usuário.
		String[] passwords = generatePassword(entity.getPassword());
		entity.setPassword(passwords[0]);
		entity.setPasswordSalt(passwords[1]);
		
		super.save(entity);
		
		if(entity.getCars() != null && !entity.getCars().isEmpty()) {
			for(Car car : entity.getCars()) {				
				carService.save(car);
			}
		}
		
		return super.save(entity);
	}
	
	/**
	 * Retorna a senha criptofrada e o SALT.
	 */
	private String[] generatePassword(String password) {
		byte[] salt = new byte[16];
		String passwordSha = EncryptUtils.getEncryptedSha256(password);
		String saltSha = EncryptUtils.getEncryptedSha256(salt.toString());
		String finalPassword = EncryptUtils.getEncryptedSha256(passwordSha, saltSha);
		return new String[] { finalPassword, saltSha };
	}
	
	public void setLastLogin(Long id) throws CustomMessageException {
		Optional<User> opt = findById(id);
		if(opt.isPresent()) {
			opt.get().setLastLogin(LocalDateTime.now());
			super.save(opt.get());
		}
	}
	
}
