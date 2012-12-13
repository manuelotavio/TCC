package br.edu.unicapital.tccjsf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prioridadechamado database table.
 * 
 */
@Entity
public class Prioridadechamado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idprioridadeChamado;

	private String descricao;

	//bi-directional many-to-one association to Chamado
	@OneToMany(mappedBy="prioridadechamado")
	private List<Chamado> chamados;

    public Prioridadechamado() {
    }

	public int getIdprioridadeChamado() {
		return this.idprioridadeChamado;
	}

	public void setIdprioridadeChamado(int idprioridadeChamado) {
		this.idprioridadeChamado = idprioridadeChamado;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Chamado> getChamados() {
		return this.chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
}