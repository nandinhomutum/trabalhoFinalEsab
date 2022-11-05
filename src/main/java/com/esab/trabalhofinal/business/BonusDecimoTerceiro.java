package com.esab.trabalhofinal.business;

import com.esab.trabalhofinal.model.Funcionario;
import java.math.BigDecimal;



public class BonusDecimoTerceiro implements CalcularBonus{

    @Override
    public BigDecimal calcular(Funcionario funcionario) {
        BigDecimal valorBonus = BigDecimal.ZERO;
        valorBonus = funcionario.getSalario();
        return valorBonus;
    }

    public BigDecimal calcular(Object funcionario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
