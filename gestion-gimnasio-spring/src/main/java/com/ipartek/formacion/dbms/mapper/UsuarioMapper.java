/**
 * 
 */
package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Usuario;

/**
 * Contiene los archivos de mapeado de los Usuarios
 * @author Jon Ander Ochoa Ruiz
 * 24 de mar. de 2017
 */
public class UsuarioMapper implements RowMapper<Usuario> {

	private final static Logger LOGGER = LoggerFactory.getLogger(UsuarioMapper.class);
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 * Este metodo convierte cada tupla(registro) de la BBDD en un objeto
	 */
	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setCodigo(rs.getInt("codigo"));
		usuario.setNombre(rs.getString("nombre"));
		usuario.setApellidos(rs.getString("apellidos"));
		usuario.setUser(rs.getString("user"));
		usuario.setPass(rs.getString("pass"));
		usuario.setEmail(rs.getString("email"));
		LOGGER.trace(usuario.toString());
		return usuario;
	}

}
