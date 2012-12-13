package br.edu.unicapital.tccjsf.bean;

import java.util.Calendar;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.edu.unicapital.tccjsf.dao.FinalizarChamadoDao;
import br.edu.unicapital.tccjsf.dao.GenericDao;
import br.edu.unicapital.tccjsf.model.Chamado;
import br.edu.unicapital.tccjsf.model.Descricaochamado;
import br.edu.unicapital.tccjsf.model.Statuschamado;
import br.edu.unicapital.tccjsf.util.JPAUtil;


public class FinalizarChamadoBean {

	private EntityManager entityManager;
	private FinalizarChamadoDao finalizarChamadoDao;

	private Chamado chamado;
	private Descricaochamado descricaoChamado;

	private LoginBean loginBean;

	public FinalizarChamadoBean(){
		this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		this.finalizarChamadoDao = new FinalizarChamadoDao(this.entityManager);
		this.descricaoChamado = new Descricaochamado();

		this.loginBean = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}


	public String finalizar(){
		
		Statuschamado cham = new Statuschamado();
		chamado.setDataFechamento(Calendar.getInstance().getTime());
		chamado.setUsuario2(loginBean.getUsuarioLogado());
		this.finalizarChamadoDao = new GenericDao<FinalizarChamadoDao>(entityManager, Statuschamado.class).ler(3); // lendo o status do bd
		cham.setIdstatusChamado(3);
		descricaoChamado.setChamado(chamado);
		descricaoChamado.setUsuarioBean(loginBean.getUsuarioLogado());
		this.entityManager.getTransaction().begin();
		this.finalizarChamadoDao.finalizarChamado(chamado);
		this.entityManager.getTransaction().commit();
		/*try {

			this.finalizarChamadoDao.finalizarChamado(chamado);

			new GenericDao<Descricaochamado>(entityManager, Descricaochamado.class).criar(descricaoChamado);
			this.entityManager.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Finalizado com sucesso!", ""));

		} catch (PersistenceException e) {
			e.printStackTrace();
			if (this.entityManager.getTransaction()!=null && this.entityManager.getTransaction().isActive()) {
				this.entityManager.getTransaction().rollback();
			}
		}*/
			return "telaInicialUsuario.jsf";
	}

	public Descricaochamado getDescricaoChamado() {
		return descricaoChamado;
	}

	public void setDescricaoChamado(Descricaochamado descricaoChamado) {
		this.descricaoChamado = descricaoChamado;
	}

}
