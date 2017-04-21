package com.ipartek.formacion.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.EntrenamientoEjercicioDAO;
import com.ipartek.formacion.dbms.persistence.EntrenamientoEjercicio;
import com.ipartek.formacion.service.interfaces.EntrenamientoEjercicioService;

/**
 * Clase encargada de implementar las funcionalidades creadas en la interfaz EntrenamientoEjercicioService
 * @author Jon Ander Ochoa Ruiz
 * 17 de abr. de 2017
 */
@Service
public class EntrenamientoEjercicioServiceImp implements EntrenamientoEjercicioService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntrenamientoEjercicioServiceImp.class);
	@Autowired
	private EntrenamientoEjercicioDAO eEDAO;
	
	@Override
	public EntrenamientoEjercicio create(EntrenamientoEjercicio entrenamientoEjercicio) {
		LOGGER.info("create()");
		return eEDAO.create(entrenamientoEjercicio);
	}

	@Override
	public List<EntrenamientoEjercicio> getAll() {
		LOGGER.info("getAll()");
		return eEDAO.getAll();
	}

	@Override
	public EntrenamientoEjercicio getById(int codigo) {
		LOGGER.info("getById()");
		return eEDAO.getById(codigo);
	}

	@Override
	public EntrenamientoEjercicio update(EntrenamientoEjercicio entrenamientoEjercicio) {
		LOGGER.info("update()");
		return eEDAO.update(entrenamientoEjercicio);
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("delete()");
		eEDAO.delete(codigo);
	}

	@Override
	public List<EntrenamientoEjercicio> getByEntrenamiento(int codigo) {
		LOGGER.info("getByEntrenamiento()");
		return eEDAO.getByEntrenamiento(codigo);
	}

}
