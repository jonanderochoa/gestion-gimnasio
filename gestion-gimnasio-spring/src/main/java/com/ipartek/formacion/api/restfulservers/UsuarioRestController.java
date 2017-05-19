/**
 * 
 */
package com.ipartek.formacion.api.restfulservers;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.formacion.dbms.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

/**
 * @author Jon Ander Ochoa Ruiz
 * 19 de may. de 2017
 */
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioRestController {

	@Inject
	UsuarioService uS;
	
	@Resource(name = "usuarioValidator")
	Validator validator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	/**
	 * Metodo encargado de mostrar todos los usuario
	 * @return Devuelve todos los usuario y el codigo Http
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> usuarios = uS.getAll();
		ResponseEntity<List<Usuario>> response = null;
		if(usuarios == null || usuarios.isEmpty()){//204
			response = new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}else{//200
			response = new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		}
		return response;
	}
	
	/**
	 * Metodo encargado de mostrar el usuario por codigo
	 * @param codigo Introduce el codigo del ejercicio
	 * @return Devuelve el usuario y el codigo http
	 */
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getById(@PathVariable("codigo") int codigo){
		Usuario usuario = uS.getById(codigo);
		ResponseEntity<Usuario> response = null;
		if(usuario == null){ //404
			response = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}else{ //200
			response = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
		return response;
	}
	
	/**
	 * Metodo encargado de crear un nuevo usuario
	 * @param usuario Nuevo usuario
	 * @param ucBuilder 
	 * @return Devuelve el codigo http en funcion de si se ha podido realizar la creacion
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody Usuario usuario, UriComponentsBuilder ucBuilder){
		ResponseEntity<Void> response = null;
		try{
			Usuario usu = uS.create(usuario);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/usuarios/{codigo}").buildAndExpand(usu.getCodigo()).toUri());
			response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}catch(Exception e){
			response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	/**
	 * Metodo encargado de actualizar un usuario
	 * @param codigo Codigo del usuario que queremos actualizar
	 * @param usuario Usuario con el que actualizaremos
	 * @return Devuelve el codigo http y el usuario actualizado si se produce
	 */
	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> update(@PathVariable("codigo") int codigo, @Valid @RequestBody Usuario usuario){
		Usuario usu = uS.getById(codigo);
		ResponseEntity<Usuario> response = null;
		if(usuario == null){//404
			response = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}else{
			try{//202
				usu = uS.update(usuario);
				response = new ResponseEntity<Usuario>(usu, HttpStatus.ACCEPTED);
			}catch(Exception e){//406
				response = new ResponseEntity<Usuario>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}
	
	/**
	 * Metodo encargado de borrar un usuario
	 * @param codigo Codigo del usuario que queremos eliminar
	 * @return Devuelve el codigo http resultante
	 */
	@RequestMapping(value="/{codigo}", method=RequestMethod.DELETE)
	public ResponseEntity<Usuario> delete(@PathVariable("codigo") int codigo){
		Usuario usuario = uS.getById(codigo);
		ResponseEntity<Usuario> response = null;
		if(usuario == null){//404
			response = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}else{//204
			uS.delete(codigo);
			response = new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
