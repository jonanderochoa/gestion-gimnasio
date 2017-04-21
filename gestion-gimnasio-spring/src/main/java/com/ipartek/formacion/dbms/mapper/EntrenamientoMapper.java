/**
 * 
 */
package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Entrenamiento;

/**
 * Contiene los archivos de mapeado de los Entrenamientos
 * @author Jon Ander Ochoa Ruiz
 * 24 de mar. de 2017
 */
public class EntrenamientoMapper implements RowMapper<Entrenamiento> {

	private final static Logger LOGGER = LoggerFactory.getLogger(Entrenamiento.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 * Este metodo convierte cada tupla(registro) de la BBDD en un objeto
	 */
	@Override
	public Entrenamiento mapRow(ResultSet rs, int rowNum) throws SQLException {
		Entrenamiento entrenamiento = new Entrenamiento();
		entrenamiento.setCodigo(rs.getInt("codigo"));
		entrenamiento.setFecha(rs.getDate("fecha"));
		//@Todo usuario
		LOGGER.info(entrenamiento.toString());
		return entrenamiento;
	}

}
