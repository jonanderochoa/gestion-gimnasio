package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;
import com.ipartek.formacion.service.interfaces.EntrenamientoEjercicioService;

/**
 * Controlador que se comunica entre la vista y la capa service de entrenamientoEjercicio
 * @author Jon Ander Ochoa Ruiz
 * 16 de abr. de 2017
 */
@Controller
@RequestMapping(value="/entrenamientoEjercicios")
public class EntrenamientoEjercicioController {

	@Inject
	private EntrenamientoEjercicioService eeS;
	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(EntrenamientoEjercicioController.class);
	//Que datos carga y a que vista
	ModelAndView mav = null;
	
	/**
	 * Carga la lista con todos los entrenamientoEjercicio en entrenamientoEjercicios.jsp
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView getAll(){
		LOGGER.info("GetAll");
		//Vista a la que dirige
		mav = new ModelAndView("entrenamientoEjercicios/entrenamientoEjercicios");
		//Cargamos la lista
		List<EntrenamientoEjercicio> entrenamientoEjercicios = eeS.getAll();
		//Añade un objeto al mav con la lista de entrenamientos y la llama listadoEntrenamientos
		mav.addObject("listadoEntrenamientoEjercicios", entrenamientoEjercicios);
		return mav;
	}
	
	/**
	 * Carga los entrenamientoEjercicios que contenga el codigo de entrenamiento seleccionado.
	 * @param codigo Codigo del entrenamiento con el que busca el entrenamientoEjercicio
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(value = "/{codigo}")
	private ModelAndView getByEntrenamiento(@PathVariable("codigo") int codigo){
		LOGGER.info("getByEntrenamiento");
		mav = new ModelAndView("entrenamientoEjercicios/entrenamientoEjercicios");
		//Cargamos la lista
		List<EntrenamientoEjercicio> entrenamientoEjercicios = eeS.getByEntrenamiento(codigo);
		
		mav.addObject("listadoEntrenamientoEjercicios", entrenamientoEjercicios);
		return mav;
	}
	
	/**
	 * Carga el entrenamientoEjercicio que contenga el codigo seleccionado.
	 * Muestra sus valores en entrenamientoEjercicio.jsp
	 * @param codigo Codigo con el que busca el entrenamientoEjercicio
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(value = "/id/{codigo}")
	private ModelAndView getById(@PathVariable("codigo") int codigo){
		LOGGER.info("getById");
		mav = new ModelAndView("entrenamientoEjercicios/entrenamientoEjercicio");
		EntrenamientoEjercicio entrenamientoEjercicio = eeS.getById(codigo);
		mav.addObject("entrenamiento", entrenamientoEjercicio);
		return mav;
	}
	
	@RequestMapping(value = "/addEntrenamientoEjercicio/{codigo}")
	private String addEntrenamientoByUsuario(@PathVariable("codigo") int codigo, Model model){
		LOGGER.info("addEntrenamientoEjercicio");
		model.addAttribute("entrenamientoEjercicio", new EntrenamientoEjercicio());
		return "entrenamientoEjercicios/entrenamientoEjercicio";
	}
	
	/**
	 * Crea un nuevo entrenamientoEjercicio en blanco que posteriormente sera cargado con valores y guardado
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(value = "/addEntrenamientoEjercicio")
	private ModelAndView addEntrenamientoEjercicio(){
		LOGGER.info("addEntrenamientoEjercicio");
		EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
		ModelAndView mav = new ModelAndView("/entrenamientoEjercicios/entrenamientoEjercicio");
		mav.addObject("entrenamientoEjercicio", entrenamientoEjercicio);
		return mav;
	}
	
	/**
	 * Metodo que actualiza o crea el entrenamientoEjercicio introducido como parametro
	 * @param entrenamientoEjercicio entrenamientoEjercicio que se desea actualizar, crear
	 * @param bindingResult Devuelve si el resultado de la validacion tiene errores o no
	 * @param model Modelo al que se le añade el entrenamientoEjercicio y la vista
	 * @return Un String con la url a la que ir
	 */
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public String saveEntrenamientoEjercicio(@ModelAttribute("entrenamientoEjercicio") EntrenamientoEjercicio entrenamientoEjercicio, Model model){
		String destino = "";
		if(entrenamientoEjercicio.getCodigo() > EntrenamientoEjercicio.CODIGO_NULO){
			LOGGER.info("update");
			eeS.update(entrenamientoEjercicio);
		}else{
			eeS.create(entrenamientoEjercicio);
			LOGGER.info("create");
		}
		destino = "redirect:/entrenamientoEjercicios";
		return destino;
	}
	
	/**
	 * Metodo encargado de eliminar un entrenamientoEjercicio
	 * @param codigo Codigo del entrenamientoEjercicio que queremos eliminar
	 * @return Nos manda a la lista de entrenamientoEjercicios
	 */
	@RequestMapping(value = "/deleteEntrenamientoEjercicio/{codigo}")
	private String delete(@PathVariable("codigo") int codigo){
		LOGGER.info("delete");
		eeS.delete(codigo);
		return "redirect:/entrenamientoEjercicios";
	}
	
	
}
