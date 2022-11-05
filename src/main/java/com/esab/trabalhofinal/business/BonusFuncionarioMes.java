package com.esab.trabalhofinal.business;

import com.esab.trabalhofinal.model.Funcionario;
import java.math.BigDecimal;



/**
 *
 * @author Administrador
 */
public class BonusFuncionarioMes implements CalcularBonus{

    @Override
    public BigDecimal calcular(Funcionario funcionario) {
    	BigDecimal valorBonus = BigDecimal.ZERO;
        valorBonus = funcionario.getSalario().multiply(new BigDecimal("0.30"));
        return valorBonus;
    }
}
