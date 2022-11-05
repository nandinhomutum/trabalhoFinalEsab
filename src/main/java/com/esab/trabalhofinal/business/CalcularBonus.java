package com.esab.trabalhofinal.business;


import com.esab.trabalhofinal.model.Funcionario;
import java.math.BigDecimal;



/**
 *
 * @author Administrador
 */
public interface CalcularBonus {
    
    public BigDecimal calcular(Funcionario funcionario);
    
}
