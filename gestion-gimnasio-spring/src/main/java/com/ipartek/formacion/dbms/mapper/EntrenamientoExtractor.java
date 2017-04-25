package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Ejercicio;
import com.ipartek.formacion.dbms.persistence.Entrenamiento;
import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;
import com.ipartek.formacion.dbms.persistence.Usuario;

/**
 * Mapea la tabla de BBDD Entrenamientos
 * @author Jon Ander Ochoa Ruiz
 * 16 de abr. de 2017
 */
public class EntrenamientoExtractor implements ResultSetExtractor<Map<Integer, Entrenamiento>>{

	private final static Logger LOGGER = LoggerFactory.getLogger(EntrenamientoExtractor.class);
	
;	@Override
	public Map<Integer, Entrenamiento> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, Entrenamiento> entrenamientos = new HashMap<Integer, Entrenamiento>();
		while(rs.next()){
			//recogemos el codigo del entrenamiento
			int codigo = rs.getInt("cod_entrenamiento");
			//Recogemos el entrenamiento del mapa con ese codigo
			Entrenamiento entrenamiento = entrenamientos.get(codigo);
			if(entrenamiento == null){ //Si el entrenamiento es nulo...
				//Mete los valores en los campos de la instancia de entrenamiento
				entrenamiento = new Entrenamiento();
				entrenamiento.setCodigo(rs.getInt("cod_entrenamiento"));
				entrenamiento.setFecha(rs.getDate("fecha"));
				entrenamiento.setActivo(rs.getBoolean("enactivo"));
				//Guardamos el entrenamiento en el mapa junto con su llave (codigo)
				entrenamientos.put(entrenamiento.getCodigo(), entrenamiento);
				LOGGER.info(entrenamiento.toString());
			}
			//Mete el usuario en el campo usuario de la instancia de la clase entrenamiento
			Integer uUsuario = rs.getInt("usuario_codigo");
			if(uUsuario != null && uUsuario > 0){
				Usuario usuario = new Usuario();
				usuario.setCodigo(rs.getInt("usuario_codigo"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setUser(rs.getString("user"));
				usuario.setPass(rs.getString("pass"));
				usuario.setEmail(rs.getString("email"));
				usuario.setActivo(rs.getBoolean("uactivo"));
				entrenamiento.setUsuario(usuario);
			}
			
			//Mete  la lista de entrenamientos ejercicio en la lista de la instancia de la clase entrenamiento
			Integer eEntrenamientoEjercicio = rs.getInt("cod_entrenamientoEjercicio");
			//LOGGER.info("nº ejercicios " +entrenamiento.getListaEntrenamientoEjercicio().size());
			if(eEntrenamientoEjercicio != null && eEntrenamientoEjercicio > 0){//Si hay ejerciciios..
				EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
				//Entrenamiento
				entrenamientoEjercicio.setCodigo(rs.getInt("cod_entrenamientoEjercicio"));
				entrenamientoEjercicio.setSeries(rs.getInt("series"));
				entrenamientoEjercicio.setRepeticiones(rs.getInt("repeticiones"));
				entrenamientoEjercicio.setPeso(rs.getDouble("peso"));
				entrenamientoEjercicio.setTiempo(rs.getInt("tiempo"));
				entrenamientoEjercicio.setActivo(rs.getBoolean("eeactivo"));
				//Ejercicio
				entrenamientoEjercicio.setEjercicioCodigo(rs.getInt("cod_ejercicio"));
				entrenamientoEjercicio.setActividad(rs.getString("actividad"));
				entrenamientoEjercicio.setGrupomuscular(rs.getString("grupomuscular"));
				entrenamientoEjercicio.setMaquina(rs.getString("maquina"));
				entrenamientoEjercicio.setDescripcion(rs.getString("descripcion"));
				entrenamientoEjercicio.setEjercicioActivo(rs.getBoolean("ejactivo"));
				entrenamiento.getListaEntrenamientoEjercicio().add(entrenamientoEjercicio);
				LOGGER.info(entrenamientoEjercicio.toString());
			}
			
			LOGGER.info("nº entrenamiento 2: "+ entrenamiento.getListaEntrenamientoEjercicio().size());
			
		}
		return entrenamientos;
	}
}
