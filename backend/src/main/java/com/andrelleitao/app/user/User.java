package com.andrelleitao.app.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.andrelleitao.app.car.Car;
import com.andrelleitao.app.core.audited.BaseAudited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseAudited<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
	@SequenceGenerator(name = "SEQ_USER", 
		sequenceName = "SEQ_USER", allocationSize = 1, initialValue = 1)
	@Column(name = "USER_ID")
	@Override
	public Long getId() {
		return super.getId();
	}
	
	private String firstName;
	
	@NotBlank
	@Column(name = "FIRST_NAME", nullable = false, length = 30)
	public String getFirstName() {
		return firstName;
	}
	
	private String lastName;
	
	@NotBlank
	@Column(name = "LAST_NAME", nullable = false, length = 30)
	public String getLastName() {
		return lastName;
	}
	
	private String email;
	
	@NotBlank
	@Email
	@Column(name = "EMAIL", nullable = false, length = 50)
	public String getEmail() {
		return email;
	}
	
	private LocalDate birthday;	
	
	@Column(name = "BIRTHDAY")
	public LocalDate getBirthday() {
		return birthday;
	}
	
	private String login;
	
	@NotBlank
	@Column(name = "LOGIN", length = 30, nullable = false)
	public String getLogin() {
		return login;
	}
	
	private String password;
	
	@NotBlank
	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}
	
	public String passwordSalt;
	
	@Column(name = "PASSWORD_SALT", nullable = false)
	public String getPasswordSalt() {
		return passwordSalt;
	}
	
	private String phone;
	
	@NotBlank
	@Column(name = "PHONE", nullable = false)
	public String getPhone() {
		return phone;
	}
	
	private Set<Car> cars;

	@ManyToMany
	@JoinTable(name = "user_car", joinColumns = @JoinColumn(name = "USER_ID"), 
		inverseJoinColumns = @JoinColumn(name = "CAR_ID"))
	public Set<Car> getCars() {
		if (cars == null) {
			cars = new HashSet<Car>();
		}
		return cars;
	}
	
	private LocalDateTime lastLogin;	
	
	@Column(name = "LAST_LOGIN")
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	
}
