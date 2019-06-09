package br.com.locadora.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fgv.dao.FuncionarioDAO;
import br.com.locadora.model.Funcionario;

@Named
@RequestScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Funcionario funcionario = new Funcionario();

	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public String efetuarLogin() {
		FuncionarioDAO uDao = new FuncionarioDAO();
		Funcionario f = uDao.login(funcionario);
		boolean existe = f != null;
		System.out.println(existe);
		if(existe) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("funcionario", f);
			return "pages/index?faces-redirect=true";
		}else {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Funcionário não Encontrado!"));
			return "login?faces-redirect=true";
		}
	}
	
	public String efetuarLogout() {
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("funcionario");
		
		System.out.println("logout");
		
		return "/login?faces-redirect=true";
	}

}
