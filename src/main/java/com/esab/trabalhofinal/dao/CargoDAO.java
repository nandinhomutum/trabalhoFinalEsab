package com.esab.trabalhofinal.dao;

import com.esab.trabalhofinal.dao.Conexao;
import com.esab.trabalhofinal.dao.DAOInterface;
import com.esab.trabalhofinal.model.Cargo;
import com.esab.trabalhofinal.utilidades.GerenciadorDeLog;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import java.sql.SQLException;

/**
 *
 * @author nandi
 */
public class CargoDAO implements DAOInterface<Cargo> {

	private static CargoDAO INSTANCE;

	public CargoDAO() {

	}

	public static CargoDAO getCargoDAOInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CargoDAO();
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}

	@Override
	public List<Cargo> getAll() {
		
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cargo> cq = cb.createQuery(Cargo.class);
		Root<Cargo> rootEntry = cq.from(Cargo.class);
		CriteriaQuery<Cargo> all = cq.select(rootEntry);
		TypedQuery<Cargo> allQuery = em.createQuery(all);
		List<Cargo> retorno = allQuery.getResultList();
		em.getTransaction().commit();
		
		return retorno;
	}
		
	public Cargo get(String nome) {
		
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cargo> cq = cb.createQuery(Cargo.class);
		Root<Cargo> rootEntry = cq.from(Cargo.class);
		CriteriaQuery<Cargo> criteria = cq.select(rootEntry);
		cq.where(rootEntry.get("nome").in(Arrays.asList(nome)));
		TypedQuery<Cargo> query = em.createQuery(criteria);
		try {
			Cargo retorno = query.getSingleResult();
			em.getTransaction().commit();
			
		  return retorno;
		} catch (NoResultException nre) {
			em.getTransaction().commit();
			String mensagem = nre.getMessage();
			GerenciadorDeLog.getInstance().getLogger().severe(mensagem);
			return null;
		}
	}


	@Override
	public Cargo get(BigInteger id) {
		
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		em.getTransaction().begin();
		Cargo retorno = em.find(Cargo.class, id);
		em.getTransaction().commit();
		
		return retorno;
	}

	@Override
	public void save(Cargo cargo) {
		
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(cargo);
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(BigInteger id) {
		
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<Cargo> query = criteriaBuilder.createCriteriaDelete(Cargo.class);
		Root<Cargo> root = query.from(Cargo.class);
		query.where(root.get("id").in(Arrays.asList(id)));
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
		
	}

   
}