package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;

public class EntrenamientoEjercicioExtractor implements ResultSetExtractor<Map<Integer, EntrenamientoEjercicio>>{

	@Override
	public Map<Integer, EntrenamientoEjercicio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, EntrenamientoEjercicio> entrenamientoEjercicios = new HashMap<Integer, EntrenamientoEjercicio>();
		while(rs.next()){
			int codigo = rs.getInt("codigo");
			EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
			if(entrenamientoEjercicio != null){
				//EntrenamientoEjercicio
				entrenamientoEjercicio.setCodigo(rs.getInt("codigo"));
				entrenamientoEjercicio.setSerie(rs.getInt("series"));
				entrenamientoEjercicio.setRepeticion(rs.getInt("repeticiones"));
				entrenamientoEjercicio.setPeso(rs.getDouble("peso"));
				entrenamientoEjercicio.setTiempo(rs.getInt("tiempo"));
				entrenamientoEjercicio.setActivo(rs.getBoolean("activo"));
				//Ejercicio
				entrenamientoEjercicio.setActividad(rs.getString("actividad"));
				entrenamientoEjercicio.setGrupomuscular(rs.getString("grupomuscular"));
				entrenamientoEjercicio.setMaquina(rs.getString("maquina"));
				entrenamientoEjercicio.setDescripcion(rs.getString("descripcion"));
				
			}
		}
		
		
	
		return null;
	}

}
