package br.edu.unicapital.tccjsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.unicapital.tccjsf.dao.CriarUsuarioDao;
import br.edu.unicapital.tccjsf.dao.GenericDao;
import br.edu.unicapital.tccjsf.model.Statususuario;
import br.edu.unicapital.tccjsf.model.Tipousuario;
import br.edu.unicapital.tccjsf.model.Usuario;
import br.edu.unicapital.tccjsf.util.JPAUtil;


@ManagedBean
public class CriarUsuarioBean {
	
	private EntityManager entityManager;
	private CriarUsuarioDao criarUsuarioDao;
	
	private Usuario usuario;
	private int tipoUsuario;
	
	public CriarUsuarioBean(){
		this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		this.criarUsuarioDao = new CriarUsuarioDao(this.entityManager);
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String criar(){
		
		Tipousuario t = new GenericDao<Tipousuario>(entityManager, Tipousuario.class).ler(tipoUsuario);
		usuario.setTipousuario(t);
		
		Statususuario status = new GenericDao<Statususuario>(entityManager, Statususuario.class).ler(1); 
		usuario.setStatususuario(status);
		
		this.entityManager.getTransaction().begin();
		
		try {
			
			this.criarUsuarioDao.criarUsuario(usuario);
			
			new GenericDao<Usuario>(entityManager, Usuario.class).criar(usuario);
			this.entityManager.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Criado com sucesso", ""));
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (this.entityManager.getTransaction()!=null && this.entityManager.getTransaction().isActive()) {
				this.entityManager.getTransaction().rollback();
			}
		}
		return "usuario.jsf";
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	

}
