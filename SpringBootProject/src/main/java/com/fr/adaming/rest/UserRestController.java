package com.fr.adaming.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.UserCreateDto;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "api/users")
public class UserRestController {

	@Autowired
	private IUserService service;

	// Créer un Dto sans ID pour préciser qu'il ne doit jamais avoir d'ID dans
	// Swagger
	@ApiOperation(notes = "Créé un utilisateur à condition que son ID soit nul ou égal à 0", value = "create")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public User create(@Valid @RequestBody UserCreateDto dto) {
		User user = new User();
		user.setNom(dto.getNom());
		user.setPrenom(dto.getPrenom());
		user.setEmail(dto.getEmail());
		;
		user.setPwd(dto.getPwd());
		return service.create(user);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public User readById(@PathVariable Long id) {
		return service.readById(id);
	}

	@ApiOperation(notes = "Modifie un utilisateur à condition que son ID existe déjà", value = "update")
	@RequestMapping(path = "", method = RequestMethod.PUT)
	public User update(@RequestBody User user) {
		return service.update(user);
	}

	@RequestMapping(path = "", method = RequestMethod.DELETE)
	public boolean deleteById(@RequestBody Long id) {
		return service.deleteById(id);
	}

}
