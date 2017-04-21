package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipartek.formacion.dbms.persistence.Ejercicio;

/**
 * Mapea la tabla de BBDD de los Ejercicios
 * @author Jon Ander Ochoa Ruiz
 * 24 de mar. de 2017
 */
public class EjercicioMapper implements RowMapper<Ejercicio> {

	private final static Logger LOGGER = LoggerFactory.getLogger(EjercicioMapper.class);
	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 * Este metodo convierte cada tupla(registro) de la BBDD en un objeto
	 */
	@Override
	public Ejercicio mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ejercicio ejercicio = new Ejercicio();
		ejercicio.setEjercicioCodigo(rs.getInt("codigo"));
		ejercicio.setActividad(rs.getString("actividad"));
		ejercicio.setGrupomuscular(rs.getString("grupomuscular"));
		ejercicio.setMaquina(rs.getString("maquina"));
		ejercicio.setDescripcion(rs.getString("descripcion"));
		LOGGER.info(ejercicio.toString());
		return ejercicio;
	}
}
