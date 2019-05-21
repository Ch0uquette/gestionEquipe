package com.fr.adaming.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao daoUser;

	Logger log = Logger.getLogger(UserService.class);

	@Override
	public User create(User user) {
		// Email et pwd ne doivent pas être null
		// Email doit être unique
		// Dto empêche l'insertion d'un id donc pas de pb à ce niveau

		if (user.getEmail() == null) {
			log.error("L'email ne doit pas être null");
			return null;
		} else if (user.getPwd() == null) {
			log.error("Le pwd ne doit pas être null");
			return null;
		} else if (user.getEmail() != null && user.getPwd() != null) {
			if (daoUser.findByEmail(user.getEmail()) == null) {

				try {
					User result = daoUser.save(user);
					log.trace("User enregistré et unique");
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

			} else {
				log.error("Le mail est déjà  utilisé");
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public User readById(Long id) {

		if (id != null) {
			try {
				User result = daoUser.findById(id).get();
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
	public User update(User user) {
		if (daoUser.existsById(user.getId())) {
			try {
				User result = daoUser.save(user);
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
	public boolean deleteById(Long id) {
		if (id != null && daoUser.existsById(id) == true) {
			daoUser.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<User> readAll() {
		return daoUser.findAll();
	}
}