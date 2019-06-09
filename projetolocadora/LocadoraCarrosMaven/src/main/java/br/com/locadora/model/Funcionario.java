package br.com.locadora.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.locadora.enums.TipoUsuario;

@Entity
@DiscriminatorValue("F")
public class Funcionario extends Pessoa{

	private String login;
	private String senha;
	private String numeroCTPS;
	private String PIS;
	private BigDecimal salario;
	private Date dataAdmissao;
	private Date dataDemissao;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNumeroCTPS() {
		return numeroCTPS;
	}
	public void setNumeroCTPS(String numeroCTPS) {
		this.numeroCTPS = numeroCTPS;
	}
	public String getPIS() {
		return PIS;
	}
	public void setPIS(String pIS) {
		PIS = pIS;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Date getDataDemissao() {
		return dataDemissao;
	}
	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
}
