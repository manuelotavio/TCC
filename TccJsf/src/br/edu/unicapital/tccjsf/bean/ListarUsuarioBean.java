package br.edu.unicapital.tccjsf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.edu.unicapital.tccjsf.dao.GenericDao;
import br.edu.unicapital.tccjsf.model.Usuario;
import br.edu.unicapital.tccjsf.util.JPAUtil;


@ManagedBean
public class ListarUsuarioBean {

	private Usuario usuario;
	private EntityManager entityManager;
	private GenericDao<Usuario> usuarioDao;

	public ListarUsuarioBean() {
		this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		this.usuarioDao = new GenericDao<Usuario>(entityManager, Usuario.class);
		this.usuario = new Usuario();

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios(){

		return usuarioDao.listarTodos();
	}

	
}
