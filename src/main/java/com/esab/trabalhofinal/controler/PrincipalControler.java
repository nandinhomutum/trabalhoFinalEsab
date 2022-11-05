/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esab.trabalhofinal.controler;

import com.esab.trabalhofinal.utilidades.GerenciadorDeLog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.esab.trabalhofinal.view.TelaPrincipalView;

/**
 *
 * @author nandi
 */
public class PrincipalControler {

    private TelaPrincipalView view;

    public PrincipalControler() {
        configurarTela();   
    }
    

    public void abrirManterFuncionario() { 
        new ManterFuncionarioControler();
        this.sair();
    }

    public void abreBuscarFuncionario() {
        new BuscarFuncionarioControler();
        this.sair();
    }

    public void abreSalario() {
    	new SalarioControler();
        this.sair();
    }
    
    public void abreCargos(){
        new CargosControler();
        this.sair();
    }
    
    public void sair(){
        this.view.dispose();
    }

    private void configurarTela() {
        this.view = new TelaPrincipalView();
        
        view.getMenuManterFuncionario().addActionListener(new ActionListener() {           
            @Override
           public void actionPerformed(ActionEvent ae) {
                    abrirManterFuncionario();
            } 
        });
        
        view.getMenuBuscarFuncionario().addActionListener(new ActionListener() {           
            @Override
           public void actionPerformed(ActionEvent ae) {
                    abreBuscarFuncionario();
            } 
        }); 
        
        view.getMenuCalcularSalario().addActionListener(new ActionListener() {           
            @Override
           public void actionPerformed(ActionEvent ae) {
                    abreSalario();
            } 
        }); 
        
        view.getMenuCargos().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				abreCargos();				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});  
        
        view.getMenuSair().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sair();				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		}); 
        
        view.getMenuLog().addActionListener(new ActionListener() {           
            @Override
           public void actionPerformed(ActionEvent ae) {
                    abrirLog();
            } 
        });
        
        this.view.setVisible(true);
    }
    private void abrirLog() {
    	GerenciadorDeLog.getInstance().abrirArquivoDeLog();
    }

}
