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

import com.fr.adaming.entity.Equipe;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EquipeServiceTests {

	@Autowired
	private IEquipeService service;

	private static Equipe resultEquipe;
	
	@Test
	public void aa_createEquipeValid() {
		Equipe testEquipe = new Equipe (null, "nom", 15);
		resultEquipe = service.create(testEquipe);
		
//		String expectedResult = "SUCCESS";
//		
//		assertEquals("Un problème d'ajout d'une EQUIPE", expectedResult, result);
		assertNotNull(service.create(testEquipe));
		
	}
	
	//Si la méthode génère une exception, on peut écrire @Test(Expected=NullpointerException) sans le assert puisque la méthode attend une exception.
	@Test
	public void ab_createEquipeExistingName() {
		aa_createEquipeValid();
		Equipe testEquipe = new Equipe (null, "nom", 15);
		assertNull(service.create(testEquipe));
	}
	
	@Test
	public void ac_createEquipeNoName() {
		Equipe testEquipe = new Equipe (null, null, 15);
		assertNull(service.create(testEquipe));
	}
	
	@Test
	public void ba_readById() {
		Equipe testEquipe =  new Equipe (null, "nom", 15);
		resultEquipe = service.create(testEquipe);
		System.out.println("id readById: "+resultEquipe.getId());
		assertNotNull(service.readById(testEquipe.getId()));
	}
	
	@Test
	public void ca_deleteValid() {
		System.out.println("*********** DeleteValid ***************");
		Equipe testEquipe = new Equipe (null, "nom1", 15);
		service.create(testEquipe);
		System.out.println("id delete: "+testEquipe.getId());
		assertTrue(service.deleteById(testEquipe.getId()));
	}
	
	@Test
	public void cb_deleteInvalid() {
		System.out.println("*********** DeleteInValid ***************");
		Equipe testEquipe = new Equipe (5L, "nom1", 15);
		assertFalse(service.deleteById(testEquipe.getId()));
	}

	@Before
	public void beforeMethod() {
		System.out.println("************** Before Testing **************");
	}
	
	@After
	public void afterMethod() {
		System.out.println("************** After Testing **************");
		if (resultEquipe != null && resultEquipe.getId() != null) {
			service.deleteById(resultEquipe.getId());
		}
	}
}