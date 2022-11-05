/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esab.trabalhofinal.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.esab.trabalhofinal.dao.HistoricoBonusDAO;
import com.esab.trabalhofinal.dao.HistoricoSalarioDAO;
import com.esab.trabalhofinal.model.Funcionario;
import com.esab.trabalhofinal.model.HistoricoBonus;
import com.esab.trabalhofinal.model.HistoricoSalario;
import com.esab.trabalhofinal.utilidades.GerenciadorDeLog;
import com.esab.trabalhofinal.view.TelaHistoricoFuncionariosView;

/**
 *
 * @author Administrador
 */
public class FuncionarioHistoricoControler {
    
    private TelaHistoricoFuncionariosView view;
    private final Funcionario funcionario;
    
    public FuncionarioHistoricoControler(Funcionario funcionario){
        configurarTela();
        this.funcionario = funcionario;
        String msg = "Bônus consultado para o funcionário "+ funcionario.getNome();
         GerenciadorDeLog.getInstance().getLogger().fine(msg);
        preencherTela(funcionario);
        PreencherTabelaBonus();
        PreencherTabelaSalario();        
    }
    
     private void configurarTela(){
        this.view = new TelaHistoricoFuncionariosView();
        criarEventListeners();
        view.setVisible(true);
    }

    private void preencherTela(Funcionario funcionario) {
        view.getJlbNome().setText(funcionario.getNome());
        view.getJlbCargo().setText(funcionario.getCargo().getNome());
        view.getjLBIdade().setText(String.valueOf(funcionario.getIdade()));
        view.getJlbSalario().setText(String.valueOf(funcionario.getSalario()));
    }
    
    public void PreencherTabelaBonus(){
      String nomeBuscado = funcionario.getNome();
      DefaultTableModel tabela = new DefaultTableModel();
      tabela.addColumn("BONUS");
      tabela.addColumn("VALOR RECEBIDO");
      tabela.addColumn("MES");
      tabela.addColumn("ANO");
      
      List<HistoricoBonus> listaBonus = HistoricoBonusDAO.getHistoricoDAOInstance().getAllFuncionario(nomeBuscado);
      for (HistoricoBonus bonus: listaBonus){
          tabela.addRow(new Object[]{ bonus.getTipoBonus(),
              bonus.getValorBonus(),
              bonus.getMes(),
              bonus.getAno()
          });
         
      }
       view.getjTBonus().setModel(tabela);
    }

    private void PreencherTabelaSalario(){
        BigInteger idFuncionario = funcionario.getId();
      DefaultTableModel tabela1 = new DefaultTableModel();
      tabela1.addColumn("BONUS");
      tabela1.addColumn("VALOR RECEBIDO");
      tabela1.addColumn("MES");
      tabela1.addColumn("ANO");
      
      List<HistoricoSalario> listaSalario = HistoricoSalarioDAO.getHistoricoDAOInstance().getAllFuncionario(idFuncionario);
      for (HistoricoSalario salario: listaSalario){
          tabela1.addRow(new Object[]{ salario.getBonus(),
              salario.getSalarioFinal(),
              salario.getMes(),
              salario.getAno()
          });
         
      }
       view.getjTSalario().setModel(tabela1);
    }

	private void criarEventListeners() {
		view.getJbSair().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				sair();
			}
		});
	}

	public void sair() {
		new PrincipalControler();
		this.view.dispose();
	}

}
