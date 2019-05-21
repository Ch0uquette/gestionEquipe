package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTests {
	
	@Autowired
	private IUserService service;
	
	private static User resultUser;
	
	@Test
	public void aa_createUserValid() {
		User testUser = new User (null, "nom", "prenom", "email@email.fr", "azerty1234", null);
		resultUser = service.create(testUser);
		
//		String expectedResult = "SUCCESS";
//		
//		assertEquals("Un problème d'ajout d'un USER", expectedResult, result);
		assertNotNull(resultUser.getId());
	}
	
	//Si la méthode génère une exception, on peut écrire @Test(Expected=NullpointerException) sans le assert puisque la méthode attend une exception.
	@Test
	public void ab_createUserEmailExistant() {
		User testUser = new User (null, "nom", "prenom", "email@email.fr", "azerty1234", null);
		testUser = service.create(testUser);
		resultUser = service.create(new User (null, "nom", "prenom", "email@email.fr", "azerty1234", null));
		assertNull(resultUser);
		service.deleteById(testUser.getId());
	}
	
	@Test
	public void ac_createUserNoEmail() {
		resultUser = service.create(new User (null, "nom", "prenom", null, "azerty1234", null));
		assertNull(resultUser);
	}
	
	@Test
	public void ad_createUserNoPwd() {
		resultUser = service.create(new User (null, "nom", "prenom", "email@email.fr", null, null));
		assertNull(resultUser);
	}
	
	@Test
	public void ba_readById() {
		User testUser =  new User (null, "nom", "prenom", "email@email.fr", "azerty1234", null);
		resultUser = service.create(testUser);
		assertNotNull(service.readById(resultUser.getId()));
	}
	
	@Test
	public void bb_readNoId() {
		User testUser =  new User (null, "nom", "prenom", "email@email.fr", "azerty1234", null);
		resultUser = service.create(testUser);
		assertNull(service.readById(null));
	}
	
	@Test
	public void ca_deleteValid() {		
		User testUser =  new User (null, "nom", "prenom", "email@email.fr", "azerty1234", null);
		resultUser = service.create(testUser);
		assertTrue(service.deleteById(resultUser.getId()));
	}
	
	@Test
	public void cd_deleteInvalid() {
		User testUser =  new User (null, "nom", "prenom", "email@email.fr", "azerty1234", null);
		resultUser = service.create(testUser);
		assertFalse(service.deleteById(null));
		resultUser = null;
	}

//	@Test
//	public void da_readAll() {
//		User testUser1 =  new User (null, "nom", "prenom", "email@email.fr", "azerty1234", null);
//		resultUser = service.create(testUser1);
//		User testUser2 =  new User (null, "nom", "prenom", "email@email.fr", "1234", null);
//		resultUser = service.create(testUser2);
//		assertNotNull(service.readAll());
//		resultUser = null;
//	}
	
//	@Test
//	public void db_readAllInvalid() {
//		assertNull(service.readAll());	
//	}
	
	@Before
	public void beforeMethod() {
		System.out.println("************** Before Testing **************");
	}
	
	@After
	public void afterMethod() {
		System.out.println("************** After Testing **************");
		if (resultUser != null && resultUser.getId() != null) {
			service.deleteById(resultUser.getId());
		}
	}
}
