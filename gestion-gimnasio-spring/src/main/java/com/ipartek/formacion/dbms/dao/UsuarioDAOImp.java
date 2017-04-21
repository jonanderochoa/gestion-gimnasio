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

import com.ipartek.formacion.dbms.dao.interfaces.UsuarioDAO;
import com.ipartek.formacion.dbms.mapper.UsuarioMapper;
import com.ipartek.formacion.dbms.persistence.Usuario;

/**
 * Clase encargada de implementar los metodos de CRUD de la interfaz UsuarioDAO
 * @author Jon Ander Ochoa Ruiz
 * 26 de mar. de 2017
 */
@Repository("usuarioDAOImp")
public class UsuarioDAOImp implements UsuarioDAO {

	//Para la conexion con la BBDD
	private DataSource dataSource;
	//Para lectura (GETALL, GETBYID)
	private JdbcTemplate jdbcTemplate;
	//Variable que almacena los procedimientos almacenados (INSERT, UODATE, DELETE)
	private SimpleJdbcCall jdbcCall;
	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDAOImp.class);
	
	/**
	 * Al inyectar el codigo del setDataSource() va al root-context donde genera un objeto
	 * (bean) de la conexion dataSource y lo guarda en la variable que hemos creado mas arriba
	 * @param dataSource
	 */
	@Inject
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		//Lectura de la BBDD
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		//
	}

	@Override
	public Usuario create(Usuario usuario) {
		LOGGER.info("create");
		final String SQL = "usuarioCreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		jdbcCall.withProcedureName(SQL);//Se le asigna el nombre del procedimiento almacenado 
		/*
		 * Le pasamos los valores de java a SQL en un mapa escepto codigo que lo recogemos
		 * una vez creado el usuario en el procedimiento almacenado (ya que lo autogenera) 
		 */
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", usuario.getNombre())
				.addValue("papellidos", usuario.getApellidos())
				.addValue("puser", usuario.getUser())
				.addValue("ppass", usuario.getPass())
				.addValue("pemail", usuario.getEmail());
		LOGGER.info(usuario.toString());
		//jdbcCall.execute(in); Ejecuta la consulta dandole los parametros del mapa in y devuelve el out
		//Recogemos los datos que devuelve la consulta con otro mapa llamado out
		Map<String, Object> out = jdbcCall.execute(in);
		//Recogemos el codigo del procedimiento almacenado y lo pasamos a java
		usuario.setCodigo((Integer)out.get("pcodigo"));
		return usuario;
	}

	@Override
	public List<Usuario> getAll() {
		LOGGER.info("getAll");
		final String SQL = "call usuariogetAll();";
		List<Usuario> usuarios = null;
		try{
			usuarios = jdbcTemplate.query(SQL, new UsuarioMapper());
			LOGGER.info(usuarios.toString());
		}catch(EmptyResultDataAccessException e){
			usuarios = new ArrayList<Usuario>();
			LOGGER.error(e.getMessage());
		}
		return usuarios;
	}

	@Override
	public Usuario getById(int codigo) {
		LOGGER.info("getById");
		Usuario usuario = null;
		final String SQL = "call usuariogetById(?);";
		try{
			usuario = jdbcTemplate.queryForObject(SQL, new UsuarioMapper(), new Object[] { codigo });
			LOGGER.info(usuario.toString());
		}catch(EmptyResultDataAccessException e){
			usuario = null;
			LOGGER.trace("No se ha encontrado usuario para codigo: "+codigo+" "+e.getMessage());
		}
		return usuario;
	}
	
	@Override
	public Usuario getByUser(String user) {
		LOGGER.info("getByUser");
		Usuario usuario = null;
		final String SQL = "call usuariogetByUser(?);";
		try{
			usuario = jdbcTemplate.queryForObject(SQL, new UsuarioMapper(), new Object[] { user });
			LOGGER.info(usuario.toString());
		}catch(EmptyResultDataAccessException e){
			usuario = null;
			LOGGER.trace("No se ha encontrado usuario para ese user: "+user+" "+e.getMessage());
		}
		return usuario;
	}

	@Override
	public Usuario update(Usuario usuario) {
		LOGGER.info("update");
		final String SQL = "usuarioUpdate";
		//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		this.jdbcCall = new SimpleJdbcCall(dataSource);//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		jdbcCall.withProcedureName(SQL);//Se le asigna el nombre del procedimiento almacenado 
		//Le pasamos los valores de java a SQL mediante un mapa
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", usuario.getNombre())
				.addValue("papellidos", usuario.getApellidos())
				.addValue("puser", usuario.getUser())
				.addValue("ppass", usuario.getPass())
				.addValue("pemail", usuario.getEmail())
				.addValue("pcodigo", usuario.getCodigo());
		LOGGER.info(usuario.toString());
		//Ejecutamos la consulta dandole los parametros del mapa in
		jdbcCall.execute(in);
		return usuario;
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("delete");
		//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "usuarioDelete";
		//Le pasamos la cadena con el nombre del procedimiento almacenado a la instancia de SimpleJdbcCall
		jdbcCall.withProcedureName(SQL);
		//Creamos un mapa con los parametros del procedimiento almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		//Ejecutamos la consulta dandole los parametros del mapa in
		jdbcCall.execute(in);
	}

	/* (non-Javadoc)
	 * @see com.ipartek.formacion.dbms.dao.interfaces.UsuarioDAO#deleteReal(int)
	 */
	@Override
	public void deleteReal(int codigo) {
		LOGGER.info("deleteReal");
		//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "usuarioDeleteReal";
		//Le pasamos la cadena con el nombre del procedimiento almacenado a la instancia de SimpleJdbcCall
		jdbcCall.withProcedureName(SQL);
		//Creamos un mapa con los parametros del procedimiento almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		//Ejecutamos la consulta dandole los parametros del mapa in
		jdbcCall.execute(in);
	}

}
