package com.ipartek.formacion.controller.validador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Usuario;
import com.ipartek.formacion.service.Util;
import com.ipartek.formacion.service.interfaces.UsuarioService;

/**
 * Implementa la validacion de un usuario
 * 
 * @author Jon Ander Ochoa Ruiz 15 de abr. de 2017
 */
public class UsuarioValidator implements Validator {

	@Value("${usuario.nombre.size.min}")
	private int nombreTamMin;
	@Value("${usuario.nombre.size.max}")
	private int nombreTamMax;
	@Value("${usuario.apellidos.size.min}")
	private int apellidosTamMin;
	@Value("${usuario.apellidos.size.max}")
	private int apellidosTamMax;
	@Value("${usuario.user.size.min}")
	private int userTamMin;
	@Value("${usuario.user.size.max}")
	private int userTamMax;
	@Value("${usuario.pass.size.min}")
	private int passTamMin;
	@Value("${usuario.pass.size.max}")
	private int passTamMax;
	
	@Autowired
	private UsuarioService uS;
	
	@Override
	public boolean supports(Class<?> paramClass) {
		// Si paramClass es un usuario lo devuelve
		return Usuario.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// Validamos que no sea nulo, no este en blanco
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "form.nombreRequerido",
				"tiene que introducirse un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "form.apellidosRequerido",
				"tienen que introducirse los apellidos");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "form.passRequerido",
				"tiene que introducirse un usuario");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "form.passRequerido",
				"tiene que introducirse un password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.emailRequerido",
				"tiene que introducirse un email");

		// Guardamos el objeto a validar como un usuario
		Usuario usuario = (Usuario) obj;
		// Si el codigo del usuario(obj) es menos que nulo...
		if (usuario.getCodigo() < Usuario.CODIGO_NULO) {
			// Se rechaza el valor (campo, errorCode, array, mensaje)
			errors.rejectValue("codigo", "form.codigoNegativo", new Object[] { "'codigo'" },
					"no puede ser menor que " + Usuario.CODIGO_NULO);
		}
		// Valida la longitud del nombre de usuario
		if (usuario.getNombre().length() < nombreTamMin || usuario.getNombre().length() > nombreTamMax) {
			errors.rejectValue("nombre", "form.longitudNombreIncorrecta", new Object[] { nombreTamMin, nombreTamMax },
					"El nombre tiene que ocupar entre " + nombreTamMin + " y " + nombreTamMax + " caracteres.");
		}
		// Valida la longitud del apellido del usuario
		if (usuario.getApellidos().length() < apellidosTamMin || usuario.getApellidos().length() > apellidosTamMax) {
			errors.rejectValue("apellidos", "form.longitudApellidosIncorrecta", new Object[] { apellidosTamMin, apellidosTamMax },
					"El apellido tienen que ocupar entre "+ apellidosTamMin +" y "+ apellidosTamMax +" caracteres.");
		}
		// Valida la longitud del user del usuario
		if (usuario.getUser().length() < userTamMin || usuario.getUser().length() > userTamMax) {
			errors.rejectValue("user", "form.longitudUsuarioIncorrecta", new Object[] { userTamMin, userTamMax },
					"El usuario tienen que ocupar entre "+ userTamMin +" y "+ userTamMax +" caracteres.");
		}
		// Valida la longitud de la contraseña del usuario
		if (usuario.getPass().length() < passTamMin || usuario.getPass().length() > passTamMax) {
			errors.rejectValue("pass", "form.longitudPasswordIncorrecta", new Object[] { passTamMin, passTamMax },
					"La contraseña tienen que ocupar entre "+ passTamMin +" y "+ passTamMax +" caracteres.");
		}
		// Valida que el email sea valido
		if (!Util.validarEmail(usuario.getEmail())) {
			errors.rejectValue("email", "form.formatoEmailIncorrecta", new Object[] { "'email'" },
					"El email introducido no es correcto.");
		}
		
		//Valida que el user sea valido
		if(uS.getByUser(usuario.getUser()) != null){
			errors.rejectValue("user", "form.userExistente", new Object[] { "'user'" },
					"Ese usuario ya existe.");
		}
	}
}
