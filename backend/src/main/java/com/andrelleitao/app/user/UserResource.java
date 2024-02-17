package com.andrelleitao.app.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import com.andrelleitao.app.car.Car;
import com.andrelleitao.app.car.CarResource;
import com.andrelleitao.app.core.audited.BaseAuditedResource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource extends BaseAuditedResource<Long, User> {
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;
	private String login;
	private String password;
	private String phone;
	private Set<CarResource> cars;
	private LocalDateTime lastLogin;
	
	public UserResource() {
		super();
	}
	
	public UserResource(User entity) {
		super(entity);
		
		if(entity != null) {
			setFirstName(entity.getFirstName());
			setLastName(entity.getFirstName());
			setEmail(entity.getEmail());
			setBirthday(entity.getBirthday());
			setLogin(entity.getLogin());
			setPassword(entity.getPassword());
			setPhone(entity.getPhone());
			setCars(toResource(entity.getCars()));
			setLastLogin(entity.getLastLogin());
		}
	}
	
	public Set<CarResource> toResource(Set<Car> cars) {
		return cars.stream().map(CarResource::new).collect(Collectors.toSet());
	}
	
}
