package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Entrenamiento;

/**
 * Interfaz que contiene las funcionalidades de Entrenamiento
 * @author Jon Ander Ochoa Ruiz
 * 26 de mar. de 2017
 */
public interface EntrenamientoService {

	/**
	 * Crea un nuevo entrenamiento con el objeto entrenamiento que le entregamos
	 * @param entrenamiento que le entregamos
	 * @return entrenamiento que devuelve
	 */
	public Entrenamiento create(Entrenamiento entrenamiento);
	
	/**
	 * Devuelve una lista con todos los entrenamientos
	 * @return List<Ejercicio> 
	 */
	public List<Entrenamiento> getAll();
	
	/**
	 * Devuelve el entrenamiento que contiene un codigo especifico
	 * @param codigo del entrenamiento que queremos ver
	 * @return entrenamiento que devuelve
	 */
	public Entrenamiento getById(int codigo);
	
	/**
	 * Devuelve la lista de entrenamientos en funcion del codigo de usuario
	 * @param codigo Codigo de usuario con el que filtrar
	 * @return Devuelve la lista de Entrenamientos
	 */
	public List<Entrenamiento> getByUsuario(int codigo);
	/**
	 * Sustituye el entrenamiento por el que le entregamos
	 * @param entrenamiento que le entregamos
	 * @return entrenamiento que devuelve
	 */
	public Entrenamiento update(Entrenamiento entrenamiento);
	
	/**
	 * Elimina el entrenamiento con ese codigo
	 * @param codigo del elemento que desea eliminar
	 */
	public void delete(int codigo);

	/**
	 * Crea un informe que muestra los valores del entrenamiento con sus ejercicios
	 * @param codigo Codigo del entrenamiento
	 * @return Devuelve el informe del entrenamiento
	 */
	public Entrenamiento getInforme(int codigo);
}
