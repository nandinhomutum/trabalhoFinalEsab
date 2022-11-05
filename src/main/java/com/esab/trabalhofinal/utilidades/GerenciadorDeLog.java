package com.esab.trabalhofinal.utilidades;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import lombok.Getter;

@Getter
public class GerenciadorDeLog {    
    private Logger logger = Logger.getLogger("Log");  
    private FileHandler fileHandler;
    
	private GerenciadorDeLog() {
		connectaArquivoDeLog();
	}
    
    public static GerenciadorDeLog getInstance() {
        return GerenciadorDeLogHolder.INSTANCE;
    }
    
    private static class GerenciadorDeLogHolder {

        private static final GerenciadorDeLog INSTANCE = new GerenciadorDeLog();
    }
    
    private void connectaArquivoDeLog() {
    	try {
			fileHandler = new FileHandler("src/main/resources/Log.txt", 8096, 1, true);
			logger.addHandler(fileHandler);
			fileHandler.setLevel(Level.FINE);
	        logger.setLevel(Level.FINE);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public void abrirArquivoDeLog() {
		try {
			File log = new File("src/main/resources/Log.txt");
			Desktop.getDesktop().edit(log);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
