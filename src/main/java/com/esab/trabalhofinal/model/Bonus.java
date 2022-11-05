
package com.esab.trabalhofinal.model;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nandi
 */
@Getter
@Setter
public abstract class Bonus implements IBonus{
	private BigDecimal percentual;
	private BigDecimal valor;
}
   
