package br.edu.unicapital.tccjsf.dao;

import javax.persistence.EntityManager;

import br.edu.unicapital.tccjsf.model.Chamado;


public class CriarChamadoDao {

	private EntityManager manager;

	public CriarChamadoDao(EntityManager manager){
		this.manager = manager;
	}

	public void criarChamado(Chamado novo){
		manager.persist(novo);
		manager.flush();
	}

}
