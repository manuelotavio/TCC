package br.edu.unicapital.tccjsf.dao;

import javax.persistence.EntityManager;

import br.edu.unicapital.tccjsf.model.Usuario;


public class CriarUsuarioDao {
	
	private EntityManager manager;

	public CriarUsuarioDao(EntityManager manager){
		this.manager = manager;
	}

	public void criarUsuario(Usuario novo){
		manager.persist(novo);
		manager.flush();
	}


}
