package com.esab.trabalhofinal.view;


import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class TelaPrincipalView extends javax.swing.JFrame {
    /**
     * Creates new form TelaPrincipalView
     */
    public TelaPrincipalView() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
                
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFuncionario = new javax.swing.JMenu();
        menuManterFuncionario = new javax.swing.JMenuItem();
        menuBuscarFuncionario = new javax.swing.JMenuItem();
        menuCargos = new javax.swing.JMenu();
        menuSalario = new javax.swing.JMenu();
        menuCalcularSalario = new javax.swing.JMenuItem();
        menuFerramentas = new javax.swing.JMenu();
        menuLog = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de gest??o de pessoas");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        menuFuncionario.setText("Funcion??rio");
      

        menuManterFuncionario.setText("Manter Funcionario");
        
        menuFuncionario.add(menuManterFuncionario);

        menuBuscarFuncionario.setText("Buscar Funcionario");
        
        menuFuncionario.add(menuBuscarFuncionario);

        jMenuBar1.add(menuFuncionario);

        menuCargos.setText("Cargos");
        
        jMenuBar1.add(menuCargos);

        menuSalario.setText("Sal??rio");

        menuCalcularSalario.setText("Calcular Sal??rio");
        
        menuSalario.add(menuCalcularSalario);

        jMenuBar1.add(menuSalario);

        menuFerramentas.setText("Ferramentas");

        menuLog.setText("Log");
        menuFerramentas.add(menuLog);

        jMenuBar1.add(menuFerramentas);

        menuSair.setText("Sair");
        
        
        jMenuBar1.add(menuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuBuscarFuncionario;
    private javax.swing.JMenuItem menuCalcularSalario;
    private javax.swing.JMenu menuCargos;
    private javax.swing.JMenu menuFerramentas;
    private javax.swing.JMenu menuFuncionario;
    private javax.swing.JMenuItem menuLog;
    private javax.swing.JMenuItem menuManterFuncionario;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuSalario;
    // End of variables declaration//GEN-END:variables

   
}