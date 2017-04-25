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
	Usuario usuario2;
	int[] codigos = {0,1,2,4};
	List<Usuario> usuarios;
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDAOTest.class);
	
	@Test
	public void testClase() {
		LOGGER.info("testClase");
		assertEquals("class com.ipartek.formacion.dbms.dao.UsuarioDAOImp", this.uD.getClass().toString());
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {}
	@After
	public void tearDown() throws Exception {}
	@Test
	public void testSetDataSource() {}
	
	/**
	 * Crea un nuevo usuario y le carga unos valores
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		LOGGER.info("setUp");
		
		usuario = new Usuario();
		usuario.setNombre("miniyo");
		usuario.setApellidos("supermi");
		usuario.setUser("mini");
		usuario.setPass("super");
		usuario.setEmail("min@super.com");
		
		usuario2 = new Usuario();
		usuario2.setNombre("prueba");
		usuario2.setApellidos("prueba");
		usuario2.setUser("prueba");
		usuario2.setPass("prueba");
		usuario2.setEmail("prueba@prueba.com");
	}

	/**
	 * Comprueba que el numero de elementos activos en la BBDD es 12
	 */
	@Test(timeout = 400) //Tiempo maximo de carga
	public void testGetAll() {
		LOGGER.info("TestgetAll");
		LOGGER.info("tamaño de BBDD usuario", usuarios.size());
		//Cargamos la lista de la BBDD en la variable
		List<Usuario> usuarios = uD.getAll();
		//Numero de elementos de la BBDD
		int size = 12;
		//La lista tiene que tener IGUAL tamaño que size
		assertEquals(size, usuarios.size());
	}

	/**
	 * test que comprueba si recibe bien un usuario de la BBDD
	 */
	@Test
	public void testGetById() {
		LOGGER.info("testgetById");
		for(int i = 0; i < codigos.length; i++){
			//Guarda en usuario los usuario de la BBDD [0, 1, 2, 4]
			Usuario usuario = uD.getById(codigos[i]);
			//El usuario no deberia ser nulo
			assertNotNull("El usuario no deberia ser nulo", usuario);
			//El usuario deberia ser nulo
			assertNull("El usuario deberia ser nulo", uD.getById(-100));
			//El usuario deberia ser igual
			assertEquals(uD.getById(usuario.getCodigo()), usuario);
		}
	}

	/**
	 * Prueba la creacion de un Usuario
	 */
	@Test
	public void testCreate() {
		LOGGER.info("testCreate");
		//Creamos un usuario
		Usuario usu = uD.create(usuario);
		//El usu no deberia ser nulo
		assertNotNull("No deberia ser nulo", uD.getById(usu.getCodigo()));
		//El usuario de la BBDD que tienen el mismo codigo que usu, deberia de ser igual a usu
		assertEquals(uD.getById(usu.getCodigo()), usu);
		//Elimina el usuario que hemos creado
		uD.deleteReal(usu.getCodigo());
	}
	
	/**
	 * Este test
	 */
	@Test
	public void testGetByUser() {
		LOGGER.info("testgetByUser");
		//Carga el usuario que contenga el user=mini
		Usuario usu = uD.getByUser("mini");
		//El usuario no deberia ser nulo
		assertNotNull("el objeto es nulo", usu);
		//Carga el usuario que contenga el user=cats
		usu = uD.getByUser("cats");
		//El usuario deberia ser nulo
		assertNull("el objeto es nulo", usu);
	}

	/**
	 * Este test 
	 */
	@Test
	public void testUpdate() {
		LOGGER.info("testUpdate");
		//Si existe el usuario con user= mini lo eliminamos
		if(uD.getByUser("mini") != null){
			uD.deleteReal(uD.getByUser("mini").getCodigo());
		}else{
			//Creamos el usuario
			Usuario usu = uD.create(usuario);
			//actualizamos el usuario
			uD.update(usuario2);
			LOGGER.info(uD.getById(usu.getCodigo()).toString());
			//Eliminamos el usuario modificado
			uD.deleteReal(usu.getCodigo());
		}
	}

	/**
	 * Este test crea un usuario y despues comprueba que lo elimina correctamente
	 */
	@Test
	public void testDelete() {
		LOGGER.info("testDelete");
		//Si existe el usuario con user= mini lo eliminamos
		if(uD.getByUser("mini") != null){
			uD.deleteReal(uD.getByUser("mini").getCodigo());
		}else{
			//Creamos el usuario en la BBDD metiendole el usuario
			Usuario usu = uD.create(usuario);
			//Borramos el usuario en la BBDD para comprobar que se hace bien
			uD.deleteReal(usu.getCodigo());
			//Si es nulo
			assertNull("El usuario deberia de ser nulo", uD.getById(usu.getCodigo()));
		}
	}

}
