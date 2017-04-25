package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Entrenamiento;
import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;
import com.ipartek.formacion.dbms.persistence.Usuario;

public class EntrenamientoEjercicioExtractor implements ResultSetExtractor<Map<Integer, EntrenamientoEjercicio>>{

	private final static Logger LOGGER = LoggerFactory.getLogger(EntrenamientoExtractor.class);
	
	@Override
	public Map<Integer, EntrenamientoEjercicio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, EntrenamientoEjercicio> entrenamientoEjercicios = new HashMap<Integer, EntrenamientoEjercicio>();
		while(rs.next()){
			//Recogemos el codigo del entrenamientoEjercicio
			int codigo = rs.getInt("codigo");
			//Recogemos el entrenamientoEjercicio
			EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
			if(entrenamientoEjercicio != null){
				//Entrenamiento
				entrenamientoEjercicio.setCodigo(rs.getInt("codigo"));
				entrenamientoEjercicio.setSeries(rs.getInt("series"));
				entrenamientoEjercicio.setRepeticiones(rs.getInt("repeticiones"));
				entrenamientoEjercicio.setPeso(rs.getDouble("peso"));
				entrenamientoEjercicio.setTiempo(rs.getInt("tiempo"));
				entrenamientoEjercicio.setActivo(rs.getBoolean("eactivo"));
				//Ejercicio
				entrenamientoEjercicio.setEjercicioCodigo(rs.getInt("cod_ejercicio"));
				entrenamientoEjercicio.setActividad(rs.getString("actividad"));
				entrenamientoEjercicio.setGrupomuscular(rs.getString("grupomuscular"));
				entrenamientoEjercicio.setMaquina(rs.getString("maquina"));
				entrenamientoEjercicio.setDescripcion(rs.getString("descripcion"));
				entrenamientoEjercicio.setEjercicioActivo(rs.getBoolean("ejactivo"));
				entrenamientoEjercicios.put(entrenamientoEjercicio.getCodigo(), entrenamientoEjercicio);
				LOGGER.info(entrenamientoEjercicio.toString());
			}
			Integer eEntrenamiento = rs.getInt("cod_entrenamiento");
			if(eEntrenamiento != null && eEntrenamiento > 0){//Si hay ejerciciios..
				Entrenamiento entrenamiento = new Entrenamiento();
				//Mete los valores en los campos de la instancia de entrenamiento
				entrenamiento = new Entrenamiento();
				entrenamiento.setCodigo(rs.getInt("cod_entrenamiento"));
				entrenamiento.setFecha(rs.getDate("fecha"));
				entrenamiento.setActivo(rs.getBoolean("enactivo"));
				//Mete el usuario en el entrenamiento
				Integer uUsuario = rs.getInt("usuario_codigo");
				if(uUsuario != null && uUsuario > 0){
					Usuario usuario = new Usuario();
					usuario.setCodigo(rs.getInt("cod_usuario"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellidos(rs.getString("apellidos"));
					usuario.setUser(rs.getString("user"));
					usuario.setPass(rs.getString("pass"));
					usuario.setEmail(rs.getString("email"));
					usuario.setActivo(rs.getBoolean("uactivo"));
					entrenamiento.setUsuario(usuario);
				}
				//Guardamos el entrenamiento en entrenamientoEjercicio
				entrenamientoEjercicio.setEntrenamiento(entrenamiento);
				LOGGER.info(entrenamiento.toString());
			}
		}
		return entrenamientoEjercicios;
	}

}
