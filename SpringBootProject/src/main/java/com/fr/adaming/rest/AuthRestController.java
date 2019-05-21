package com.fr.adaming.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IAuthService;

@RestController
@RequestMapping(path = "api/auth")
public class AuthRestController {

	@Autowired
	private IAuthService service;

	@RequestMapping(path="/login", method = RequestMethod.POST)
	private User login (@Valid @RequestBody LoginDto loginDto) {
		User u = service.login(loginDto.getEmail(), loginDto.getPwd());
		return u;
	}
	
	@RequestMapping(path="/register", method = RequestMethod.POST)
	private String register (@Valid @RequestBody RegisterDto registerDto) {
		return service.register(registerDto.getNom(), registerDto.getPrenom(), registerDto.getEmail(), registerDto.getPwd());
	}
}
