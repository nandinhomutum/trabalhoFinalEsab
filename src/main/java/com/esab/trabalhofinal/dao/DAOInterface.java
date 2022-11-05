/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esab.trabalhofinal.dao;

import java.math.BigInteger;
import java.util.List;


public interface DAOInterface<T> {       

    public T get(BigInteger id);
	
    public void save(T obj);
	
    //public void update(T obj);

    public void delete(BigInteger id);

    public List<T> getAll();
}