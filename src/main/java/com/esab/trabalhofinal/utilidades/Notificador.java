package com.esab.trabalhofinal.utilidades;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Notificador {
	
	private Notificador() {
    }
    
    public static Notificador getInstance() {
        return NotificadorHolder.INSTANCE;
    }
    
    private static class NotificadorHolder {

        private static final Notificador INSTANCE = new Notificador();
    }
    
    @SuppressWarnings("static-access")
	public void disparaErro(String menssagem) {
    	new JOptionPane().showMessageDialog(new JFrame(),menssagem,"Erro",JOptionPane.ERROR_MESSAGE);
    }
    
    @SuppressWarnings("static-access")
	public void disparaAviso(String menssagem) {
    	new JOptionPane().showMessageDialog(new JFrame(),menssagem,"Aviso",JOptionPane.WARNING_MESSAGE);
    }
    
    @SuppressWarnings("static-access")
	public void disparaInfo(String menssagem) {
    	new JOptionPane().showMessageDialog(new JFrame(),menssagem,"Aviso",JOptionPane.INFORMATION_MESSAGE);
    }

}
