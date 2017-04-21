package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Entrenamiento;

/**
 * Esta interfaz genera los metodos necesarios de acceso a la BBDD
 * @author Jon Ander Ochoa Ruiz
 * 26 de mar. de 2017
 */
public interface EntrenamientoDAO extends DAOSetter  {
	
	/**
	 * Crea un nuevo entrenamiento
	 * @param entrenamiento El nuevo entrenamiento
	 * @return Devuelve el entrenamiento nuevo
	 */
	public Entrenamiento create(Entrenamiento entrenamiento);
	/**
	 * Muestra una lista de entrenamientos
	 * @return Devuelve la lista de entrenamientos
	 */
	public List<Entrenamiento> getAll();
	/**
	 * Muestra un entrenamiento en funcion del codigo introducido
	 * @param codigo Codigo que filtra
	 * @return Devuelve un entrenamiento
	 */
	public Entrenamiento getById(int codigo);
	/**
	 * Devuelve una lista de entrenamientos filtrados por el usuario
	 * @param codigo Codigo del usuario por el que filtrar
	 * @return Devuelve una lista de entrenamientos
	 */
	public List<Entrenamiento> getByUsuario(int codigo);
	/**
	 * Actualiza un entrenamiento con el que se entrega
	 * @param entrenamiento Entrenamiento nuevo
	 * @return Devuelve un entrenamiento 
	 */
	public Entrenamiento update(Entrenamiento entrenamiento);
	/**
	 * Eliminamos un entrenamiento
	 * @param codigo Codigo que selecciona el entrenamiento a eliminar
	 */
	public void delete(int codigo);
	/**
	 * Genera un informe del entrenamiento
	 * @param codigoCodigo del entrenamiento
	 * @return Devuelve un informe del entrenamiento
	 */
	public Entrenamiento getInforme(int codigo);
}
