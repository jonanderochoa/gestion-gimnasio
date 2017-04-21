package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Usuario;

/**
 * Interfaz que contiene las funcionalidades de Usuario
 * @author Jon Ander Ochoa Ruiz
 * 26 de mar. de 2017
 */
public interface UsuarioService {

	/**
	 * Crea un nuevo usuario con el objeto usuario que le entregamos
	 * @param usuario que le entregamos
	 * @return usuario que devuelve
	 */
	public Usuario create(Usuario usuario);
	
	/**
	 * Devuelve una lista con todos los usuario
	 * @return List<Usuario> 
	 */
	public List<Usuario> getAll();
	
	/**
	 * Devuelve el usuario que contiene un codigo especifico
	 * @param codigo del usuario que queremos ver
	 * @return usuario que devuelve
	 */
	public Usuario getById(int codigo);
	
	/**
	 * Devuelve el usuario que contiene un user especifico
	 * @param user del usuario que queremos ver
	 * @return usuario que devuelve
	 */
	public Usuario getByUser(String user);
	/**
	 * Sustituye el Usuario por el que le entregamos
	 * @param usuario que le entregamos
	 * @return usuario que devuelve
	 */
	public Usuario update(Usuario usuario);
	
	/**
	 * Elimina el usuario con ese codigo
	 * @param codigo del elemento que desea eliminar
	 */
	public void delete(int codigo);
}
