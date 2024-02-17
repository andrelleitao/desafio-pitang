package com.andrelleitao.app.car;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.andrelleitao.app.core.audited.BaseAuditedService;
import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.enums.SearchOperation;

@Service
public class CarService extends BaseAuditedService<Long, Car, CarSpecification, CarRepository> {
	
	@Override
	public Car save(Car entity) throws CustomMessageException {
		
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("licensePlate", entity.getLicensePlate(), SearchOperation.EQUAL));
		List<Car> cars = findByFilters(filters);
		
		// Verifica se a placa já existe para para um ID diferente.
		if(cars != null && !cars.isEmpty()) {
			Car car = cars.get(0);
			if (car.getId() != entity.getId()) {
				throw new CustomMessageException("License plate already exists");
			}
		}
		
		return super.save(entity);
	}
		
}
