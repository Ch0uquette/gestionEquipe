package com.fr.adaming.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEquipeDao;
import com.fr.adaming.entity.Equipe;

@Service
public class EquipeService implements IEquipeService {

	@Autowired
	private IEquipeDao daoEquipe;
	Logger log = Logger.getLogger(UserService.class);

	@Override
	public Equipe create(Equipe equipe) {

		try {
			Equipe result = daoEquipe.save(equipe);
			log.trace("Equipe enregistrée et unique");
			return result;
		} catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				log.error("Argument Exception: argument null");
			} else if (e instanceof SQLIntegrityConstraintViolationException) {
				log.error("Contrainte de violabilité");
			} else {
				log.error(e.getStackTrace());
			}
			return null;
		}
	}

	@Override
	public Equipe readById(Long id) {

		if (id != null) {
			try {
				Equipe result = daoEquipe.findById(id).get();
				return result;
			} catch (Exception e) {
				log.error(e.getStackTrace());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public List<Equipe> readAll() {
		return daoEquipe.findAll();
	}

	@Override
	public String update(Equipe equipe) {
		System.out.println("************************UPDATE******************************");
		Long id = equipe.getId();
		System.out.println("DEBUG " + equipe.getId());
		if (id != null) {
			if (daoEquipe.existsById(id)) {
				daoEquipe.save(equipe);
				System.out.println("DEBUG update Mise à  jour");
				return "SUCCESS Mise à  jour";
			} else {
				System.out.println("DEBUG update l'équipe existe déjà ");
				return "FAIL l'équipe n'existe  pas";
			}
		} else {
			return "Vous devez mettre un id pour mettre à jour";
		}

	}

	@Override
	public boolean deleteById(Long id) {
		if (id != null && daoEquipe.existsById(id) == true) {
			daoEquipe.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
