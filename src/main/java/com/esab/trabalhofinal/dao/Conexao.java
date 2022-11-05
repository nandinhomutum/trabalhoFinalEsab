/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esab.trabalhofinal.dao;

import java.sql.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Conexao {
    

 
  private EntityManagerFactory emf;
  private EntityManager em;

    private Conexao() {
    	    	
    }

    public static Conexao getInstance() {
        return NewSingletonHolder.INSTANCE;
    }

    private static class NewSingletonHolder {

        private static final Conexao INSTANCE = new Conexao();
    }
   
    
    @SuppressWarnings("static-access")
	public EntityManager abreTransacao() {
    	if(null != em) {
    		if(em.isJoinedToTransaction()) {
    			em.close();
    			emf.close();
    			emf = new Persistence().createEntityManagerFactory("persistenceUnit");
        		em = emf.createEntityManager();        		
    		}
    		return em;
    	} else {
			emf = new Persistence().createEntityManagerFactory("persistenceUnit");
    		em = emf.createEntityManager();
    		return em;
    	}
    }
    
   
}