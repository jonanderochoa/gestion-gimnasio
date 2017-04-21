package com.ipartek.formacion.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

/**
 * Controlador que se comunica entre la vista y la capa service de usuario
 * @author Jon Ander Ochoa Ruiz
 * 27 de mar. de 2017
 */
@Controller
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	@Inject
	private UsuarioService uS;
	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
	//Que datos carga y a que vista
	ModelAndView mav = null;
	//Accede al objeto del servlet-context (name="id")
	@Resource(name = "usuarioValidator")
	private Validator validator = null;
	
	/**
	 * Metodo que alimneta al metodo init() del servlet de spring. Por lo que vamos a cargar el validador
	 * de Spring
	 * @param binder Recibe el binder al que carga el validador
	 */
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	/**
	 * Metodo que añade un nuevo usuario
	 * @param model Modelo al que se le añade el usuario y la vista
	 * @return Devuelve el String donde verlo
	 */
	@RequestMapping(value = "/addUsuario")
	public String addUsuario(Model model){
		LOGGER.info("addUsuario");
		//Añadimos un objeto en blanco pero no nulo
		model.addAttribute("usuario", new Usuario());
		return "usuarios/usuario";
	}
	
	/**
	 * Metodo que muestra todos los usuarios
	 * @return mav Un ModelAndView con la lista de usuarios y la vista
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(){
		LOGGER.info("getAll");
		//Vista a la que se dirige
		mav = new ModelAndView("usuarios/usuarios");
		//Autentificacion por roll
		//TODO
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LOGGER.info(auth.toString());
		if(auth.getName().equals("jony")){
			Usuario usuario = uS.getByUser(auth.getName());
			mav.addObject("listadoUsuarios",usuario);
		}else{
			//carga la lista
			List<Usuario> usuarios = uS.getAll();
			//Añade un objeto al mav con la lista de usuarios y la llama listadoUsuarios
			mav.addObject("listadoUsuarios", usuarios);
		}
		
		
		return mav;
	}
	
	/**
	 * Metodo encargado de mostrar el usuario del codigo entregado
	 * @param codigo Codigo del usuario que queremos ver
	 * @return mav Devuelve un ModelAndView con el usuario y la vista en la que mostrarlo
	 */
	@RequestMapping(value = "/{codigo}")
	public ModelAndView getById(@PathVariable("codigo") int codigo){
		LOGGER.info("getById");
		mav = new ModelAndView("usuarios/usuario");
		Usuario usuario = uS.getById(codigo);
		mav.addObject("usuario", usuario);
		return mav;
	}
	
	/**
	 * Metodo encargado de mostrar el usuario del user entregado
	 * @param user User del usuario que queremos ver
	 * @return mav Devuelve un ModelAndView con el usuario y la vista en la que mostrarlo
	 */
	@RequestMapping(value = "/{user}")
	public ModelAndView getByUser(@PathVariable("user") String user){
		LOGGER.info("getByUser");
		mav = new ModelAndView("usuarios/usuario");
		Usuario usuario = uS.getByUser(user);
		mav.addObject("usuario", usuario);
		return mav;
	}
	
	/**
	 * Metodo que actualiza en usuario introducido como parametro
	 * @param usuario usuario que se desea actualizar
	 * @param bindingResult Devuelve si el resultado de la validacion tiene errores o no
	 * @param model Modelo al que se le añade el usuario y la vista
	 * @return Un String con la url a la que ir
	 */
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public String saveUsuario(@ModelAttribute("usuario") @Validated Usuario usuario, BindingResult bindingResult, Model model){
		String destino = "";
		if(bindingResult.hasErrors()){ //Si hay errores en la validacion
			LOGGER.info("usuario tiene errores");
			destino = "usuarios/usuario";
		}else{
			destino = "redirect:/usuarios";
			if(usuario.getCodigo() > Usuario.CODIGO_NULO){
				LOGGER.info("update");
				uS.update(usuario);
			}else{
				uS.create(usuario);
				LOGGER.info("create");
			}
		}
		return destino;
	}
	
	/**
	 * Metodo que elimina el usuario con el codigo seleccionado
	 * @param codigo Codigo del usuario a eliminar
	 * @return redirecciona al mismo controlador para recargar el getAll
	 */
	@RequestMapping(value = "/deleteUsuario/{codigo}")
	public String delete(@PathVariable("codigo") int codigo){
		LOGGER.info("delete");
		uS.delete(codigo);
		return "redirect:/usuarios";
	}
}
