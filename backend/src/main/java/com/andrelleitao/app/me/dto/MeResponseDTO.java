package com.andrelleitao.app.me.dto;

import com.andrelleitao.app.user.User;
import com.andrelleitao.app.user.UserResource;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "password", "id", "updatedAt" })
public class MeResponseDTO extends UserResource {
	
	public MeResponseDTO(User user) {
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setEmail(user.getEmail());
		setBirthday(user.getBirthday());
		setLogin(user.getLogin());
		setPhone(user.getPhone());
		setCars(toResource(user.getCars()));
		setCreatedAt(user.getCreatedAt());
		setLastLogin(user.getLastLogin());
	}
}
