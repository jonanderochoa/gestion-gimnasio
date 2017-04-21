package com.ipartek.formacion.controller.validador;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;

public class EntrenamientoEjercicioValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return EntrenamientoEjercicio.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "entrenamiento", "form.entrenamientoRequerido", "tiene que introducir un entrenamiento");
		
	}

}
