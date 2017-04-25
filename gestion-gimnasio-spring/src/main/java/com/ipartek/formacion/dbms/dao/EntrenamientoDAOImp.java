package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.EntrenamientoDAO;
import com.ipartek.formacion.dbms.mapper.EntrenamientoExtractor;
import com.ipartek.formacion.dbms.mapper.EntrenamientoExtractor2;
import com.ipartek.formacion.dbms.mapper.EntrenamientoMapper;
import com.ipartek.formacion.dbms.persistence.Entrenamiento;

/**
 * Clase encargada de implementar los metodos de CRUD de la interfaz EntrenamientoDAO
 * @author Jon Ander Ochoa Ruiz
 * 26 de mar. de 2017
 */
@Repository("entrenamientoDAOImp")
public class EntrenamientoDAOImp implements EntrenamientoDAO {

	//Para la conexion con la BBDD
	private DataSource dataSource;
	//para lectura (getAll y getById)
	private JdbcTemplate jdbcTemplate;
	//para procedimientos almacenados
	private SimpleJdbcCall jdbcCall;
	//LOGGER
	private final static Logger LOGGER = LoggerFactory.getLogger(EntrenamientoDAOImp.class);
	
	/**
	 * Al inyectar el codigo del setDataSource() va al root-context donde genera un objeto
	 * (bean) de la conexion dataSource y lo guarda en la variable que hemos creado mas arriba
	 * @param dataSource
	 */
	@Inject
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	
	}

	@Override
	public List<Entrenamiento> getAll() {
		LOGGER.info("getAll");
		final String SQL = "call entrenamientogetAll();";
		List<Entrenamiento> entrenamientos = null;
		try{
			entrenamientos = jdbcTemplate.query(SQL, new EntrenamientoMapper());
			LOGGER.info(entrenamientos.toString());
		}catch(EmptyResultDataAccessException e){
			entrenamientos = new ArrayList<Entrenamiento>();
			LOGGER.info(e.getMessage());
		}
		return entrenamientos;
	}

	@Override
	public Entrenamiento getById(int codigo) {
		LOGGER.info("getById");
		final String SQL = "call entrenamientogetById(?);";
		Entrenamiento entrenamiento = null;
		try{
			entrenamiento = jdbcTemplate.queryForObject(SQL, new EntrenamientoMapper(), new Object[] { codigo });
			LOGGER.info(entrenamiento.toString());
		}catch(EmptyResultDataAccessException e){
			entrenamiento = null;
			LOGGER.info("No hay ningun valor con ese codigo "+ e.getMessage());
		}
		return entrenamiento;
	}
	
	@Override
	public List<Entrenamiento> getByUsuario(int codigo) {
		LOGGER.info("getByUsuario");
		final String SQL = "call entrenamientoUsuario(?);";
		List<Entrenamiento> entrenamientos = null;
		try{
			entrenamientos = jdbcTemplate.query(SQL, new EntrenamientoMapper(), new Object[] { codigo });
			LOGGER.info(entrenamientos.toString());
		}catch(EmptyResultDataAccessException e){
			entrenamientos = new ArrayList<Entrenamiento>();
			LOGGER.info("No hay ningun valor con ese codigo "+ e.getMessage());
		}
		return entrenamientos;
	}


	@Override
	public Entrenamiento create(Entrenamiento entrenamiento) {
		LOGGER.info("create");
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "entrenamientoCreate";
		jdbcCall.withProcedureName(SQL);
		/*
		 * Le pasamos los valores de java a SQL en un mapa escepto codigo que lo recogemos
		 * una vez creado el entrenamiento en el procedimiento almacenado (ya que lo autogenera) 
		 */
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pfecha", entrenamiento.getFecha())
				.addValue("pusuario_codigo", entrenamiento.getUsuario());
		LOGGER.info(entrenamiento.toString());
		Map<String, Object> out = jdbcCall.execute(in);
		entrenamiento.setCodigo((Integer)out.get("pcodigo"));
		//Recogemos el codigo del procedimiento almacenado y lo pasamos a java
		return entrenamiento;
	}
	
	@Override
	public Entrenamiento update(Entrenamiento entrenamiento) {
		LOGGER.info("update");
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "entrenamientoUpdate";
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pfecha", entrenamiento.getFecha());
		jdbcCall.execute(in);
		return entrenamiento;
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("delete");
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "entrenamientoDelete";
		jdbcCall.withProcedureName(SQL);
		//Le pasamos los valores de java a SQL mediante un mapa
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		jdbcCall.execute(in);
	}

	@Override
	public Entrenamiento getInforme(int codigo) {
		LOGGER.info("getInforme");
		final String SQL = "call entrenamientoInforme(?);";
		Entrenamiento entrenamiento = null;
		try{
			Map<Integer, Entrenamiento> entrenamientos = jdbcTemplate.query(SQL, new EntrenamientoExtractor(), new Object[] { codigo });
			entrenamiento = entrenamientos.get(codigo);
			LOGGER.info(entrenamiento.toString());
		}catch(EmptyResultDataAccessException e){
			entrenamiento = null;
			LOGGER.error("No hay ningun valor con ese codigo "+ e.getMessage());
		}
		return entrenamiento;
	}

}
