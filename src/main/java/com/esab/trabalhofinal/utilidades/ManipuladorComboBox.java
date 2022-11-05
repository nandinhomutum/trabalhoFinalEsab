/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esab.trabalhofinal.utilidades;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author nandi
 */
public class ManipuladorComboBox {
    
    private ManipuladorComboBox() {
    }
    
    public static ManipuladorComboBox getInstance() {
        return ManipuladorComboBoxHolder.INSTANCE;
    }
    
    private static class ManipuladorComboBoxHolder {

        private static final ManipuladorComboBox INSTANCE = new ManipuladorComboBox();
    }
    
    public void removeItens(JComboBox<String> jcb){
            jcb.removeAllItems();                    
    }
    
    public void preencherComboBox(List<String> itens, JComboBox<String> jcb){
            jcb.removeAllItems();
            for(String item : itens){
                jcb.addItem(item);
            }
    }
}
