package com.andrelleitao.app.car;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrelleitao.app.component.UserInfo;
import com.andrelleitao.app.core.audited.BaseAuditedController;
import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.user.User;
import com.andrelleitao.app.user.UserService;

@RestController
@RequestMapping("api/cars")
@CrossOrigin
public class CarController
		extends BaseAuditedController<Long, Car, CarService, CarSpecification, CarRepository, CarResource> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInfo userInfo;
	
	@Override
	public Car fromResource(CarResource resource, Car entity) {
		entity = super.fromBaseResource(resource, entity);
		entity.setYear(resource.getYear());
		entity.setLicensePlate(resource.getLicensePlate());
		entity.setModel(resource.getModel());
		entity.setColor(resource.getColor());		
		return entity;
	}

	@Override
	public CarResource toResource(Car entity) {
		return new CarResource(entity);
	}
	
	@Override
	public ResponseEntity<CarResource> save(@RequestBody Car entity) throws CustomMessageException {
		getService().save(entity);		
		User loggedUser = userInfo.getLoggedUser();
		
		// Associa ao usuário.
		loggedUser.getCars().add(entity);
		userService.save(loggedUser);
		
		return ResponseEntity.ok(toResource(entity));
	}
		
	@Override
	public List<CarResource> findAll() {
		User loggedUser = userInfo.getLoggedUser();		
		return loggedUser.getCars().stream().map(this::toResource).collect(Collectors.toList());
	}
	
	@Override
	public ResponseEntity<Void> delete(Long id) throws CustomMessageException {
		Optional<Car> opt = getService().findById(id);
		User loggedUser = userInfo.getLoggedUser();	
		loggedUser.getCars().remove(opt.get());
		userService.save(loggedUser);
		getService().deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
