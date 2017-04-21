package com.ipartek.formacion.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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

import com.ipartek.formacion.dbms.persistence.Ejercicio;
import com.ipartek.formacion.service.interfaces.EjercicioService;

/**
 * Controlador que se comunica entre la vista y la capa service de ejercicios
 * @author Jon Ander Ochoa Ruiz
 * 27 de mar. de 2017
 */
@Controller
@RequestMapping(value="/ejercicios")
public class EjercicioController {

	@Inject
	private EjercicioService eS;
	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(EjercicioController.class);
	//Que datos carga y a que vista
	ModelAndView mav = null;
	//accede al objeto de servlet-context (name= "id")
	@Resource(name="ejercicioValidator")
	private Validator validator = null;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	/**
	 * Carga la lista con todos los ejercicios en ejercicios.jsp
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(){
		LOGGER.info("getAll()");
		//Vista a la que dirige
		mav = new ModelAndView("ejercicios/ejercicios");
		//Carga la lista
		List<Ejercicio> ejercicios = eS.getAll();
		//Añade un objeto al mav con la lista de ejercicios y la llama listadoEjercicios
		mav.addObject("listadoEjercicios", ejercicios);
		return mav;
	}
	
	/**
	 * Carga el ejercicio que contenga el codigo seleccionado.
	 * Muestra sus valores en ejercicio.jsp
	 * @param codigo Codigo con el que busca el ejercicio
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(value="/{codigo}")
	public ModelAndView getById(@PathVariable("codigo") int codigo){
		LOGGER.info("getById");
		Ejercicio ejercicio = eS.getById(codigo);
		mav = new ModelAndView("ejercicios/ejercicio");
		mav.addObject("ejercicio", ejercicio);
		return mav;
	}
	
	/**
	 * Filtra una lista de ejercicios usando como filtro la actividad
	 * @param actividad El filtro
	 * @return Devuelve un modelAndView con el modelo y la vista. En este caso ejercicios.jsp
	 */
	@RequestMapping(value="/actividad/{actividad}")
	public ModelAndView getByActivity(@PathVariable("actividad") String actividad){
		LOGGER.info("getByActivity");
		List<Ejercicio> ejercicios = eS.getByActivity(actividad);
		mav= new ModelAndView("ejercicios/ejercicios");
		mav.addObject("listadoEjercicios", ejercicios);
		return mav;
	}
	
	/**
	 * Guarda o actualiza un ejercicio validandolo con Spring
	 * @param ejercicio Recibe un ejercicio que creara un nuevo valor o modificara uno existente
	 * @param bindingResult Devuelve el resultado de la validacion
	 * @return Devuelve la cadena a donde redirigirse
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveEjercicio(@ModelAttribute("ejercicio") @Validated Ejercicio ejercicio, BindingResult bindingResult){
		String destino = "";
		if(bindingResult.hasErrors()){ //Si hay errores
			LOGGER.info("ejercicios tiene errores");
			destino = "ejercicios/ejercicio";
		}else{
			destino = "redirect:/ejercicios/actividad/"+ejercicio.getActividad();
			if(ejercicio.getEjercicioCodigo() > Ejercicio.CODIGO_NULO){
				LOGGER.info("update");
				eS.update(ejercicio);
			}else{
				LOGGER.info("create");
				eS.create(ejercicio);
			}
		}
		return destino;
	}
	/**
	 * Crea un nuevo ejercicio en blanco que posteriormente sera cargado con valores y guardado
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(value="/addEjercicio")
	public ModelAndView addEjercicio(){
		LOGGER.info("addEjercicio");
		Ejercicio ejercicio = new Ejercicio();
		ModelAndView mav = new ModelAndView("/ejercicios/ejercicio");
		mav.addObject("ejercicio", ejercicio);
		return mav;
	}
	
	/**
	 * Metodo encargado de eliminar un ejercicio
	 * @param codigo Codigo del ejercicio que queremos eliminar
	 * @return Nos manda a la lista de ejercicios
	 */
	@RequestMapping(value="/deleteEjercicio/{codigo}")
	public String delete(@PathVariable("codigo") int codigo){
		LOGGER.info("delete");
		eS.delete(codigo);
		return "redirect:/ejercicios";
	}
	
}
