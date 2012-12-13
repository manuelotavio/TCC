package br.edu.unicapital.tccjsf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the statuschamado database table.
 * 
 */
@Entity
public class Statuschamado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idstatusChamado;

	private String tipo;

	//bi-directional many-to-one association to Chamado
	@OneToMany(mappedBy="statuschamado")
	private List<Chamado> chamados;

    public Statuschamado() {
    }

	public int getIdstatusChamado() {
		return this.idstatusChamado;
	}

	public void setIdstatusChamado(int idstatusChamado) {
		this.idstatusChamado = idstatusChamado;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Chamado> getChamados() {
		return this.chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
}