package com.ipartek.formacion.controller.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ipartek.formacion.dbms.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

public class UsuarioConverter implements Converter<String, Usuario> {
	
	@Autowired
	UsuarioService uS;
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioConverter.class);
	
	@Override
	public Usuario convert(String codigo) {
		Usuario usuario = uS.getById(Integer.parseInt(codigo));
		return usuario;
	}
}
