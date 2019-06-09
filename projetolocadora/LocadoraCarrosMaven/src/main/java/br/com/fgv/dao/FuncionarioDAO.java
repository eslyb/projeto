package br.com.fgv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.locadora.model.Funcionario;
import br.com.locadora.util.JPAUtil;

public class FuncionarioDAO implements GenericDAO<Funcionario>{
	
	@Override
	public void salvar(Funcionario entidade) {
		EntityManager em = JPAUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			if(entidade.getId() == null) {
				em.persist(entidade);
			}else {
				em.merge(entidade);
			}
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

	@Override
	public void remover(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			
			Funcionario funcionario = em.find(Funcionario.class, id);
			
			em.remove(funcionario);
			
			em.getTransaction().commit();
		}finally {
			em.close();
		}
		
	}

	@Override
	public Funcionario buscarPorId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.find(Funcionario.class, id);
		}finally {
			em.close();
		}
	}
	
	@Override
	public List<Funcionario> listar() {
		List<Funcionario> usuarios = new ArrayList<Funcionario>();
		EntityManager em = JPAUtil.getEntityManager();
		try {
			usuarios = em.createQuery("from " + Funcionario.class.getSimpleName(), Funcionario.class).getResultList();
			return usuarios;
		}finally {
			em.close();
		}
	}
	
	public Funcionario login(Funcionario f) {
		EntityManager em = JPAUtil.getEntityManager();
		Funcionario usuario;
		TypedQuery<Funcionario> query = em.createQuery("select u from Funcionario u where u.login = :login and u.senha = :senha", Funcionario.class);
		query.setParameter("login", f.getLogin());
		query.setParameter("senha", f.getSenha());
		
		try {
			usuario = query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
		em.close();
		
		return usuario;
	}

}
