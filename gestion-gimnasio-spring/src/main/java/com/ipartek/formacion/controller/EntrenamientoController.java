package com.ipartek.formacion.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.ipartek.formacion.dbms.persistence.Entrenamiento;
import com.ipartek.formacion.service.interfaces.EntrenamientoService;

/**
 * Controlador que se comunica entre la vista y la capa service de entrenamiento
 * @author Jon Ander Ochoa Ruiz
 * 16 de abr. de 2017
 */
@Controller
@RequestMapping(value="/entrenamientos")
public class EntrenamientoController {

	@Inject
	private EntrenamientoService enS;
	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(EntrenamientoController.class);
	//Que datos carga y a que vista
	ModelAndView mav = null;
	//accede al objeto de servlet-context (name= "id")
	@Resource(name="entrenamientoValidator")
	private Validator validator = null;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		//binder.registerCustomEditor(Date.class,  new CustomDateEditor(new SimpleDateFormat("dd//MM/yyyy"), false, 10));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
		
	/**
	 * Carga la lista con todos los entrenamientos en entrenamientos.jsp
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView getAll(){
		LOGGER.info("GetAll");
		//Vista a la que dirige
		mav = new ModelAndView("entrenamientos/entrenamientos");
		//Cargamos la lista
		List<Entrenamiento> entrenamientos = enS.getAll();
		//Añade un objeto al mav con la lista de entrenamientos y la llama listadoEntrenamientos
		mav.addObject("listadoEntrenamientos", entrenamientos);
		return mav;
	}
	
	/**
	 * Carga el entrenamiento que contenga el codigo seleccionado.
	 * Muestra sus valores en entrenamiento.jsp
	 * @param codigo Codigo con el que busca el entrenamiento
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(value = "/id/{codigo}")
	private ModelAndView getById(@PathVariable("codigo") int codigo){
		LOGGER.info("getById");
		mav = new ModelAndView("entrenamientos/entrenamientoInforme");
		Entrenamiento entrenamiento = enS.getInforme(codigo);
		mav.addObject("entrenamiento", entrenamiento);
		return mav;
	}
	
	/**
	 * Carga los entrenamientos que contenga el usuario seleccionado.
	 * Muestra sus valores en entrenamientos.jsp
	 * @param codigo Codigo del usuario con el que busca los entrenamientos
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(value = "/{codigo}")
	private ModelAndView getByUsuario(@PathVariable("codigo") int codigo){
		LOGGER.info("getByUsuario");
		mav = new ModelAndView("entrenamientos/entrenamientos");
		List<Entrenamiento> entrenamientos = enS.getByUsuario(codigo);
		mav.addObject("listadoEntrenamientos", entrenamientos);
		return mav;
	}
	
	/**
	 * Crea un nuevo entrenamiento en blanco que posteriormente sera cargado con valores y guardado
	 * @return Devuelve un modelAndView con el modelo y la vista
	 */
	@RequestMapping(value = "/addEntrenamiento")
	private String addEntrenamiento(Model model){
		LOGGER.info("addEntrenamiento");
		Entrenamiento entrenamiento = new Entrenamiento();
		model.addAttribute("entrenamiento", entrenamiento);
		return "/entrenamientos/entrenamiento";
	}
	
	/**
	 * Metodo encargado de eliminar un entrenamiento
	 * @param codigo Codigo del entrenamiento que queremos eliminar
	 * @return Nos manda a la lista de entrenamientos
	 */
	@RequestMapping(value = "/deleteEntrenamiento/{codigo}")
	private String delete(@PathVariable("codigo") int codigo){
		LOGGER.info("delete");
		enS.delete(codigo);
		return "redirect:/entrenamientos";
	}
	
	/**
	 * Metodo que actualiza o crea el entrenamiento introducido como parametro
	 * @param entrenamiento entrenamiento que se desea actualizar, crear
	 * @param bindingResult Devuelve si el resultado de la validacion tiene errores o no
	 * @param model Modelo al que se le añade el entrenamiento y la vista
	 * @return Un String con la url a la que ir
	 */
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public String saveEntrenamiento(@ModelAttribute("entrenamiento") @Validated Entrenamiento entrenamiento, BindingResult bindingResult, Model model){
		String destino = "";
		if(bindingResult.hasErrors()){
			LOGGER.info("Entrenamiento tiene errores");
			destino = "entrenamientos/entrenamiento";
		}else{
			if(entrenamiento.getCodigo() > Entrenamiento.CODIGO_NULO){
				LOGGER.info("update");
				enS.update(entrenamiento);
			}else{
				enS.create(entrenamiento);
				LOGGER.info("create");
			}
			destino = "redirect:/entrenamientos";
		}
		return destino;
	}
	
	/**
	 * 
	 * @param model
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/getInforme/{codigo}")
	public String getInforme(Model model, @PathVariable("codigo") int codigo) {
		LOGGER.info("getInforme");
		Entrenamiento entrenamiento = enS.getInforme(codigo);
		model.addAttribute("entrenamiento", entrenamiento);
		return "/entrenamientos/entrenamientoInforme";

}
}
