package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Ejercicio;

/**
 * Interfaz que contiene las funcionalidades de Ejercicio
 * @author Jon Ander Ochoa Ruiz
 * 25 de mar. de 2017
 */
public interface EjercicioService {
	
	/**
	 * Crea un nuevo ejercicio con el objeto ejercicio que le entregamos
	 * @param ejercicio que le entregamos
	 * @return Ejercicio que devuelve
	 */
	public Ejercicio create(Ejercicio ejercicio);
	
	/**
	 * Devuelve una lista con todos los ejercicios
	 * @return List<Ejercicio> 
	 */
	public List<Ejercicio> getAll();
	
	/**
	 * Devuelve el ejercicio que contiene un codigo especifico
	 * @param codigo del ejercicio que queremos ver
	 * @return Ejercicio que devuelve
	 */
	public Ejercicio getById(int codigo);
	
	/**
	 * Devuelve la lista de ejercicios en funcion de la actividad seleccionada
	 * @param actividad Actividad por la que queremos filtrar los ejercicios
	 * @return Devuelve una lista de ejercicios
	 */
	public List<Ejercicio> getByActivity(String actividad);
	
	/**
	 * Sustituye el ejercicio por el que le entregamos
	 * @param ejercicio que le entregamos
	 * @return Ejercicio que devuelve
	 */
	public Ejercicio update(Ejercicio ejercicio);
	
	/**
	 * Elimina el ejercicio con ese codigo
	 * @param codigo del elemento que desea eliminar
	 */
	public void delete(int codigo);
}
