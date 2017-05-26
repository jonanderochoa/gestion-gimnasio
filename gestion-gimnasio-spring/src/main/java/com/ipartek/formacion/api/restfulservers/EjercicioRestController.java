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
import org.springframework.http.MediaType;
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


import com.ipartek.formacion.dbms.persistence.Ejercicio;
import com.ipartek.formacion.service.interfaces.EjercicioService;

/**
 * Este controlador se encarga de gestionar un servicio REST de lado del servidor.
 * Contiene los metodos necesarios para manipular los ejercicios.
 * @author Jon Ander Ochoa Ruiz
 * 12 de abr. de 2017
 */
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/ejercicios")
public class EjercicioRestController {

	@Inject
	EjercicioService eS;
	
	@Resource(name = "ejercicioValidator")
	Validator validator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	/**
	 * Metodo encargado de mostrar todos los ejercicio
	 * @return Devuelve todos los ejercicio y el codigo Http
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Ejercicio>> getAll(){
		List<Ejercicio> ejercicios = eS.getAll();
		ResponseEntity<List<Ejercicio>> response = null;
		if(ejercicios == null || ejercicios.isEmpty()){//204
			response = new ResponseEntity<List<Ejercicio>>(HttpStatus.NO_CONTENT);
		}else{//200
			response = new ResponseEntity<List<Ejercicio>>(ejercicios, HttpStatus.OK);
		}
		return response;
	}
	
	/**
	 * Metodo encargado de mostrar el ejercicio por codigo
	 * @param codigo Introduce el codigo del ejercicio
	 * @return Devuelve el ejercicio y el codigo http
	 */
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Ejercicio> getById(@PathVariable("codigo") int codigo){
		Ejercicio ejercicio = eS.getById(codigo);
		ResponseEntity<Ejercicio> response = null;
		if(ejercicio == null){ //404
			response = new ResponseEntity<Ejercicio>(HttpStatus.NOT_FOUND);
		}else{ //200
			response = new ResponseEntity<Ejercicio>(ejercicio, HttpStatus.OK);
		}
		return response;
	}
	
	/**
	 * Metodo encargado de crear un nuevo ejercicio
	 * @param ejercicio Nuevo ejercicio
	 * @param ucBuilder 
	 * @return Devuelve el codigo http en funcion de si se ha podido realizar la creacion
	 */
	@RequestMapping(method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Integer> create(@Valid @RequestBody Ejercicio ejercicio, UriComponentsBuilder ucBuilder){
		Ejercicio ej = eS.getById(ejercicio.getEjercicioCodigo());
		ResponseEntity<Integer> response = null;
		if(ej != null){ //Si ya existe...
			response = new ResponseEntity<Integer>(0, HttpStatus.CONFLICT);
		}
		try{ //Si no existe...
			Ejercicio ejer = eS.create(ejercicio);
			response = new ResponseEntity<Integer>(ejer.getEjercicioCodigo(), HttpStatus.CREATED);
		}catch(Exception e){ //Si hay un error...
			response = new ResponseEntity<Integer>(-1, HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	/**
	 * Metodo encargado de actualizar un ejercicio
	 * @param codigo Codigo del ejercicio que queremos actualizar
	 * @param ejercicio Ejercicio con el que actualizaremos
	 * @return Devuelve el codigo http y el ejercicio actualizado si se produce
	 */
	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Ejercicio> update(@PathVariable("codigo") int codigo, @Valid @RequestBody Ejercicio ejercicio){
		Ejercicio ejer = eS.getById(codigo);
		ResponseEntity<Ejercicio> response = null;
		if(ejercicio == null){//404
			response = new ResponseEntity<Ejercicio>(HttpStatus.NOT_FOUND);
		}else{
			try{//202
				ejer = eS.update(ejercicio);
				response = new ResponseEntity<Ejercicio>(ejer, HttpStatus.ACCEPTED);
			}catch(Exception e){//406
				response = new ResponseEntity<Ejercicio>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}
	
	/**
	 * Metodo enccargado de borrar un ejercicio
	 * @param codigo Codigo del ejercicio que queremos eliminar
	 * @return Devuelve el codigo http resultante
	 */
	@RequestMapping(value="/{codigo}", method=RequestMethod.DELETE)
	public ResponseEntity<Ejercicio> delete(@PathVariable("codigo") int codigo){
		Ejercicio ejercicio = eS.getById(codigo);
		ResponseEntity<Ejercicio> response = null;
		if(ejercicio == null){//404
			response = new ResponseEntity<Ejercicio>(HttpStatus.NOT_FOUND);
		}else{//204
			eS.delete(codigo);
			response = new ResponseEntity<Ejercicio>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
