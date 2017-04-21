package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
/**
 * Clase de persistencia
 * @author Jon Ander Ochoa Ruiz
 * 16 de abr. de 2017
 */
public class Usuario implements Serializable, Comparable<Usuario>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private String nombre;
	private String apellidos;
	private String user;
	private String pass;
	private String email;
	private boolean activo;
	
	public Usuario() {
		super();
		this.codigo = CODIGO_NULO;
		this.nombre = "";
		this.apellidos = "";
		this.user = "";
		this.pass = "";
		this.email = "";
		this.activo = true;
	}

	@Override
	public int compareTo(Usuario o) {
		return this.getNombre().compareToIgnoreCase(o.getNombre());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Usuario other = (Usuario) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", user=" + user
				+ ", pass=" + pass + ", email=" + email + ", activo=" + activo + "]";
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
