package com.ipartek.formacion.api.restfulclients;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.service.Util;

/**
 * Controlador que se encarga del mapeu de urls del servicio web
 * @author Jon Ander Ochoa Ruiz
 * 27 de mar. de 2017
 */
@Controller
@RequestMapping(value="/webservices/weather")
public class WeatherRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherRestController.class);
	
	
	/**
	 * El tiempo
	 * @return
	 * @throws IOExceptiozn 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String paginaDelTiempo(){
		LOGGER.info("weather");
		return "/sw/weather";
	}
}
