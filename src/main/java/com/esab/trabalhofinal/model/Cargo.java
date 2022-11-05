package com.esab.trabalhofinal.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
@Table(name = "cargos")
public class Cargo {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
	
	@Column(nullable = false,name = "nome")
    private String nome;

    public Cargo(String nome) {
        this.nome = nome;
    }
    
    
    
}
