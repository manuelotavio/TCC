package br.edu.unicapital.tccjsf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.edu.unicapital.tccjsf.dao.GenericDao;
import br.edu.unicapital.tccjsf.model.Chamado;
import br.edu.unicapital.tccjsf.util.JPAUtil;


@ManagedBean
public class ChamadoFinalizadoBean {
	private Chamado chamado;
	private EntityManager entityManager;
	private GenericDao<Chamado> chamadoDao;

	private LoginBean loginBean;

	public ChamadoFinalizadoBean() {
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
		if(loginBean.getUsuarioLogado().getTipousuario().getIdtipoUsuario() == 3){
			return chamadoDao.listarTodosFinalizados(chamado.getStatuschamado(), loginBean.getCpf());
		}
		return chamadoDao.listarFinalizados(chamado.getStatuschamado(), loginBean.getCpf());
	}
}
