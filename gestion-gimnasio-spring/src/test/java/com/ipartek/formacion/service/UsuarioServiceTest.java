/**
 * 
 */
package com.ipartek.formacion.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipartek.formacion.dbms.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

/**
 * @author Jon Ander Ochoa Ruiz
 * 19 de abr. de 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class UsuarioServiceTest {

	@Autowired
	UsuarioService uS;
	Usuario usuario;
	int[] codigos = {0,1,2,4};
	List<Usuario> usuarios;
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceTest.class);
	
	/**
	 * Configura la prueba
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		usuario = new Usuario();
		usuario.setNombre("nora");
		usuario.setApellidos("gatitos");
		usuario.setUser("cats");
		usuario.setPass("blablabla");
		usuario.setEmail("nora@esploradora.jar");
	}
	
	@Test
	public void testClase(){
		LOGGER.info("testClase");
		assertEquals("class com.ipartek.formacion.service.UsuarioServiceImp", this.uS.getClass().toString());
	}

	/**
	 * Test method for {@link com.ipartek.formacion.service.UsuarioServiceImp#create(com.ipartek.formacion.dbms.persistence.Usuario)}.
	 */
	@Test
	public void testCreate() {
		//
	}

	/**
	 * Test method for {@link com.ipartek.formacion.service.UsuarioServiceImp#getAll()}.
	 */
	@Test
	public void testGetAll() {
		//
	}

	/**
	 * Test method for {@link com.ipartek.formacion.service.UsuarioServiceImp#getById(int)}.
	 */
	@Test
	public void testGetById() {
		//
	}

	/**
	 * Test method for {@link com.ipartek.formacion.service.UsuarioServiceImp#getByUser(java.lang.String)}.
	 */
	@Test
	public void testGetByUser() {
		//
	}

	/**
	 * Test method for {@link com.ipartek.formacion.service.UsuarioServiceImp#update(com.ipartek.formacion.dbms.persistence.Usuario)}.
	 */
	@Test
	public void testUpdate() {
		//
	}

	/**
	 * Test method for {@link com.ipartek.formacion.service.UsuarioServiceImp#delete(int)}.
	 */
	@Test
	public void testDelete() {
		//
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
}
