package com.ipartek.formacion.controller.validador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Ejercicio;


public class EjercicioValidator implements Validator {

	@Value("${ejercicio.actividad.size.min}")
	private int actividadTamMin;
	@Value("${ejercicio.actividad.size.max}")
	private int actividadTamMax;
	@Value("${ejercicio.grupoMuscular.size.min}")
	private int grupoMuscularTamMin;
	@Value("${ejercicio.grupoMuscular.size.max}")
	private int grupoMuscularTamMax;
	@Value("${ejercicio.maquina.size.min}")
	private int maquinaTamMin;
	@Value("${ejercicio.maquina.size.max}")
	private int maquinaTamMax;
	@Value("${ejercicio.descripcion.size.min}")
	private int descripcionTamMin;
	@Value("${ejercicio.descripcion.size.max}")
	private int descripcionTamMax;

	@Override
	public boolean supports(Class<?> paramClass) {
		// Si paramClass es un ejercicio lo devuelve
		return Ejercicio.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// Validamos que no sea nulo, no este en blanco
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actividad", "form.actividadRequerido","tiene que introducirse una actividad");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "grupomuscular", "form.grupoMuscularRequerido","tienen que introducirse el grupo muscular");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maquina", "form.maquinaRequerido","tiene que introducirse una maquina o ejercicio");

		// Guardamos el objeto a validar como un Ejercicio
		Ejercicio ejercicio = (Ejercicio) obj;
		// Si el codigo del ejercicio(obj) es menos que nulo...
		if (ejercicio.getEjercicioCodigo() < Ejercicio.CODIGO_NULO) {
			// Se rechaza el valor (campo, errorCode, array, mensaje)
			errors.rejectValue("codigo", "form.codigoNegativo", new Object[] { "'codigo'" },
					"no puede ser menor que " + Ejercicio.CODIGO_NULO);
		}
		// Valida la longitud del nombre de ejercicio
		if (ejercicio.getActividad().length() < actividadTamMin || ejercicio.getActividad().length() > actividadTamMax) {
			errors.rejectValue("actividad", "form.longitudActividadIncorrecta", new Object[] { actividadTamMin, actividadTamMax },
					"La actividad tiene que ocupar entre " + actividadTamMin + " y " + actividadTamMax + " caracteres.");
		}
		// Valida el grupo muscular del ejercicio
		if (ejercicio.getGrupomuscular().length() < grupoMuscularTamMin || ejercicio.getGrupomuscular().length() > grupoMuscularTamMax) {
			errors.rejectValue("grupomuscular", "form.longitudGrupoMuscularIncorrecta",	new Object[] { grupoMuscularTamMin, grupoMuscularTamMax }, 
					"El grupo muscular tienen que ocupar entre "+ grupoMuscularTamMin + " y " + grupoMuscularTamMax + " caracteres.");
		}
		// Valida la maquina del ejercicio
		if (ejercicio.getMaquina().length() < maquinaTamMin || ejercicio.getMaquina().length() > maquinaTamMax) {
			errors.rejectValue("maquina", "form.longitudMaquinaIncorrecta", new Object[] { maquinaTamMin, maquinaTamMax },
					"La maquina tienen que ocupar entre " + maquinaTamMin + " y " + maquinaTamMax + " caracteres.");
		}
		// Valida la descripcion del ejercicio
		if (ejercicio.getDescripcion().length() < descripcionTamMin || ejercicio.getDescripcion().length() > descripcionTamMax) {
			errors.rejectValue("descripcion", "form.longitudDescripcionIncorrecta", new Object[] { descripcionTamMin, descripcionTamMax },
					"La descripción tienen que ocupar entre " + descripcionTamMin + " y " + descripcionTamMax + " caracteres.");
		}
	}

}
