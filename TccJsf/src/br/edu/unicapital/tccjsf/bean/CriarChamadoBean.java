package br.edu.unicapital.tccjsf.bean;

import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.unicapital.tccjsf.dao.CriarChamadoDao;
import br.edu.unicapital.tccjsf.dao.GenericDao;
import br.edu.unicapital.tccjsf.model.Chamado;
import br.edu.unicapital.tccjsf.model.Descricaochamado;
import br.edu.unicapital.tccjsf.model.Prioridadechamado;
import br.edu.unicapital.tccjsf.model.Statuschamado;
import br.edu.unicapital.tccjsf.util.JPAUtil;



@ManagedBean
public class CriarChamadoBean {

	private EntityManager entityManager;
	private CriarChamadoDao criarChamadoDao;

	private Chamado chamado;
	private Descricaochamado descricaoChamado;

	private LoginBean loginBean;

	private int prioridadeChamado;

	public CriarChamadoBean(){
		this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		this.criarChamadoDao = new CriarChamadoDao(this.entityManager);
		this.chamado = new Chamado();
		this.descricaoChamado = new Descricaochamado();

		this.loginBean = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}


	public String cadastrar(){

		
		chamado.setDataCriacao(Calendar.getInstance().getTime());

		Prioridadechamado p = new GenericDao<Prioridadechamado>(entityManager, Prioridadechamado.class).ler(prioridadeChamado);
		chamado.setPrioridadechamado(p);

		chamado.setUsuario1(loginBean.getUsuarioLogado());


		Statuschamado status = new GenericDao<Statuschamado>(entityManager, Statuschamado.class).ler(1); // lendo o status do bd
		chamado.setStatuschamado(status);

		descricaoChamado.setChamado(chamado);
		descricaoChamado.setUsuarioBean(loginBean.getUsuarioLogado());
		this.entityManager.getTransaction().begin();
		try {

			this.criarChamadoDao.criarChamado(chamado);

			new GenericDao<Descricaochamado>(entityManager, Descricaochamado.class).criar(descricaoChamado);
			this.entityManager.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Criado com sucesso", ""));

		} catch (PersistenceException e) {
			e.printStackTrace();
			if (this.entityManager.getTransaction()!=null && this.entityManager.getTransaction().isActive()) {
				this.entityManager.getTransaction().rollback();
			}
		}
			return "telaInicialUsuario.jsf";
	}

	public Descricaochamado getDescricaoChamado() {
		return descricaoChamado;
	}

	public void setDescricaoChamado(Descricaochamado descricaoChamado) {
		this.descricaoChamado = descricaoChamado;
	}

	public int getPrioridadeChamado() {
		return prioridadeChamado;
	}

	public void setPrioridadeChamado(int prioridadeChamado) {
		this.prioridadeChamado = prioridadeChamado;
	}

}
