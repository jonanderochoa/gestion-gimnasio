package com.ipartek.formacion.dbms.dao.interfaces;

import javax.sql.DataSource;

/**
 * Esta clase se encarga de configurar la cadena de conexiones
 * @author Jon Ander Ochoa Ruiz
 * 24 de mar. de 2017
 */
public interface DAOSetter {

	/**
	 * Metodo que indicara el origen de datos(DataSources) a los bean para 
	 * crear la cadena de conexiones
	 * @param dataSource origen de datos
	 */
	public void setDataSource(DataSource dataSource);
}
