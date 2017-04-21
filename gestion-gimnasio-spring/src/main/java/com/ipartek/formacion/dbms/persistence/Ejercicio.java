package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
/**
 * Clase de persistencia
 * @author Jon Ander Ochoa Ruiz
 * 16 de abr. de 2017
 */
public class Ejercicio implements Serializable, Comparable<Ejercicio> {

	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	private int ejercicioCodigo;
	private String actividad;
	private String grupomuscular;
	private String maquina;
	private String descripcion;
	private boolean ejercicioActivo;
	
	public Ejercicio() {
		super();
		this.ejercicioCodigo = CODIGO_NULO;
		this.actividad = "";
		this.grupomuscular = "";
		this.maquina = "";
		this.descripcion = "";
		this.ejercicioActivo = true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maquina == null) ? 0 : maquina.hashCode());
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
		Ejercicio other = (Ejercicio) obj;
		if (maquina == null) {
			if (other.maquina != null)
				return false;
		} else if (!maquina.equals(other.maquina))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ejercicio [codigo=" + ejercicioCodigo + ", actividad=" + actividad + ", grupomuscular=" + grupomuscular
				+ ", maquina=" + maquina + ", descripcion=" + descripcion + ", activo=" + ejercicioActivo + "]";
	}

	
	public boolean isEjercicioActivo() {
		return ejercicioActivo;
	}

	public void setEjercicioActivo(boolean ejercicioActivo) {
		this.ejercicioActivo = ejercicioActivo;
	}

	@Override
	public int compareTo(Ejercicio o) {
		return this.getMaquina().compareToIgnoreCase(o.getMaquina());
	}

	public int getEjercicioCodigo() {
		return ejercicioCodigo;
	}
	public void setEjercicioCodigo(int ejercicioCodigo) {
		this.ejercicioCodigo = ejercicioCodigo;
	}
	public String getMaquina() {
		return maquina;
	}
	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getGrupomuscular() {
		return grupomuscular;
	}
	public void setGrupomuscular(String grupomuscular) {
		this.grupomuscular = grupomuscular;
	}
}
