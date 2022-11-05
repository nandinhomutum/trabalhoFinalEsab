package com.esab.trabalhofinal.business;


import com.esab.trabalhofinal.model.Funcionario;
import java.math.BigDecimal;



/**
 *
 * @author Administrador
 */
public class BonusDistancia implements CalcularBonus{

    @Override
    public BigDecimal calcular(Funcionario funcionario) {
        BigDecimal valorBonus = BigDecimal.ZERO ;
        if ( funcionario.getKm().intValue() <= 10){
          valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.05"));  
        } else if ( funcionario.getKm().intValue() <= 50){
          valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.1"));  
        } else if ( funcionario.getKm().intValue() > 50){
          valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.15"));  
        }
        
        return valorBonus;
   }
}
