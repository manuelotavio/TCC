package br.edu.unicapital.tccjsf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the descricaochamado database table.
 * 
 */
@Entity
public class Descricaochamado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int iddescricaoChamado;

    @Lob()
	private String descricao;

	//bi-directional many-to-one association to Chamado
    @ManyToOne
	@JoinColumn(name="protocoloChamado")
	private Chamado chamado;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuarioBean;

    public Descricaochamado() {
    }

	public int getIddescricaoChamado() {
		return this.iddescricaoChamado;
	}

	public void setIddescricaoChamado(int iddescricaoChamado) {
		this.iddescricaoChamado = iddescricaoChamado;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Chamado getChamado() {
		return this.chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	
	public Usuario getUsuarioBean() {
		return this.usuarioBean;
	}

	public void setUsuarioBean(Usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}
	public String toString(){
		return getDescricao(); 
	}
	
}