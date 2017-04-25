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
import com.ipartek.formacion.dbms.persistence.Usuario;

public class EntrenamientoExtractor2 implements ResultSetExtractor<Map<Integer, Entrenamiento>> {

	private final static Logger LOGGER = LoggerFactory.getLogger(EntrenamientoExtractor2.class);
	
	@Override
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
			//Mete enl usuario en el campo usuario de la instancia de la clase entrenamiento
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
		}
		return entrenamientos;
	}

}
