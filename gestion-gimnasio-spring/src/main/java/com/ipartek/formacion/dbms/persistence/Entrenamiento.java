package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Clasede persistencia
 * @author Jon Ander Ochoa Ruiz
 * 16 de abr. de 2017
 */
public class Entrenamiento implements Serializable, Comparable<Entrenamiento> {

	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private Date fecha;
	private Usuario usuario;
	private List<EntrenamientoEjercicio> listaEntrenamientoEjercicio;
	private boolean activo;
	
	public Entrenamiento() {
		super();
		this.codigo = CODIGO_NULO;
		this.fecha = new Date();
		usuario = new Usuario();
		listaEntrenamientoEjercicio = new ArrayList<EntrenamientoEjercicio>();
		this.activo = true; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrenamiento other = (Entrenamiento) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrenamiento [codigo=" + codigo + ", fecha=" + fecha + ", usuario=" + usuario
				+ ", listaEntrenamientoEjercicio=" + listaEntrenamientoEjercicio + ", activo=" + activo + "]";
	}


	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<EntrenamientoEjercicio> getListaEntrenamientoEjercicio() {
		return listaEntrenamientoEjercicio;
	}

	public void setListaEntrenamientoEjercicio(List<EntrenamientoEjercicio> listaEntrenamientoEjercicio) {
		this.listaEntrenamientoEjercicio = listaEntrenamientoEjercicio;
	}

	@Override
	public int compareTo(Entrenamiento o) {
		return this.getFecha().compareTo(o.getFecha());
	}
}
