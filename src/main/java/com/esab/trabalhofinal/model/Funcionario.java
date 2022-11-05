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

/**
 *
 * @author nandi
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
	
	@Column(nullable = false,name = "nome")
    private String nome;	
	
	@Column(name = "idade")
    private BigInteger idade;
    
	@Column
    private BigDecimal salario;
    
	@OneToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "idCargo", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT))
    private Cargo cargo;
	
	@Column
	private BigInteger km;
    
//	@ManyToOne
//    private List<Bonus> listaBonus;
    
    @Column
    private String mes;
    
//    @OneToOne
//    private Bonus bonus;
    
    @Column
    private BigInteger faltas;
    
    @Column
    private String ano;

}
