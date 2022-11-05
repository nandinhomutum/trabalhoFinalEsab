
package com.esab.trabalhofinal.dao;

import com.esab.trabalhofinal.model.Funcionario;
import com.esab.trabalhofinal.utilidades.GerenciadorDeLog;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;



/**
 *
 * @author nandi
 */
public class FuncionarioDAO implements DAOInterface<Funcionario> {
	private static FuncionarioDAO INSTANCE;

	private FuncionarioDAO() {

	}

	public static FuncionarioDAO getFuncionarioDAOInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FuncionarioDAO();
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}

	@Override
	public Funcionario get(BigInteger id) {
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		Funcionario retorno = em.find(Funcionario.class, id);
		em.getTransaction().commit();
		return retorno;
	}
	
	public List<Funcionario> get(String nome) {
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Funcionario> cq = cb.createQuery(Funcionario.class);
		Root<Funcionario> rootEntry = cq.from(Funcionario.class);
		CriteriaQuery<Funcionario> criteria = cq.select(rootEntry);
		cq.where(rootEntry.get("nome").in(Arrays.asList(nome)));
		TypedQuery<Funcionario> query = em.createQuery(criteria);
		try {
			List<Funcionario> retorno = query.getResultList();
			em.getTransaction().commit();
                        
			return retorno;
		} catch (NoResultException nre) {
			em.getTransaction().commit();
                        String mensagem = nre.getMessage();
			GerenciadorDeLog.getInstance().getLogger().severe(mensagem);
			return null;
		}
	}
	
	public List<Funcionario> getLikeName(String nome) {
		
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNativeQuery(
				"SELECT f.id , f.nome , f.idade , f.salario , f.idCargo , f.mes, f.faltas , f.ano ,f.km FROM funcionarios f WHERE NOME LIKE ?",
				Funcionario.class);
		query.setParameter(1, "%" + nome + "%");
		try {
			@SuppressWarnings("unchecked")
			List<Funcionario> retorno = query.getResultList();
			
			em.getTransaction().commit();
			return retorno;
		} catch (NoResultException nre) {
			                 GerenciadorDeLog.getInstance().getLogger().severe(nre.getMessage());
			em.getTransaction().commit();
			;
			return null;
		}
	}

	@Override
	public void save(Funcionario funcionario) {
		// @SuppressWarnings("static-access")
		// EntityManagerFactory emf = new
		// Persistence().createEntityManagerFactory("persistenceUnit");
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(funcionario);
		em.getTransaction().commit();
		//em.close();
		//emf.close();
	}

	@Override
	public void delete(BigInteger id) {
		// @SuppressWarnings("static-access")
		// EntityManagerFactory emf = new
		// Persistence().createEntityManagerFactory("persistenceUnit");
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<Funcionario> query = criteriaBuilder.createCriteriaDelete(Funcionario.class);
		Root<Funcionario> root = query.from(Funcionario.class);
		query.where(root.get("id").in(Arrays.asList(id)));
		em.getTransaction().begin();
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
		//em.close();
		//emf.close();
	}

	@Override
	public List<Funcionario> getAll(){
		// @SuppressWarnings("static-access")
		// EntityManagerFactory emf = new
		// Persistence().createEntityManagerFactory("persistenceUnit");
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Funcionario> cq = cb.createQuery(Funcionario.class);
		Root<Funcionario> rootEntry = cq.from(Funcionario.class);
		CriteriaQuery<Funcionario> all = cq.select(rootEntry);
		TypedQuery<Funcionario> allQuery = em.createQuery(all);
		List<Funcionario> retorno = allQuery.getResultList();
		em.getTransaction().commit();
		//em.close();
		//emf.close();
		return retorno;
	}
}
