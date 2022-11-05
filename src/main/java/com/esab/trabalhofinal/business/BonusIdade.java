/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esab.trabalhofinal.business;

import java.math.BigDecimal;

import com.esab.trabalhofinal.model.Funcionario;

/**
 *
 * @author Administrador
 */
public class BonusIdade implements CalcularBonus{

    @Override
    public BigDecimal calcular(Funcionario funcionario) {
    	
    	BigDecimal valorBonus = BigDecimal.ZERO;
        if ( funcionario.getIdade().intValue() <= 25){
          valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.05"));  
        } else if ( funcionario.getIdade().intValue() <= 50){
          valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.1"));  
        } else if ( funcionario.getIdade().intValue() > 50){
          valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.15"));  
        }
        
        return valorBonus;
   }
}
