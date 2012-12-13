package br.edu.unicapital.tccjsf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.edu.unicapital.tccjsf.dao.GenericDao;
import br.edu.unicapital.tccjsf.model.Chamado;
import br.edu.unicapital.tccjsf.util.JPAUtil;


@ManagedBean
public class HistoricoChamadosBean {
	private Chamado chamado;
	private EntityManager entityManager;
	private GenericDao<Chamado> chamadoDao;

	private LoginBean loginBean;

	public HistoricoChamadosBean() {
		this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		this.chamadoDao = new GenericDao<Chamado>(entityManager, Chamado.class);
		this.chamado = new Chamado();
		
		this.loginBean = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");


	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public List<Chamado> getChamados(){

		return chamadoDao.listarHistorico(chamado.getStatuschamado(), loginBean.getCpf());
	}
}
