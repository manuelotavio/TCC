package br.edu.unicapital.tccjsf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cpf;

	private String email;

	private String nome;

	private String rg;

	private String senha;

	private int telefone;

	//bi-directional many-to-one association to Statususuario
    @ManyToOne
	private Statususuario statususuario;

	//bi-directional many-to-one association to Tipousuario
    @ManyToOne
	private Tipousuario tipousuario;
    
    public Usuario() {
    }

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTelefone() {
		return this.telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public Statususuario getStatususuario() {
		return this.statususuario;
	}

	public void setStatususuario(Statususuario statususuario) {
		this.statususuario = statususuario;
	}
	
	public Tipousuario getTipousuario() {
		return this.tipousuario;
	}

	public void setTipousuario(Tipousuario tipousuario) {
		this.tipousuario = tipousuario;
	}
	
}