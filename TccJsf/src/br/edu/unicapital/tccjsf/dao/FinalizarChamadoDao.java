package br.edu.unicapital.tccjsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.unicapital.tccjsf.model.Chamado;
import br.edu.unicapital.tccjsf.model.Usuario;


public class FinalizarChamadoDao {

	private EntityManager manager;

	public FinalizarChamadoDao(EntityManager manager){
		this.manager = manager;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos(int pagina, int quantidadePorPagina){
		Query query = manager.createQuery("SELECT x FROM Chamado x");
		query.setFirstResult(pagina);
		query.setMaxResults(quantidadePorPagina);
		return query.getResultList();
	}

	public Chamado getProtocolo(int protocolo) {
		Query query = manager.createQuery("SELECT u FROM Usuario u WHERE u.protocolo = :protocolo");
		query.setParameter("protocolo", protocolo);

		try {
			return (Chamado) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void finalizarChamado(Chamado finalizado){
		manager.merge(finalizado);
		manager.flush();
	}

}
