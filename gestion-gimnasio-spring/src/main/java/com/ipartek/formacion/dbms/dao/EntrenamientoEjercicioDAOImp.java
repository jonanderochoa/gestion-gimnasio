package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.EntrenamientoEjercicioDAO;
import com.ipartek.formacion.dbms.mapper.EntrenamientoEjercicioExtractor;
import com.ipartek.formacion.dbms.mapper.EntrenamientoEjercicioMapper;
import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;

/**
 * Clase encargada de implementar los metodos de la interfaz
 * EntrenamientoEjercicioDAO
 * 
 * @author Jon Ander Ochoa Ruiz 16 de abr. de 2017
 */
@Repository("entrenamientoEjercicioDAOImp")
public class EntrenamientoEjercicioDAOImp implements EntrenamientoEjercicioDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	private static final Logger LOGGER = LoggerFactory.getLogger(EntrenamientoEjercicioDAOImp.class);

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public EntrenamientoEjercicio create(EntrenamientoEjercicio entrenamientoEjercicio) {
		LOGGER.info("create");
		final String SQL = "entrenamientoEjercicioCreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		jdbcCall.withProcedureName(SQL);//Se le asigna el nombre del procedimiento almacenado 
		/*
		 * Le pasamos los valores de java a SQL en un mapa escepto codigo que lo recogemos
		 * una vez creado el usuario en el procedimiento almacenado (ya que lo autogenera) 
		 */
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pseries", entrenamientoEjercicio.getSeries())
				.addValue("prepeticiones", entrenamientoEjercicio.getRepeticiones())
				.addValue("ppeso", entrenamientoEjercicio.getPeso())
				.addValue("ptiempo", entrenamientoEjercicio.getTiempo())
		
				.addValue("pactividad", entrenamientoEjercicio.getActividad())
				.addValue("pgrupo", entrenamientoEjercicio.getGrupomuscular())
				.addValue("pmaquina", entrenamientoEjercicio.getMaquina())
				.addValue("pdescripcion", entrenamientoEjercicio.getDescripcion());
		LOGGER.info(entrenamientoEjercicio.toString());
		//jdbcCall.execute(in); Ejecuta la consulta dandole los parametros del mapa in y devuelve el out
		//Recogemos los datos que devuelve la consulta con otro mapa llamado out
		Map<String, Object> out = jdbcCall.execute(in);
		//Recogemos el codigo del procedimiento almacenado y lo pasamos a java
		entrenamientoEjercicio.setCodigo((Integer)out.get("pcodigo"));
		return entrenamientoEjercicio;
	}

	@Override
	public List<EntrenamientoEjercicio> getAll() {
		LOGGER.info("getAll");
		final String SQL = "call entrenamientoEjerciciogetAll();";
		List<EntrenamientoEjercicio> entrenamientoEjercicios = null;
		try{
			entrenamientoEjercicios = jdbcTemplate.query(SQL, new EntrenamientoEjercicioMapper());
			LOGGER.info(entrenamientoEjercicios.toString());
		}catch(EmptyResultDataAccessException e){
			entrenamientoEjercicios = new ArrayList<EntrenamientoEjercicio>();
			LOGGER.error(e.getMessage());
		}
		return entrenamientoEjercicios;
	}

	@Override
	public EntrenamientoEjercicio getById(int codigo) {
		LOGGER.info("getById");
		final String SQL = "call entrenamientoEjerciciogetById(?);";
		EntrenamientoEjercicio entrenamientoEjercicio = null;
		try{
			entrenamientoEjercicio = jdbcTemplate.queryForObject(SQL, new EntrenamientoEjercicioMapper(), new Object[] { codigo });
			LOGGER.info(entrenamientoEjercicio.toString());
		}catch(EmptyResultDataAccessException e){
			entrenamientoEjercicio = null;
			LOGGER.info("No hay ningun valor con ese codigo "+ e.getMessage());
		}
		return entrenamientoEjercicio;
	}

	@Override
	public EntrenamientoEjercicio update(EntrenamientoEjercicio entrenamientoEjercicio) {
		LOGGER.info("update");
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "entrenamientoEjercicioUpdate";
		jdbcCall.withProcedureName(SQL);
		//Le pasamos los valores de java a SQL mediante un mapa
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pseries", entrenamientoEjercicio.getSeries())
				.addValue("prepeticiones", entrenamientoEjercicio.getRepeticiones())
				.addValue("ppeso", entrenamientoEjercicio.getPeso())
				.addValue("ptiempo", entrenamientoEjercicio.getTiempo())
				
				.addValue("pactividad", entrenamientoEjercicio.getActividad())
				.addValue("pgrupo", entrenamientoEjercicio.getGrupomuscular())
				.addValue("pmaquina", entrenamientoEjercicio.getMaquina())
				.addValue("pdescripcion", entrenamientoEjercicio.getDescripcion());
		LOGGER.info(entrenamientoEjercicio.toString());
		jdbcCall.execute(in);
		return entrenamientoEjercicio;
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("delete");
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "entrenamientoEjercicioDelete";
		// Le pasamos la cadena con el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		// Creamos un mapa con los parametros que le pasamos al procedimiento
		// almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		// Ejecuta el procedimiento almacenado
		jdbcCall.execute(in);
	}

	@Override
	public List<EntrenamientoEjercicio> getByEntrenamiento(int codigo) {
		LOGGER.info("getByEntrenamiento");
		final String SQL = "call entrenamientoEjerciciogetByEntrenamiento(?);";
		List<EntrenamientoEjercicio> entrenamientoEjercicios = null;
		try{
			entrenamientoEjercicios = jdbcTemplate.query(SQL, new EntrenamientoEjercicioMapper(), new Object[] { codigo });
			LOGGER.info(entrenamientoEjercicios.toString());
		}catch(EmptyResultDataAccessException e){
			entrenamientoEjercicios = new ArrayList<EntrenamientoEjercicio>();
			LOGGER.info("No hay ningun valor con ese codigo "+ e.getMessage());
		}
		return entrenamientoEjercicios;
	}

	@Override
	public EntrenamientoEjercicio getEntrenamientoEjercicioInforme(int codigo) {
		LOGGER.info("getEntrenamientoEjercicioInforme");
		final String SQL = "call entrenamientoEjercicioInforme(?);";
		EntrenamientoEjercicio entrenamientoEjercicio = null;
		try{
			Map<Integer, EntrenamientoEjercicio> entrenamientoEjercicios = jdbcTemplate.query(SQL, new EntrenamientoEjercicioExtractor(), new Object[] { codigo });
			entrenamientoEjercicio = entrenamientoEjercicios.get(codigo);
			LOGGER.info(entrenamientoEjercicios.toString());
		}catch(EmptyResultDataAccessException e){
			entrenamientoEjercicio = null;
			LOGGER.info("No hay ningun valor con ese codigo "+ e.getMessage());
		}
		return entrenamientoEjercicio;
	}

}
