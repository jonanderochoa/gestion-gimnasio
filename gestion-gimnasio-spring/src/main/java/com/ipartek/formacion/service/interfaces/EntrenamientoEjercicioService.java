package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;

/**
 * Interfaz que contiene las funcionalidades de EntrenamientoEjercicio
 * @author Jon Ander Ochoa Ruiz
 * 17 de abr. de 2017
 */
public interface EntrenamientoEjercicioService {

	/**
	 * Crea un nuevo entrenamientoEjercicio con el objeto entrenamientoEjercicio que le entregamos
	 * @param entrenamientoEjercicio que le entregamos
	 * @return entrenamientoEjercicio que devuelve
	 */
	public EntrenamientoEjercicio create(EntrenamientoEjercicio entrenamientoEjercicio);
	
	/**
	 * Devuelve una lista con todos los entrenamientoEjercicio
	 * @return List<Ejercicio> 
	 */
	public List<EntrenamientoEjercicio> getAll();
	
	/**
	 * Devuelve el entrenamientoEjercicio que contiene un codigo especifico
	 * @param codigo del entrenamientoEjercicio que queremos ver
	 * @return entrenamientoEjercicio que devuelve
	 */
	public EntrenamientoEjercicio getById(int codigo);
	
	/**
	 * Sustituye el entrenamientoEjercicio por el que le entregamos
	 * @param entrenamientoEjercicio que le entregamos
	 * @return entrenamientoEjercicio que devuelve
	 */
	public EntrenamientoEjercicio update(EntrenamientoEjercicio entrenamientoEjercicio);
	
	/**
	 * Elimina el entrenamientoEjercicio con ese codigo
	 * @param codigo del elemento que desea eliminar
	 */
	public void delete(int codigo);

	/**
	 * Muestra la lista de entrenamientoEjercicios para un determinado entrenamiento
	 */
	public List<EntrenamientoEjercicio> getByEntrenamiento(int codigo);
}
