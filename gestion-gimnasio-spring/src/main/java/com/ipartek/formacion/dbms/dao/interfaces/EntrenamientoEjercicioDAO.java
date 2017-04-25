package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;

/**
 * Esta interfaz genera los metodos necesarios de acceso a la BBDD
 * @author Jon Ander Ochoa Ruiz
 * 14 de abr. de 2017
 */
public interface EntrenamientoEjercicioDAO extends DAOSetter {

	/**
	 * Crea un nuevo entrenamientoEjercicio
	 * @param entrenamientoEjercicio El nuevo entrenamiento
	 * @return Devuelve el entrenamientoEjercicio nuevo
	 */
	public EntrenamientoEjercicio create(EntrenamientoEjercicio entrenamientoEjercicio);
	
	/**
	 * Muestra una lista de entrenamientoEjercicios
	 * @return Devuelve la lista de entrenamientoEjercicios
	 */
	public List<EntrenamientoEjercicio> getAll();
	
	/**
	 * Muestra un entrenamientoEjercicio en funcion del codigo introducido
	 * @param codigo Codigo que filtra
	 * @return Devuelve un entrenamientoEjercicio
	 */
	public EntrenamientoEjercicio getById(int codigo);
	/**
	 * Actualiza un entrenamientoEjercicio con el que se entrega
	 * @param entrenamientoEjercicio EntrenamientoEjercicio nuevo
	 * @return Devuelve un entrenamientoEjercicio 
	 */
	public EntrenamientoEjercicio update(EntrenamientoEjercicio entrenamientoEjercicio);
	/**
	 * Eliminamos un entrenamientoEjercicio
	 * @param codigo Codigo que selecciona el entrenamientoEjercicio a eliminar
	 */
	public void delete(int codigo);

	/**
	 * Muestra la lista de entrenamientoEjercicios para un determinado entrenamiento
	 */
	public List<EntrenamientoEjercicio> getByEntrenamiento(int codigo);
	/**
	 * Muestra un informe con los datos
	 */
	public EntrenamientoEjercicio getEntrenamientoEjercicioInforme(int codigo);
}
