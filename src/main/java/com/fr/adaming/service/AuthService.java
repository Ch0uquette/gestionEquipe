package com.fr.adaming.service;

import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IAuthDao;
import com.fr.adaming.entity.User;

@Service
public class AuthService implements IAuthService {

	@Autowired
	public IAuthDao dao;
	
	Logger log = Logger.getLogger(AuthService.class);
	
	@Override
	public User login(String nom, String pwd) {
		User user = null;
		if (nom != null || pwd != null) {
			
			try {
				user = dao.login(nom, pwd);
				return user;
			} catch (Exception e) {
				log.error(e.getStackTrace());
				return user = null;
			}
			
		}else {
			return user;
		}
	}

	@Override
	public String register(String nom, String prenom, String email, String pwd) {
				
					if (email == null ) {
						log.error("L'email ne doit pas être null");
						return "Echec lors de la création, l'email ne doit pas être vide";
					} else if (pwd == null) {
						log.error("Le pwd ne doit pas être null");
						return "Echec lors de la création, le pwd ne doit pas être vide";	
					}else if(email != null && pwd != null){
						if (dao.findByEmail(email) == null) {
							User user = new User();
							user.setNom(nom);
							user.setPrenom(prenom);
							user.setEmail(email);
							user.setPwd(pwd);
												
							try {
								dao.save(user);
								log.trace("User enregistré et unique");
								return "SUCCESS";
							} catch (Exception e) {
								if (e instanceof IllegalArgumentException) {
									log.error("Argument Exception: argument null");
								}else if (e instanceof SQLIntegrityConstraintViolationException) {
									log.error("Contrainte de violabilité");
								}else {
									log.error(e.getStackTrace());
								}
								return "Erreur lors de l'inscription dans la base de données";
							}
							
						}else {
							log.error("L'email est déjà  utilisé");
							return "L'email doit être unique";
						}
					}else {
						return "Erreur d'entrée de champ";
					}
			}
}
