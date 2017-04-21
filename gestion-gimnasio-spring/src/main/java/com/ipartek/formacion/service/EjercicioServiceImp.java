package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.EjercicioDAO;
import com.ipartek.formacion.dbms.persistence.Ejercicio;
import com.ipartek.formacion.service.interfaces.EjercicioService;


/**
 * Clase encargada de implementar las funcionalidades creadas en la interfaz EjercicioService
 * @author Jon Ander Ochoa Ruiz
 * 25 de mar. de 2017
 */
@Service
public class EjercicioServiceImp implements EjercicioService {
	
	@Inject
	private EjercicioDAO ejercicioDAO;
	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(EjercicioServiceImp.class);
	
	
	@Override
	public Ejercicio create(Ejercicio ejercicio) {
		LOGGER.info("Create");
		return ejercicioDAO.create(ejercicio);
	}

	@Override
	public List<Ejercicio> getAll() {
		LOGGER.info("GetAll");
		return ejercicioDAO.getAll();
	}

	@Override
	public Ejercicio getById(int codigo) {
		LOGGER.info("GetById");
		return ejercicioDAO.getById(codigo);
	}

	@Override
	public Ejercicio update(Ejercicio ejercicio) {
		LOGGER.info("Update");
		return ejercicioDAO.update(ejercicio);
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("Delete");
		ejercicioDAO.delete(codigo);
	}

	@Override
	public List<Ejercicio> getByActivity(String actividad) {
		LOGGER.info("getByActivity");
		return ejercicioDAO.getByActivity(actividad);
	}

}
