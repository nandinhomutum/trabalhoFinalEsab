/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esab.trabalhofinal.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.esab.trabalhofinal.business.FuncionarioBusiness;
import com.esab.trabalhofinal.dao.FuncionarioDAO;
import com.esab.trabalhofinal.model.Funcionario;
import com.esab.trabalhofinal.utilidades.Notificador;
import com.esab.trabalhofinal.view.TelaBuscarFuncionarioView;

/**
 *
 * @author nandi
 */
public class BuscarFuncionarioControler {
    
    private TelaBuscarFuncionarioView view;
    private FuncionarioBusiness business;
    
    public BuscarFuncionarioControler(){
        this.business = FuncionarioBusiness.getInstance();
        configurarTela();
    }
      
      public void abrirManterFuncionario() {
  		new ManterFuncionarioControler();
                this.view.dispose();
    }
        
    private void configurarTela(){
        this.view = new TelaBuscarFuncionarioView();
        criarEventListeners();
        PreencherTabela();
        view.setVisible(true);
    }
    
    
	private void criarEventListeners() {
		view.getBtnFechar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				sair();
			}
		});

		view.getBtnBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				buscarFuncionariosPorNome();
			}
		});
		
		view.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				abrirManterFuncionario();
			}
		});
		
		view.getEditarButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				alterar();
			}
		});
		
		view.getBtnExcluir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				excluir();
			}
		});
		
		view.getBtnAdicionar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				abrirAdicionarBonus();
			}
		});
		
		view.getBtnBonus().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				abrirBonusFuncionario();
			}
		});
	}

	public void PreencherTabela(){
        DefaultTableModel tabela = (DefaultTableModel) view.getTbFuncionarios().getModel();
     
      List<Funcionario> listaFuncionarios = FuncionarioBusiness.getInstance().getAll();
      for (Funcionario funcionario: listaFuncionarios){
          tabela.addRow(new Object[]{ funcionario.getId(),
              funcionario.getNome(),
              funcionario.getIdade(),
              funcionario.getCargo().getNome(),
              funcionario.getSalario()
          });
         
      }
       view.getTbFuncionarios().setModel(tabela);
    }
    
    public void buscarFuncionariosPorNome() {
    	String nome = this.view.getTxtNome().getText();
    	List<Funcionario> listaFuncionarios = business.getLikeName(nome);
    	DefaultTableModel tabela = (DefaultTableModel) view.getTbFuncionarios().getModel();
    	limpaTabela(tabela);
        for (Funcionario funcionario: listaFuncionarios){
            tabela.addRow(new Object[]{ funcionario.getId(),
                funcionario.getNome(),
                funcionario.getIdade(),
                funcionario.getCargo().getNome(),
                funcionario.getSalario()
            });
           
        }
        view.getTbFuncionarios().setModel(tabela);
    }
    
    public Funcionario pegarFuncionario(){
        //DefaultTableModel tabela = (DefaultTableModel) view.getTbFuncionarios().getModel();
        int linha = view.getTbFuncionarios().getSelectedRow();
        BigInteger id = new BigInteger(view.getTbFuncionarios().getValueAt(linha, 0).toString());
       return FuncionarioDAO.getFuncionarioDAOInstance().get(id);        
    }
    
    public void alterar(){
	    int linhaSelecionada = this.view.getTbFuncionarios().getSelectedRow();
	    if(linhaSelecionada > -1){
	      BigInteger id = new BigInteger(this.view.getTbFuncionarios().getModel().getValueAt(linhaSelecionada, 0).toString());
	      String nomeCargo = this.view.getTbFuncionarios().getModel().getValueAt(linhaSelecionada, 3).toString();
	      new ManterFuncionarioControler(id,nomeCargo);
	      this.view.dispose();
	    }else{
	        Notificador.getInstance().disparaAviso("Selecione um funcionário para editar");
	    }
	}

	public void excluir(){
	    int linhaSelecionada = this.view.getTbFuncionarios().getSelectedRow();
	    if(linhaSelecionada > -1){
	      BigInteger id = new BigInteger(this.view.getTbFuncionarios().getModel().getValueAt(linhaSelecionada, 0).toString());
	      business.delete(id);
	      this.view.dispose();
	      configurarTela();
	    }else{
	        Notificador.getInstance().disparaAviso("Selecione um funcionário para excluir");
	    }
	}

	private void limpaTabela(DefaultTableModel tabela) {
    	int rowCount = tabela.getRowCount();
    	for (int i = rowCount - 1; i >= 0; i--) {
    		tabela.removeRow(i);
    	}
    }
	
	private void abrirAdicionarBonus() {
		int linhaSelecionada = this.view.getTbFuncionarios().getSelectedRow();
		if (linhaSelecionada > -1) {
			BigInteger id = new BigInteger(
					this.view.getTbFuncionarios().getModel().getValueAt(linhaSelecionada, 0).toString());
			Funcionario funcionario = business.get(id);
			new AdicionarBonusControler(funcionario);
			view.dispose();
		} else {
			Notificador.getInstance().disparaAviso("Selecione um funcionário");
		}
	}
	
	private void abrirBonusFuncionario() {
		int linhaSelecionada = this.view.getTbFuncionarios().getSelectedRow();
		if (linhaSelecionada > -1) {
			BigInteger id = new BigInteger(
					this.view.getTbFuncionarios().getModel().getValueAt(linhaSelecionada, 0).toString());
			Funcionario funcionario = business.get(id);
			new FuncionarioHistoricoControler(funcionario);
		} else {
			Notificador.getInstance().disparaAviso("Selecione um funcionário");
		}
		view.dispose();
	}
    
    public void sair() {
		new PrincipalControler();
		this.view.dispose();
    }
}
