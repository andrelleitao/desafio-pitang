package com.andrelleitao.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.andrelleitao.app.car.Car;
import com.andrelleitao.app.car.CarRepository;
import com.andrelleitao.app.car.CarService;
import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.enums.SearchOperation;
import com.andrelleitao.app.user.User;
import com.andrelleitao.app.user.UserRepository;
import com.andrelleitao.app.user.UserService;

import jakarta.annotation.Resource;

@SpringBootTest
@Transactional
class DesafioPitangApplicationTests {
	
	@Resource
	CarService carService;
	@Resource
	UserService userService;
	@Resource
	private UserRepository userRepository;
	@Resource
	private CarRepository carRepository;
		
	@Test
	void givenUser_whenSave_thenGetOk() {
		User user = newUser();
		
		userRepository.save(user);
        		
        Optional<User> user2 = userRepository.findById(user.getId());
        assertEquals(user.getFirstName(), user2.get().getFirstName());
	}
	
	@Test
	void givenCar_whenSave_thenGetOk() {
		Car car = newCar();
		
		carRepository.save(car);
        
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("licensePlate", car.getLicensePlate(), SearchOperation.EQUAL));
		List<Car> cars = carService.findByFilters(filters);
		
		assertEquals(car.getLicensePlate(), cars.get(0).getLicensePlate());        
	}
	
	@Test()
	public void givenLicensePlate_whenAlreadyExists_thenGetBadRequest() {
		Exception exception = assertThrows(CustomMessageException.class, () -> {
			Car car = newCar();				
			car.setLicensePlate("XXX-1245");
			carService.save(car);
			
			Car car2 = newCar();
			car2.setLicensePlate("XXX-1245");
			carService.save(car2);
		});

		String expectedMessage = "License plate already exists";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test()
	public void givenLogin_whenAlreadyExists_thenGetBadRequest() {
		Exception exception = assertThrows(CustomMessageException.class, () -> {
			User user = newUser();
			user.setEmail("email1@teste.com");
			user.setLogin("user1");
			userService.save(user);
			
			User user2 = newUser();	
			user2.setEmail("email2@teste.com");
			user2.setLogin("user1");
			userService.save(user2);
		});

		String expectedMessage = "Login already exists";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test()
	public void givenEmail_whenAlreadyExists_thenGetBadRequest() {
		Exception exception = assertThrows(CustomMessageException.class, () -> {
			User user = newUser();	
			user.setLogin("test1");
			user.setEmail("teste@gmail.com");
			userService.save(user);
			
			User user2 = newUser();
			user2.setLogin("test2");
			user2.setEmail("teste@gmail.com");
			userService.save(user2);
		});

		String expectedMessage = "Email already exists";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	/**
	 * Responsável por criar um novo usuário.
	 */
	private User newUser() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Baptist");
		user.setPassword("12345678");
		user.setEmail("teste@gmail.com");
		user.setLogin("login.test");
		user.setBirthday(LocalDate.of(1983, 5, 5));
		user.setPhone("558188888888");
		
		return user;
	}
	
	/**
	 * Responsável por criar um novo carro.
	 */
	private Car newCar() {
		Car car = new Car();
		car.setColor("White");
		car.setLicensePlate("XXX-1247");
		car.setModel("Car model");
		car.setYear(2014);
		
		return car;
	}

}
