package br.edu.unicapital.tccjsf.bean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.edu.unicapital.tccjsf.dao.LoginDao;
import br.edu.unicapital.tccjsf.model.Tipousuario;
import br.edu.unicapital.tccjsf.model.Usuario;
import br.edu.unicapital.tccjsf.util.JPAUtil;



@ManagedBean
@SessionScoped
public class LoginBean {

	private EntityManager entityManager;
	private LoginDao loginDao;

	private String cpf;
	private String senha;
	private Usuario usuarioLogado;
	private Tipousuario tipoUsuario;


	public LoginBean() {
		this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		this.loginDao = new LoginDao(this.entityManager);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String login(){
		this.setUsuarioLogado(loginDao.getUsuario(cpf, senha));
		if(usuarioLogado == null){
			return "erroLogin.xhtml";
		}if(usuarioLogado.getStatususuario().getIdstatusUsuario() == 2){
			return "erroLogin.xhtml";
		}else {
			if(usuarioLogado.getTipousuario().getIdtipoUsuario() == 1){

				return "telaInicialUsuario.jsf";
			} else{
				if(usuarioLogado.getTipousuario().getIdtipoUsuario() == 2){
					return "telaInicialAtendente.jsf";
				}
			}
		}
		return "telaInicialAdm.jsf";
	}

	public String logout(){
		this.setUsuarioLogado(null);
		return null;
	}


	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Tipousuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Tipousuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
