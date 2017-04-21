package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Usuario;

/**
 * Esta interfaz genera los metodos necesarios de acceso a la BBDD
 * @author Jon Ander Ochoa Ruiz
 * 26 de mar. de 2017
 */
public interface UsuarioDAO extends DAOSetter  {
	/**
	 * Crea un nuevo usuario pasandoselo como parametro
	 * @param usuario usuario que crear
	 * @return usuario nuevo
	 */
	public Usuario create(Usuario usuario);
	
	/**
	 * Muestra todos los usuarios
	 * @return List<Usuario> Devuelve la lista de usuarios
	 */
	public List<Usuario> getAll();
	
	/**
	 * Muestra el usuario con el codigo entregado como parametro
	 * @param codigo Codigo del usuario que queremos ver
	 * @return Usuario con ese codigo
	 */
	public Usuario getById(int codigo);
	
	/**
	 * Busca si hay un usuario con un determinado user
	 * @param user User del usuario que queremos ver
	 * @return Usuario con ese user
	 */
	public Usuario getByUser(String user);
	/**
	 * Actualizamos un usuario
	 * @param usuario Usuario que queremos modificar
	 * @return Usuario modificado
	 */
	public Usuario update(Usuario usuario);
	
	/**
	 * Elimina el usuario seleccionado de forma logica
	 * @param codigo Codigo del usuario que queremos eliminar
	 */
	public void delete(int codigo);
	/**
	 * Elimina el usuario seleccionado de forma logica
	 * @param codigo Codigo del usuario que queremos eliminar
	 */
	public void deleteReal(int codigo);

	
}
