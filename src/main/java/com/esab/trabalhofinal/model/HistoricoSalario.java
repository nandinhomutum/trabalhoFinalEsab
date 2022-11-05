package com.esab.trabalhofinal.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class HistoricoSalario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
	
	@OneToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "idFuncionario", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT))
    private Funcionario funcionario;
	
	@Column
    private BigDecimal bonus;
	
	@Column			
    private BigDecimal salarioFinal;
	
	@Column
    private BigInteger mes;
	
	@Column
    private BigInteger ano;

    public HistoricoSalario(Funcionario funcionario, BigDecimal bonus, BigDecimal salarioFinal, BigInteger mes, BigInteger ano) {
        this.funcionario = funcionario;
        this.bonus = bonus;
        this.salarioFinal = salarioFinal;
        this.mes = mes;
        this.ano = ano;
    }
    

 
    
    
}
