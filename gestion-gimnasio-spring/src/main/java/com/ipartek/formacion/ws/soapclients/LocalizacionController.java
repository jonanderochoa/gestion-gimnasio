package com.ipartek.formacion.ws.soapclients;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/soap/localizacion")
public class LocalizacionController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView verPais(){
		ModelAndView mav = new ModelAndView("/sw/localizacion");
		WebClientSoapIp wc = new WebClientSoapIp();
		mav.addObject("nombre", wc.getCountryName());
		mav.addObject("pais", wc.getCountryCode());
		mav.addObject("detalles", wc.getCodeDetails());
		mav.addObject("ip", wc.getIp());
		return mav;
	}
}
