package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.EjercicioDAO;
import com.ipartek.formacion.dbms.mapper.EjercicioMapper;
import com.ipartek.formacion.dbms.persistence.Ejercicio;
/**
 * Clase encargada de implementar los metodos de la interfaz EjercicioDAO
 * @author Jon Ander Ochoa Ruiz
 * 25 de mar. de 2017
 */
@Repository("ejercicioDAOImp")
public class EjercicioDAOImp implements EjercicioDAO{

	//Para la conexion con la BBDD
	private DataSource dataSource;
	//Para lectura (getAll, getById)
	private JdbcTemplate jdbcTemplate;
	//Para procedimientos almacenados (insert, update, delete)
	private SimpleJdbcCall jdbcCall;
	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(EjercicioDAOImp.class);
	
	/**
	 * Al inyectar el codigo del setDataSource() va al root-context donde genera un objeto
	 * (bean) de la conexion dataSource y lo guarda en la variable que hemos creado mas arriba
	 * @param dataSource
	 */
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		//Lectura de la BBDD
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Ejercicio> getAll() {
		LOGGER.info("getAll");
		final String SQL = "call ejerciciogetAll();";
		List<Ejercicio> ejercicios = null;
		try{
			ejercicios = jdbcTemplate.query(SQL, new EjercicioMapper());
			LOGGER.info(ejercicios.toString());
		}catch(EmptyResultDataAccessException e){
			LOGGER.error(e.getMessage());
			ejercicios = new ArrayList<Ejercicio>();
		}
		return ejercicios;
	}

	@Override
	public Ejercicio getById(int codigo) {
		LOGGER.info("getById");
		final String SQL = "call ejerciciogetById(?);";
		Ejercicio ejercicio = null;
		try{
			ejercicio = jdbcTemplate.queryForObject(SQL, new EjercicioMapper(), new Object[] { codigo });
			LOGGER.info(ejercicio.toString());
		}catch(EmptyResultDataAccessException e){
			ejercicio = null;
			LOGGER.info("No existe ningun elemento "+e.getMessage());
		}
		return ejercicio;
	}

	@Override
	public List<Ejercicio> getByActivity(String actividad){
		LOGGER.info("getByActivity");
		final String SQL = "call ejercicioActividad(?);";
		List<Ejercicio> ejercicios = null;
		try{
			ejercicios = jdbcTemplate.query(SQL, new EjercicioMapper(), new Object[]{ actividad });
			LOGGER.info(ejercicios.toString());
		}catch(EmptyResultDataAccessException e){
			ejercicios = new ArrayList<Ejercicio>();
			LOGGER.info("No existeningun elemento para esa actividad "+e.getMessage());
		}
		return ejercicios;
	}

	@Override
	public Ejercicio create(Ejercicio ejercicio) {
		LOGGER.info("create");
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "ejercicioCreate";
		jdbcCall.withProcedureName(SQL);
		/*
		 * Le pasamos los valores de java a SQL en un mapa escepto codigo que lo recogemos
		 * una vez creado el ejercicio del procedimiento almacenado (ya que lo autogenera) 
		 */
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pactividad", ejercicio.getActividad())
				.addValue("pgrupomuscular", ejercicio.getGrupomuscular())
				.addValue("pmaquina", ejercicio.getMaquina())
				.addValue("pdescripcion", ejercicio.getDescripcion());
		LOGGER.info(ejercicio.toString());
		//Ejecutamos el procedimiento almacenado y lo que devuelve lo metemos en un mapa(out)
		Map<String, Object> out = jdbcCall.execute(in);
		//Recogemos el codigo del procedimiento almacenado y lo pasamos a java
		ejercicio.setEjercicioCodigo((Integer)out.get("pcodigo")); 
		return ejercicio;
	}
	
	@Override
	public Ejercicio update(Ejercicio ejercicio) {
		LOGGER.info("update");
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "ejercicioUpdate";
		jdbcCall.withProcedureName(SQL);
		//Le pasamos los valores de java a SQL mediante un mapa
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pactividad", ejercicio.getActividad())
				.addValue("pgrupomuscular", ejercicio.getGrupomuscular())
				.addValue("pmaquina", ejercicio.getMaquina())
				.addValue("pdescripcion", ejercicio.getDescripcion())
				.addValue("pcodigo", ejercicio.getEjercicioCodigo());
		LOGGER.info(ejercicio.toString());
		//Ejecutamos la consulta
		jdbcCall.execute(in);
		return ejercicio;
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("delete");
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "ejercicioDelete"; 
		//Le pasamos la cadena con el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		//Creamos un mapa con los parametros que le pasamos al procedimiento almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		//Ejecuta el procedimiento almacenado
		jdbcCall.execute(in);
	}
}
