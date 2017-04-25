/**
 * 
 */
package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;

/**
 * Clase de persistencia
 * @author Jon Ander Ochoa Ruiz
 * 10 de abr. de 2017
 */
public class EntrenamientoEjercicio extends Ejercicio implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private int series;
	private int repeticiones;	
	private double peso;
	private int tiempo; //minutos
	private Entrenamiento entrenamiento;
	private boolean activo;
	
	public EntrenamientoEjercicio() {
		super();
		this.codigo = CODIGO_NULO;
		this.series = 0;
		this.repeticiones = 0;
		this.peso = 0.0;
		this.tiempo = 0;
		this.activo = true;
		this.entrenamiento = new Entrenamiento();
	}
	
	@Override
	public String toString() {
		return "EntrenamientoEjercicio [codigo=" + codigo + ", series=" + series + ", repeticiones=" + repeticiones
				+ ", peso=" + peso + ", tiempo=" + tiempo + ", entrenamiento=" + entrenamiento + ", activo=" + activo
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((entrenamiento == null) ? 0 : entrenamiento.hashCode());
		result = prime * result + series;
		result = prime * result + tiempo;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntrenamientoEjercicio other = (EntrenamientoEjercicio) obj;
		if (entrenamiento == null) {
			if (other.entrenamiento != null)
				return false;
		} else if (!entrenamiento.equals(other.entrenamiento))
			return false;
		if (series != other.series)
			return false;
		if (tiempo != other.tiempo)
			return false;
		return true;
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


	public int getSeries() {
		return series;
	}


	public void setSeries(int series) {
		this.series = series;
	}


	public int getRepeticiones() {
		return repeticiones;
	}


	public void setRepeticiones(int repeticiones) {
		this.repeticiones= repeticiones;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public int getTiempo() {
		return tiempo;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}


	public Entrenamiento getEntrenamiento() {
		return entrenamiento;
	}


	public void setEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}

}
