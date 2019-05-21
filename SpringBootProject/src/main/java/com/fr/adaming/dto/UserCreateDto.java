package com.fr.adaming.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Equipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserCreateDto {
	
	@NotNull
	private String nom;
	private String prenom;
	private String email;
	
	@NotNull
	@Min (value = 8)
	private String pwd;
	private Equipe equipe;
}
