package br.edu.unicapital.tccjsf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipousuario database table.
 * 
 */
@Entity
public class Tipousuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtipoUsuario;

	private String tipo;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tipousuario")
	private Set<Usuario> usuarios;

    public Tipousuario() {
    }
    

	public int getIdtipoUsuario() {
		return this.idtipoUsuario;
	}

	public void setIdtipoUsuario(int idtipoUsuario) {
		this.idtipoUsuario = idtipoUsuario;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public String toString(){
		return getTipo();
	}
	
}