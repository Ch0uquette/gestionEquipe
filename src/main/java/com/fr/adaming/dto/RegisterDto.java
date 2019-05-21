package com.fr.adaming.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RegisterDto {

	@NotNull
	private String nom;
	
	@NotNull
	private String prenom;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(min=4, max=20)
	private String pwd;
}

