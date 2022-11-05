package com.esab.trabalhofinal.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Administrador
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class HistoricoBonus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
	@Column
    private String nome;
	@Column
    private String tipoBonus;
	@Column
    private BigDecimal valorBonus;
	@Column
    private BigInteger mes;
	@Column
    private BigInteger ano;

    public HistoricoBonus( String nome, String tipoBonus, BigDecimal valorBonus, BigInteger mes, BigInteger ano) {
        this.nome = nome;
        this.tipoBonus = tipoBonus;
        this.valorBonus = valorBonus;
        this.mes = mes;
        this.ano = ano;
    }
}
