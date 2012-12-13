package br.edu.unicapital.tccjsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.unicapital.tccjsf.model.Usuario;


public class UsuarioDao extends GenericDao<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioDao(EntityManager manager) {
		super(manager, Usuario.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<Usuario> listar(int pagina, int quantidadePorPagina, String filtro){
		Query query = manager.createNamedQuery("Produto.filtro", Usuario.class);
		query.setFirstResult(pagina);
		query.setMaxResults(quantidadePorPagina);
		query.setParameter("nome", "%" + filtro.toUpperCase() + "%");
		
		return query.getResultList();
	}

	
	public long getTotal(String filtro) {
		Query query = manager.createQuery("SELECT COUNT(p) FROM Produto p WHERE UPPER(p.cpf) like :cpf");
		query.setParameter("nome", "%" + filtro.toUpperCase() + "%");
		Number result = (Number) query.getSingleResult();
		return result.longValue();
	}

}
