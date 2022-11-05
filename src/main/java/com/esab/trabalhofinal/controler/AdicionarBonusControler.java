/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esab.trabalhofinal.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.esab.trabalhofinal.business.BonusDecimoTerceiro;
import com.esab.trabalhofinal.business.BonusDistancia;
import com.esab.trabalhofinal.business.BonusFalta;
import com.esab.trabalhofinal.business.BonusFuncionarioMes;
import com.esab.trabalhofinal.business.BonusGeneroso;
import com.esab.trabalhofinal.business.BonusIdade;
import com.esab.trabalhofinal.business.BonusNormal;
import com.esab.trabalhofinal.business.BonusTempo;
import com.esab.trabalhofinal.business.CalcularBonus;
import com.esab.trabalhofinal.dao.HistoricoBonusDAO;
import com.esab.trabalhofinal.dao.HistoricoSalarioDAO;
import com.esab.trabalhofinal.model.Funcionario;
import com.esab.trabalhofinal.model.HistoricoBonus;
import com.esab.trabalhofinal.model.HistoricoSalario;
import com.esab.trabalhofinal.utilidades.GerenciadorDeLog;
import com.esab.trabalhofinal.utilidades.Notificador;
import com.esab.trabalhofinal.view.TelaFuncionarioBonusView;

/**
 *
 * @author nandi
 */
public class AdicionarBonusControler {

    private TelaFuncionarioBonusView view;
    private final Funcionario funcionario;

    public AdicionarBonusControler(Funcionario funcionario) {
        configurarTela();
        this.funcionario = funcionario;
        preencherTela(funcionario);
    }
        
	private void salvar() {
		String tipoBonus;
		BigDecimal valorBonus;
		BigDecimal bonusAcumulado = BigDecimal.ZERO;
		BigInteger mes = new BigInteger(view.getCbMes().getSelectedItem().toString());
		BigInteger ano = new BigInteger(view.getJcbAno().getSelectedItem().toString());
		if (view.getCkDecimo().isSelected() == true) {
			tipoBonus = "BONUS 13 SALARIO";
			valorBonus = BigDecimal.ZERO;
			CalcularBonus bonus = new BonusDecimoTerceiro();
			valorBonus = bonus.calcular(funcionario);
			bonusAcumulado = bonusAcumulado.add(valorBonus);
			HistoricoBonus historico = new HistoricoBonus(funcionario.getNome(), tipoBonus, valorBonus, mes, ano);
			HistoricoBonusDAO.getHistoricoDAOInstance().save(historico);
                        gerarLog(tipoBonus);
		}

		if (view.getCkDistancia().isSelected() == true) {

			tipoBonus = "BONUS DISTANCIA";
			valorBonus = BigDecimal.ZERO;
			CalcularBonus bonus = new BonusDistancia();
			valorBonus = bonus.calcular(funcionario);
			bonusAcumulado = bonusAcumulado.add(valorBonus);
			HistoricoBonus historico = new HistoricoBonus(funcionario.getNome(), tipoBonus, valorBonus, mes, ano);
			HistoricoBonusDAO.getHistoricoDAOInstance().save(historico);
                        gerarLog(tipoBonus);
		}

		if (view.getJckIdade().isSelected() == true) {

			tipoBonus = "BONUS IDADE";
			valorBonus = BigDecimal.ZERO;
			CalcularBonus bonus = new BonusIdade();
			valorBonus = bonus.calcular(funcionario);
			bonusAcumulado = bonusAcumulado.add(valorBonus);
			HistoricoBonus historico = new HistoricoBonus(funcionario.getNome(), tipoBonus, valorBonus, mes, ano);
			HistoricoBonusDAO.getHistoricoDAOInstance().save(historico);
                        gerarLog(tipoBonus);
                }

		if (view.getCkFuncionarioMes().isSelected() == true) {

			tipoBonus = "BONUS FUNCIONARIO MES";
			valorBonus = BigDecimal.ZERO;
			CalcularBonus bonus = new BonusFuncionarioMes();
			valorBonus = bonus.calcular(funcionario);
			bonusAcumulado = bonusAcumulado.add(valorBonus);
			HistoricoBonus historico = new HistoricoBonus(funcionario.getNome(), tipoBonus, valorBonus, mes, ano);
			HistoricoBonusDAO.getHistoricoDAOInstance().save(historico);
                        gerarLog(tipoBonus);
                }
                
                if (view.getJckbTempo().isSelected() == true) {

			tipoBonus = "BONUS TEMPO DE SERVIÇO";
			valorBonus = BigDecimal.ZERO;
			CalcularBonus bonus = new BonusTempo();
			valorBonus = bonus.calcular(funcionario);
			bonusAcumulado = bonusAcumulado.add(valorBonus);
			HistoricoBonus historico = new HistoricoBonus(funcionario.getNome(), tipoBonus, valorBonus, mes, ano);
			HistoricoBonusDAO.getHistoricoDAOInstance().save(historico);
                        gerarLog(tipoBonus);
                }
                

		if (view.getjCBBonus().toString().equals("NORMAL")) {

			tipoBonus = "BONUS NORMAL";
			valorBonus = BigDecimal.ZERO;

			CalcularBonus bonus = new BonusNormal();
			valorBonus = bonus.calcular(funcionario);
			bonusAcumulado = bonusAcumulado.add(valorBonus);
			HistoricoBonus historico = new HistoricoBonus(funcionario.getNome(), tipoBonus, valorBonus, mes, ano);
			HistoricoBonusDAO.getHistoricoDAOInstance().save(historico);
                        gerarLog(tipoBonus);
                } else {

			tipoBonus = "BONUS GENEROSO";
			valorBonus = BigDecimal.ZERO;
			CalcularBonus bonus = new BonusGeneroso();
			valorBonus = bonus.calcular(funcionario);
                        bonusAcumulado = bonusAcumulado.add(valorBonus);
			HistoricoBonus historico = new HistoricoBonus(funcionario.getNome(), tipoBonus, valorBonus, mes, ano);
			HistoricoBonusDAO.getHistoricoDAOInstance().save(historico);
                        gerarLog(tipoBonus);
		}
		BigInteger numFaltas = new BigInteger(view.getTxtFaltas().getText());
		funcionario.setFaltas(numFaltas);
		tipoBonus = "BONUS POR FALTA";
		valorBonus = BigDecimal.ZERO;
		CalcularBonus bonus = new BonusFalta();
		System.out.println("faltas: " + funcionario.getFaltas());
		valorBonus = bonus.calcular(funcionario);
		bonusAcumulado = bonusAcumulado.add(valorBonus);
                gerarLog(tipoBonus);
		HistoricoBonus historico = new HistoricoBonus(funcionario.getNome(), tipoBonus, valorBonus, mes, ano);
		HistoricoBonusDAO.getHistoricoDAOInstance().save(historico);
                gerarLog(tipoBonus);

		BigDecimal salario = BigDecimal.ZERO;

		salario = funcionario.getSalario().add(bonusAcumulado);
		HistoricoSalario historicoSalario = new HistoricoSalario(funcionario, bonusAcumulado, salario, mes,
				ano);
		HistoricoSalarioDAO.getHistoricoDAOInstance().save(historicoSalario);
                String mensagem = "Calculado o salario do funcionario: " + funcionario.getNome();
                Notificador.getInstance().disparaInfo(mensagem);
                 GerenciadorDeLog.getInstance().getLogger().fine(mensagem);
	}
    
    private void criarEventListeners() {
    	view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
          public void actionPerformed(ActionEvent ae) {
                   sair();
            } 
        });
    	
		view.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				salvar();
			}
		});
	}

	private void configurarTela(){
        this.view = new TelaFuncionarioBonusView();
        criarEventListeners();
        view.setVisible(true);
    }

    private void preencherTela(Funcionario funcionario) {        
        view.getLblNome().setText(funcionario.getNome());
        view.getLblCargo().setText(funcionario.getNome());
       view.getLblMes().setText(funcionario.getMes());
       view.getLblAno().setText(funcionario.getAno());
        view.getLblCargo().setText(funcionario.getCargo().getNome());
        view.getLbId().setText("funcionario.getId()");
    }

	public void sair() {
		new PrincipalControler();
		this.view.dispose();
	}
        
    private void gerarLog(String tipoBonus){
        String mensagem = "Funcionario " + funcionario.getNome() + " recebeu o bonus " +tipoBonus;
       GerenciadorDeLog.getInstance().getLogger().fine(mensagem);
        
    }
            
    
}
