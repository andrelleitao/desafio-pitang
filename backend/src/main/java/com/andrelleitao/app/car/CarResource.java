package com.andrelleitao.app.car;

import com.andrelleitao.app.core.audited.BaseAuditedResource;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResource extends BaseAuditedResource<Long, Car> {
		
	@JsonProperty(required = true)
	private Integer year;
	private String licensePlate;
	private String model;	
	private String color;
	
	public CarResource() {
		super();
	}
	
	public CarResource(Car entity) {
		super(entity);
		
		if(entity != null) {
			setYear(entity.getYear());
			setLicensePlate(entity.getLicensePlate());			
			setModel(entity.getModel());						
			setColor(entity.getColor());
		}
	}
	
}
