package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Ejercicio;

/**
 * Esta interfaz genera los metodos necesarios de acceso a la BBDD
 * @author Jon Ander Ochoa Ruiz
 * 24 de mar. de 2017
 */
public interface EjercicioDAO extends DAOSetter {
	/**
	 * Crea un nuevo ejercicio
	 * @param ejercicio Se le entrega el ejercicio que creara
	 * @return Devuelve un ejercicio
	 */
	public Ejercicio create(Ejercicio ejercicio);
	
	/**
	 * Devuelve una lista de todos los ejercicios
	 * @return Lista de todos los ejercicios
	 */
	public List<Ejercicio> getAll();
	
	/**
	 * Devuelve un ejercicio que contenga el codigo introducido
	 * @param codigo Codigo de filtrado
	 * @return Ejercicio seleccionado
	 */
	public Ejercicio getById(int codigo);
	
	/**
	 * Muestra la lista de ejercicios que contengan el nombre de la actividad introducida
	 * @param actividad Actividad de filtrado
	 * @return Lista de ejercicios con ese filtrado
	 */
	public List<Ejercicio> getByActivity(String actividad);
	
	/**
	 * Actualiza un ejercicio con otro
	 * @param ejercicio Ejercicio con el que se actualizara
	 * @return Devuelve el ejercicio actualizado
	 */
	public Ejercicio update(Ejercicio ejercicio);
	
	/**
	 * Elimina el ejercicio que contenga el codigo introducido
	 * @param codigo Codigo de filtrado
	 */
	public void delete(int codigo);
	
}
