package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.User;

public interface IUserService {

	public User create(User user);
	public User readById (Long id);
	public User update (User user);
	public List<User> readAll();
	public boolean deleteById (Long id);
	
}
