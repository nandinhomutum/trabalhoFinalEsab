/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esab.trabalhofinal.dao;

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

import com.esab.trabalhofinal.model.HistoricoSalario;
import com.esab.trabalhofinal.utilidades.GerenciadorDeLog;

/**
 *
 * @author Administrador
 */
public class HistoricoSalarioDAO implements DAOInterface<HistoricoSalario>{
private static HistoricoSalarioDAO INSTANCE;

    private HistoricoSalarioDAO() {

	}   
    
    public static HistoricoSalarioDAO getHistoricoDAOInstance() {

        if (INSTANCE == null) {
            INSTANCE = new HistoricoSalarioDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    @Override
    public HistoricoSalario get(BigInteger id){
    	EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		HistoricoSalario retorno = em.find(HistoricoSalario.class, id);
		em.getTransaction().commit();
		return retorno;
    }

    @Override
	public void save(HistoricoSalario historico) {
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		em.merge(historico);
		em.getTransaction().commit();
	}

    @Override
	public void delete(BigInteger id) {
		EntityManager em = Conexao.getInstance().abreTransacao();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<HistoricoSalario> query = criteriaBuilder.createCriteriaDelete(HistoricoSalario.class);
		Root<HistoricoSalario> root = query.from(HistoricoSalario.class);
		query.where(root.get("id").in(Arrays.asList(id)));
		em.getTransaction().begin();
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
	}

    @Override
	public List<HistoricoSalario> getAll(){
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<HistoricoSalario> cq = cb.createQuery(HistoricoSalario.class);
		Root<HistoricoSalario> rootEntry = cq.from(HistoricoSalario.class);
		CriteriaQuery<HistoricoSalario> all = cq.select(rootEntry);
		TypedQuery<HistoricoSalario> allQuery = em.createQuery(all);
		List<HistoricoSalario> retorno = allQuery.getResultList();
		em.getTransaction().commit();
		return retorno;
	}

	public List<HistoricoSalario> getAllFuncionario(BigInteger idFuncionario) {
		EntityManager em = Conexao.getInstance().abreTransacao();// emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNativeQuery(
				"SELECT h.id, h.idFuncionario, h.bonus, h.salarioFinal, h.mes, h.ano FROM HistoricoSalario h WHERE h.idFuncionario = ?",
				HistoricoSalario.class);
		query.setParameter(1, idFuncionario);
		try {
			@SuppressWarnings("unchecked")
			List<HistoricoSalario> retorno = query.getResultList();
			// em.close();
			// emf.close();
			em.getTransaction().commit();
			return retorno;
		} catch (NoResultException nre) {
			String mensagem = nre.getMessage();
			GerenciadorDeLog.getInstance().getLogger().severe(mensagem);
			em.getTransaction().commit();
			;
			return null;
		}
	}
	
	public List<HistoricoSalario> getAllMes(String mes, String ano) {
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		Query query = em.createNativeQuery(
				"SELECT h.id, h.idFuncionario, h.bonus, h.salarioFinal, h.mes, h.ano FROM HistoricoSalario h WHERE h.mes = ? AND h.ano = ?",
				HistoricoSalario.class);
		query.setParameter(1, mes);
		query.setParameter(2, ano);
		try {
			@SuppressWarnings("unchecked")
			List<HistoricoSalario> retorno = query.getResultList();
			// em.close();
			// emf.close();
			em.getTransaction().commit();
			return retorno;
		} catch (NoResultException nre) {
			String mensagem = nre.getMessage();
			GerenciadorDeLog.getInstance().getLogger().severe(mensagem);
			em.getTransaction().commit();
			;
			return null;
		}
	}
   }
    
    

