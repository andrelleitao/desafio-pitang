package com.andrelleitao.app.car;

import com.andrelleitao.app.core.audited.BaseAudited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car")
@Getter
@Setter
public class Car extends BaseAudited<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAR")
	@SequenceGenerator(name = "SEQ_CAR", 
		sequenceName = "SEQ_CAR", allocationSize = 1, initialValue = 1)
	@Column(name = "CAR_ID")
	@Override
	public Long getId() {
		return super.getId();
	}
	
	private Integer year;
	
	@NotNull
	@Column(name = "YEAR", nullable = false)
	public Integer getYear() {
		return year;
	}
	
	private String licensePlate;
	
	@NotBlank
	@Column(name = "LICENSE_PLATE", nullable = false, length = 10)
	public String getLicensePlate() {
		return licensePlate;
	}
	
	private String model;
	
	@NotBlank
	@Column(name = "MODEL", nullable = false, length = 30)
	public String getModel() {
		return model;
	}
	
	private String color;
	
	@NotBlank
	@Column(name = "COLOR", nullable = false, length = 30)
	public String getColor() {
		return color;
	}
	
}
