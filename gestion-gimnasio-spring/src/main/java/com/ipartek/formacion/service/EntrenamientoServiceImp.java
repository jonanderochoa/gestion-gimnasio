package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.EntrenamientoDAO;
import com.ipartek.formacion.dbms.persistence.Entrenamiento;
import com.ipartek.formacion.service.interfaces.EntrenamientoService;

/**
 * Clase encargada de implementar las funcionalidades creadas en la interfaz EntrenamientoService
 * @author Jon Ander Ochoa Ruiz
 * 26 de mar. de 2017
 */
@Service
public class EntrenamientoServiceImp implements EntrenamientoService {

	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(EntrenamientoServiceImp.class);
	@Inject
	private EntrenamientoDAO entrenamientoDAO;
	
	@Override
	public Entrenamiento create(Entrenamiento entrenamiento) {
		LOGGER.info("create()");
		return entrenamientoDAO.create(entrenamiento);
	}

	@Override
	public List<Entrenamiento> getAll() {
		LOGGER.info("getAll()");
		return entrenamientoDAO.getAll();
	}

	@Override
	public Entrenamiento getById(int codigo) {
		LOGGER.info("getById()");
		return entrenamientoDAO.getById(codigo);
	}
	
	@Override
	public List<Entrenamiento> getByUsuario(int codigo) {
		LOGGER.info("getByUsuario()");
		return entrenamientoDAO.getByUsuario(codigo);
	}

	@Override
	public Entrenamiento update(Entrenamiento entrenamiento) {
		LOGGER.info("update()");
		return entrenamientoDAO.update(entrenamiento);
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("delete()");
		entrenamientoDAO.delete(codigo);
	}

	@Override
	public Entrenamiento getInforme(int codigo) {
		LOGGER.info("getInforme()");
		return entrenamientoDAO.getInforme(codigo);
	}
}
