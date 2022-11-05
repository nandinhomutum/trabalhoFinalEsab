/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esab.trabalhofinal.business;

import com.esab.trabalhofinal.model.Funcionario;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 *
 * @author Administrador
 */
public class BonusTempo implements CalcularBonus{

    @Override
    public BigDecimal calcular(Funcionario funcionario) {
        BigDecimal valorBonus = BigDecimal.ZERO;
        int mesAtual = LocalDate.now().getMonthValue();
        int anoAtual = LocalDate.now().getYear();
       
       int mesFuncionario = Integer.parseInt(funcionario.getMes());
       int anoFuncionario = Integer.parseInt(funcionario.getAno());
       
       int anocalculado;   
       
           anocalculado = anoAtual - anoFuncionario;
       
       if( anocalculado < 1 ){
          valorBonus = BigDecimal.ZERO;
       } else if( anocalculado >= 1 && anocalculado < 6 ){
           
           int mestrabalhados = mesAtual - mesFuncionario;
           
           if( mestrabalhados < 0){
                valorBonus = BigDecimal.ZERO;
           }else {
                 valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.02"));
           }
       } else if( anocalculado >= 6 && anocalculado < 11 ){
           
           int mestrabalhados = mesAtual - mesFuncionario;
           
           if( mestrabalhados < 0){
                valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.02"));
           }else {
                 valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.03"));
           } 
       }else if( anocalculado >= 11 && anocalculado < 16 ){
           
           int mestrabalhados = mesAtual - mesFuncionario;
           
           if( mestrabalhados < 0){
                valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.03"));
           }else {
                 valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.08"));
           }
       } else if( anocalculado >= 16 && anocalculado < 20 ){
           
           int mestrabalhados = mesAtual - mesFuncionario;
           
           if( mestrabalhados < 0){
                valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.08"));
           }else {
                 valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.10"));
           }
       }else {
         valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.15"));
       }
       
       
       
        return valorBonus;
       
    }
    
}
