package br.edu.unicapital.tccjsf.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the chamado database table.
 * 
 */
@Entity
public class Chamado {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int protocolo;

	private String assunto;

    @Temporal( TemporalType.TIMESTAMP)
	private Date dataCriacao;

    @Temporal( TemporalType.TIMESTAMP)
	private Date dataFechamento;

	//bi-directional many-to-one association to Prioridadechamado
    @ManyToOne
	@JoinColumn(name="idPrioridadeChamado")
	private Prioridadechamado prioridadechamado;

	//bi-directional many-to-one association to Statuschamado
    @ManyToOne
	@JoinColumn(name="idStatusChamado")
	private Statuschamado statuschamado;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="usuarioCria")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="usuarioPenFina")
	private Usuario usuario2;

	//bi-directional many-to-one association to Descricaochamado
	@OneToMany(mappedBy="chamado")
	private List<Descricaochamado> descricaochamados;

    public Chamado() {
    }

	public int getProtocolo() {
		return this.protocolo;
	}

	public void setProtocolo(int protocolo) {
		this.protocolo = protocolo;
	}

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataFechamento() {
		return this.dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridadechamado getPrioridadechamado() {
		return this.prioridadechamado;
	}

	public void setPrioridadechamado(Prioridadechamado prioridadechamado) {
		this.prioridadechamado = prioridadechamado;
	}
	
	public Statuschamado getStatuschamado() {
		return this.statuschamado;
	}

	public void setStatuschamado(Statuschamado statuschamado) {
		this.statuschamado = statuschamado;
	}
	
	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}
	
	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}
	
	public List<Descricaochamado> getDescricaochamados() {
		return this.descricaochamados;
	}

	public void setDescricaochamados(List<Descricaochamado> descricaochamados) {
		this.descricaochamados = descricaochamados;
	}
	
	public String getPrazo(){
		if (prioridadechamado==null) { 
			return "Sem prioridade";
		}
		if (prioridadechamado.getIdprioridadeChamado()==1) {
			return "4 horas";
		}
		if (prioridadechamado.getIdprioridadeChamado()==2) {
			return "2 horas";
		}
		if (prioridadechamado.getIdprioridadeChamado()==3) {
			return "6 horas";
		}
		return "Prioridade nao mapeada";
	}
	
}