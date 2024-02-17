package com.andrelleitao.app.user;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrelleitao.app.car.Car;
import com.andrelleitao.app.car.CarResource;
import com.andrelleitao.app.car.CarService;
import com.andrelleitao.app.core.audited.BaseAuditedController;

@RestController
@RequestMapping("api/users")
public class UserController
		extends BaseAuditedController<Long, User, UserService, UserSpecification, UserRepository, UserResource> {
		
	@Autowired
	private CarService carService;
	
	@Override
	public User fromResource(UserResource resource, User entity) {
		entity = super.fromBaseResource(resource, entity);
		entity.setFirstName(resource.getFirstName());
		entity.setLastName(resource.getLastName());
		entity.setEmail(resource.getEmail());
		entity.setBirthday(resource.getBirthday());
		entity.setLogin(resource.getLogin());
		entity.setPassword(resource.getPassword());
		entity.setPhone(resource.getPhone());
				
		entity.setCars(new HashSet<Car>());
		if (resource.getCars() != null && !resource.getCars().isEmpty()) {
			for(CarResource car : resource.getCars()) {
				Optional<Car> opt = carService.findById(car.getId());
				if(opt.isPresent()) {
					entity.getCars().add(opt.get());
				}
			}
		}
		
		return entity;
	}

	@Override
	public UserResource toResource(User entity) {
		return new UserResource(entity);
	}
}
