package br.edu.unicapital.tccjsf.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * The persistent class for the statususuario database table.
 * 
 */
@Entity
public class Statususuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idstatusUsuario;

	private String status;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="statususuario")
	private Set<Usuario> usuarios;

    public Statususuario() {
    }

	public int getIdstatusUsuario() {
		return this.idstatusUsuario;
	}

	public void setIdstatusUsuario(int idstatusUsuario) {
		this.idstatusUsuario = idstatusUsuario;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}