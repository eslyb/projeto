package br.com.fgv.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.locadora.model.Veiculo;
import br.com.locadora.util.JPAUtil;

public class VeiculoDAO implements GenericDAO<Veiculo>, Serializable{
	
	@Inject
	EntityManager em;
	
	@Override
	public void salvar(Veiculo entidade) {
		//EntityManager em = JPAUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			if(entidade.getId() == null) {
				em.persist(entidade);
			}else {
				em.merge(entidade);
			}
			em.getTransaction().commit();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void remover(int id) {
		//EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			
			Veiculo veiculo = em.find(Veiculo.class, id);
			
			em.remove(veiculo);
			
			em.getTransaction().commit();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public Veiculo buscarPorId(int id) {
		//EntityManager em = JPAUtil.getEntityManager();

		return em.find(Veiculo.class, id);
		
	}
	
	@Override
	public List<Veiculo> listar() {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		//EntityManager em = JPAUtil.getEntityManager();

		veiculos = em.createQuery("from " + Veiculo.class.getSimpleName(), Veiculo.class).getResultList();
		return veiculos;

	}

}
