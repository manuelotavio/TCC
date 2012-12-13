package br.edu.unicapital.tccjsf.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.unicapital.tccjsf.model.Statuschamado;
import br.edu.unicapital.tccjsf.util.JPAUtil;


public class GenericDao<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected EntityManager manager;
	protected Class<?> tipo;
	protected String nomeTabela;
	

	public GenericDao(EntityManager manager, Class<?> tipo) {
		this.manager = manager;
		this.tipo = tipo;
		this.nomeTabela = JPAUtil.getTableName(tipo);
	}
	
	public void criar(T t) {
		manager.persist(t);
		manager.flush();
	}
	
	@SuppressWarnings("unchecked")
	public T ler(int codigo){
		return (T) manager.getReference(tipo, codigo);
	}
	
	public void remover(T t){
		manager.remove(t);
		manager.flush();
	}
	
	public void atualizar(T t){
		manager.merge(t);
		manager.flush();
	}

//	@SuppressWarnings("unchecked")
//	public List<T> listarTodos(int pagina, int quantidadePorPagina){
//		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x");
//		query.setFirstResult(pagina);
//		query.setMaxResults(quantidadePorPagina);
//		return query.getResultList();
//	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarTodos(){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x");
		return query.getResultList();
	}
	
	public long getTotal() {
		Query query = manager.createQuery("SELECT COUNT(p) FROM " + nomeTabela + " p");
		Number result = (Number) query.getSingleResult();
		return result.longValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarAndamento(Statuschamado statuschamado, String cpf){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x" + " where idstatusChamado in (1, 2)"
				+ " and usuarioCria = " + cpf);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarTodosAndamento(Statuschamado statuschamado, String cpf){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x" + " where idstatusChamado = " + 1 +
				"and usuarioCria !=" + cpf);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarTodosPendentes(Statuschamado statuschamado, String cpf){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x" + " where idstatusChamado = " + 2 +
				"and usuarioCria !=" + cpf);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarPendentes(Statuschamado statuschamado, String cpf){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x" + " where idstatusChamado = " + 2
				+ "and usuarioPenFina =" + cpf);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarFinalizados(Statuschamado statuschamado, String cpf){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x" + " where idstatusChamado = " + 3
				+ "and usuarioPenFina =" + cpf);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarTodosFinalizados(Statuschamado statuschamado, String cpf){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x" + " where idstatusChamado = " + 3 +
				"and usuarioCria !=" + cpf);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarHistorico(Statuschamado statuschamado, String cpf){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x" + " where idstatusChamado = " + 3
				+ "and usuarioCria =" + cpf);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> pesquisarUsuario(String cpf){
		Query query = manager.createQuery("SELECT x FROM " + nomeTabela + " x" + " where cpf = " + cpf);
		return query.getResultList();
	}
	
}
