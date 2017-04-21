package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.UsuarioDAO;
import com.ipartek.formacion.dbms.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

/**
 * Clase encargada de implementar las funcionalidades creadas en la interfaz UsuarioService
 * @author Jon Ander Ochoa Ruiz
 * 26 de mar. de 2017
 */
@Service
public class UsuarioServiceImp implements UsuarioService {

	//Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImp.class);
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Override
	public Usuario create(Usuario usuario) {
		LOGGER.info("create()");
		return usuarioDAO.create(usuario);
	}

	@Override
	public List<Usuario> getAll() {
		LOGGER.info("getAll()");
		return usuarioDAO.getAll();
	}

	@Override
	public Usuario getById(int codigo) {
		LOGGER.info("getById()");
		return usuarioDAO.getById(codigo);
	}

	@Override
	public Usuario getByUser(String user) {
		LOGGER.info("getByUser()");
		return usuarioDAO.getByUser(user);
	}
	
	@Override
	public Usuario update(Usuario usuario) {
		LOGGER.info("update()");
		return usuarioDAO.update(usuario);
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("delete()");
		usuarioDAO.delete(codigo);
	}

	

}
