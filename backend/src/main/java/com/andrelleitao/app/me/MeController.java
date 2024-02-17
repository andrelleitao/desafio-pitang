package com.andrelleitao.app.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrelleitao.app.component.UserInfo;
import com.andrelleitao.app.me.dto.MeResponseDTO;

@RestController
@RequestMapping("api")
public class MeController {
	@Autowired
	private UserInfo userInfo;
	
	@GetMapping("/me")
	public ResponseEntity<MeResponseDTO> me() {
		return ResponseEntity.ok(new MeResponseDTO(userInfo.getLoggedUser()));
	} 
}
