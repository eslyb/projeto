package br.com.locadora.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fgv.dao.VeiculoDAO;
import br.com.locadora.model.Veiculo;

@Named
@javax.faces.view.ViewScoped
public class VeiculoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Veiculo veiculo; 
	
	@Inject
	private VeiculoDAO veiculoDAO;
	
	private List<Veiculo> veiculos;
	
	@PostConstruct
	public void init() {
		veiculos = new ArrayList<Veiculo>();
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public void cadastrarVeiculo() {
		//VeiculoDAO veiculos = new VeiculoDAO();
		if(veiculo.getId()==null) {
			veiculo.setSituacao(1);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com Sucesso", "O veículo foi cadastrado com Sucesso!"));
		}else {	
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso", "O veículo foi alterado com Sucesso!"));
		}
		this.veiculoDAO.salvar(veiculo);
		veiculo = new Veiculo();
	}
	
	public List<Veiculo> getVeiculos() {
		//VeiculoDAO veiculoDao = new VeiculoDAO();
		veiculos = this.veiculoDAO.listar();
		
		return veiculos;
	}
	
	public void deletarVeiculo(Veiculo veiculo) {
		System.out.println("Aqui");
		//VeiculoDAO veiculoDao = new VeiculoDAO();
		System.out.println(veiculo.getId());
		this.veiculoDAO.remover(veiculo.getId());
	}
	
	public void alterarVeiculo(Veiculo veiculo) {
		//VeiculoDAO veiculoDao = new VeiculoDAO();
		this.veiculo = this.veiculoDAO.buscarPorId(veiculo.getId());
	}

}
