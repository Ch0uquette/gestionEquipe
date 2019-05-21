package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Equipe;

public interface IEquipeService {
	
	public Equipe create(Equipe equipe);
	public Equipe readById (Long id);
	public List<Equipe> readAll();
	public String update (Equipe equipe);
	public boolean deleteById (Long id);
	
}
