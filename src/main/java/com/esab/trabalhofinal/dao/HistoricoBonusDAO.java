/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esab.trabalhofinal.dao;

import com.esab.trabalhofinal.model.HistoricoBonus;
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
 * @author Administrador
 */
public class HistoricoBonusDAO implements DAOInterface<HistoricoBonus>{
    private static HistoricoBonusDAO INSTANCE;
    
    private HistoricoBonusDAO() {

	}   
    
    public static HistoricoBonusDAO getHistoricoDAOInstance() {

        if (INSTANCE == null) {
            INSTANCE = new HistoricoBonusDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
    
    @Override
    public HistoricoBonus get(BigInteger id){
    	EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		HistoricoBonus retorno = em.find(HistoricoBonus.class, id);
		em.getTransaction().commit();
		return retorno;
    }

    @Override
	public void save(HistoricoBonus historico) {
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		em.merge(historico);
		em.getTransaction().commit();
	}

    @Override
	public void delete(BigInteger id) {
		EntityManager em = Conexao.getInstance().abreTransacao();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<HistoricoBonus> query = criteriaBuilder.createCriteriaDelete(HistoricoBonus.class);
		Root<HistoricoBonus> root = query.from(HistoricoBonus.class);
		query.where(root.get("id").in(Arrays.asList(id)));
		em.getTransaction().begin();
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
	}

    @Override
	public List<HistoricoBonus> getAll(){
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<HistoricoBonus> cq = cb.createQuery(HistoricoBonus.class);
		Root<HistoricoBonus> rootEntry = cq.from(HistoricoBonus.class);
		CriteriaQuery<HistoricoBonus> all = cq.select(rootEntry);
		TypedQuery<HistoricoBonus> allQuery = em.createQuery(all);
		List<HistoricoBonus> retorno = allQuery.getResultList();
		em.getTransaction().commit();
		return retorno;
	}
    
    public List<HistoricoBonus> getAllFuncionario(String nomeFuncionario) {
		EntityManager em = Conexao.getInstance().abreTransacao();
		em.getTransaction().begin();
		Query query = em.createNativeQuery(
				"SELECT h.id, h.nome, h.tipoBonus, h.valorBonus, h.mes, h.ano FROM HistoricoBonus h WHERE h.nome = ?",
				HistoricoBonus.class);
		query.setParameter(1, nomeFuncionario);
		try {
			@SuppressWarnings("unchecked")
			List<HistoricoBonus> retorno = query.getResultList();
			
			em.getTransaction().commit();
			return retorno;
		} catch (NoResultException nre) {
			String mensagem = nre.getMessage();
			GerenciadorDeLog.getInstance().getLogger().severe(mensagem);
			em.getTransaction().commit();
			
			return null;
		}
	}
}
  
