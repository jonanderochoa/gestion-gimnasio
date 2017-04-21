package com.ipartek.formacion.ws.soapservers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value ="/soap/version")
public class versionSoapController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView version(){
		ModelAndView mav = new ModelAndView("/sw/version");
		String wsdl=  "http://localhost:8080/gestiongimnasio/versionService?WSDL";
		mav.addObject("wsdl", wsdl);
		return mav;
	}
}
