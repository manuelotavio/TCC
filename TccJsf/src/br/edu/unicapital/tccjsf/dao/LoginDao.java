package br.edu.unicapital.tccjsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.unicapital.tccjsf.model.Usuario;


public class LoginDao {

	protected EntityManager manager;
	
	public LoginDao(EntityManager manager) {
		this.manager = manager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos(int pagina, int quantidadePorPagina){
		Query query = manager.createQuery("SELECT x FROM Usuario x");
		query.setFirstResult(pagina);
		query.setMaxResults(quantidadePorPagina);
		return query.getResultList();
	}
	
	public Usuario getUsuario(String cpf, String senha) {
		Query query = manager.createQuery("SELECT u FROM Usuario u WHERE u.cpf = :cpf AND u.senha = :senha");
		query.setParameter("cpf", cpf);
		query.setParameter("senha", senha);

		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
