package com.ipartek.formacion.controller.validador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Entrenamiento;
import com.ipartek.formacion.service.interfaces.UsuarioService;

/**
 * Implementamos la validacion de un Entrenamiento
 * @author Jon Ander Ochoa Ruiz
 * 16 de abr. de 2017
 */
public class EntrenamientoValidator implements Validator {

	@Autowired
	private UsuarioService uS;
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return Entrenamiento.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//Validamos que no este en blanco ni sea nulo
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario", "form.usuarioRequerido", "tiene que introducirse un usuario");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "form.fechaRequerido", "tiene que introducirse una fecha");
		
		Entrenamiento entrenamiento = (Entrenamiento) obj;
		//Validamos que el usuario exista
		if(uS.getById(entrenamiento.getUsuario().getCodigo()) == null){
			errors.rejectValue("usuario", "form.usuarioInvalido", new Object[] { "'id'" },
					"Introduzca un usuario valido.");
		}
		
	}

}
