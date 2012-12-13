package br.edu.unicapital.tccjsf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.edu.unicapital.tccjsf.dao.GenericDao;
import br.edu.unicapital.tccjsf.model.Chamado;
import br.edu.unicapital.tccjsf.util.JPAUtil;


@ManagedBean
public class ChamadoAndamentoBean {
	private Chamado chamado;
	private EntityManager entityManager;
	private GenericDao<Chamado> chamadoDao;

	private LoginBean loginBean;

	public ChamadoAndamentoBean() {
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
	
//	public Chamado getChamado(int protocolo) {
//		chamadoDao.pesquisarChamado(protocolo);
//		return ;
//	}

	public List<Chamado> getChamados(){
		if(loginBean.getUsuarioLogado().getTipousuario().getIdtipoUsuario() == 1){
			return chamadoDao.listarAndamento(chamado.getStatuschamado(), loginBean.getCpf());
		} else{
			if(loginBean.getUsuarioLogado().getTipousuario().getIdtipoUsuario() == 2){
				
				return chamadoDao.listarTodosAndamento(chamado.getStatuschamado(), loginBean.getCpf());
			}
			return chamadoDao.listarTodosAndamento(chamado.getStatuschamado(), loginBean.getCpf());

		}
	}
}
