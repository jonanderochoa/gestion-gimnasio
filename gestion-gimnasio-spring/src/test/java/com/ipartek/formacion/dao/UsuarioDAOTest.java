/**
 * 
 */
package com.ipartek.formacion.dao;

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

import com.ipartek.formacion.dbms.dao.interfaces.UsuarioDAO;
import com.ipartek.formacion.dbms.persistence.Usuario;

/**
 * @author Jon Ander Ochoa Ruiz
 * 20 de abr. de 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class UsuarioDAOTest {


	@Autowired
	UsuarioDAO uD;
	
	Usuario usuario;
	int[] codigos = {0,1,2,4};
	List<Usuario> usuarios;
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDAOTest.class);
	
	@Test
	public void testClase() {
		LOGGER.info("testClase");
		assertEquals("class com.ipartek.formacion.dbms.dao.UsuarioDAOImp", this.uD.getClass().toString());
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
	@Before
	public void setUp() throws Exception {
		usuario = new Usuario();
		usuario.setNombre("nora");
		usuario.setApellidos("gatitos");
		usuario.setUser("cats");
		usuario.setPass("blablabla");
		usuario.setEmail("nora@esploradora.com");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.ipartek.formacion.dbms.dao.UsuarioDAOImp#setDataSource(javax.sql.DataSource)}.
	 */
	@Test
	public void testSetDataSource() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ipartek.formacion.dbms.dao.UsuarioDAOImp#create(com.ipartek.formacion.dbms.persistence.Usuario)}.
	 */
	@Test
	public void testCreate() {
		LOGGER.info("testCreate");
		//Creamos un usuario
		//Usuario usu = uD.create(usuario);
		
	}

	/**
	 * Comprueba que el numero de elementos activos en la BBDD es 12
	 * Test method for {@link com.ipartek.formacion.dbms.dao.UsuarioDAOImp#getAll()}.
	 */
	@Test
	public void testGetAll() {
		LOGGER.info("getAll");
		//Cargamos la lista de la BBDD en la variable
		List<Usuario> usuarios = uD.getAll();
		//Numero de elementos de la BBDD
		int size = 12;
		//La lista tiene que ser identica
		assertEquals(size, usuarios.size());
	}

	/**
	 * Test method for {@link com.ipartek.formacion.dbms.dao.UsuarioDAOImp#getById(int)}.
	 */
	@Test
	public void testGetById() {
		LOGGER.info("testgetById");
		for(int i = 0; i < codigos.length; i++){
			Usuario usuario = uD.getById(codigos[i]);
			//Si no es nulo
			assertNotNull("El usuario no es nulo");
			//Si es igual
			//assertEquals(expected, actual);
		}
		
	}

	/**
	 * Test method for {@link com.ipartek.formacion.dbms.dao.UsuarioDAOImp#getByUser(java.lang.String)}.
	 */
	@Test
	public void testGetByUser() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ipartek.formacion.dbms.dao.UsuarioDAOImp#update(com.ipartek.formacion.dbms.persistence.Usuario)}.
	 */
	@Test
	public void testUpdate() {
		LOGGER.info("testUpdate");
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ipartek.formacion.dbms.dao.UsuarioDAOImp#delete(int)}.
	 */
	@Test
	public void testDelete() {
		LOGGER.info("testDelete");
		//Creamos el usuario en la BBDD metiendole el usuario
		Usuario usu = uD.create(usuario);
		//Borramos el usuario en la BBDD para comprobar que se hace bien
		uD.deleteReal(usu.getCodigo());
		//Si no es nulo
		assertNull("El usuario no se ha borrado correctamente", uD.getById(usu.getCodigo()));	
	}

}
