package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;

public class EntrenamientoEjercicioMapper implements RowMapper<EntrenamientoEjercicio>{

	private static final Logger LOGGER = LoggerFactory.getLogger(EntrenamientoEjercicioMapper.class);
	@Override
	public EntrenamientoEjercicio mapRow(ResultSet rs, int rownum) throws SQLException {
		EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
		//Ejercicio
		entrenamientoEjercicio.setActividad(rs.getString("actividad"));
		entrenamientoEjercicio.setGrupomuscular(rs.getString("grupomuscular"));
		entrenamientoEjercicio.setMaquina(rs.getString("maquina"));
		entrenamientoEjercicio.setDescripcion(rs.getString("descripcion"));
		//EntrenamientoEjercicio
		entrenamientoEjercicio.setCodigo(rs.getInt("codigo"));
		entrenamientoEjercicio.setSerie(rs.getInt("series"));
		entrenamientoEjercicio.setRepeticion(rs.getInt("repeticiones"));
		entrenamientoEjercicio.setPeso(rs.getDouble("peso"));
		entrenamientoEjercicio.setTiempo(rs.getInt("tiempo"));
		LOGGER.info(entrenamientoEjercicio.toString());
		return entrenamientoEjercicio;
	}

	

}
