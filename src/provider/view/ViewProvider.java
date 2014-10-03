/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.view;

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import provider.controller.AdminProvider;

/**
 *
 * @author BuiRai
 */
public class ViewProvider extends javax.swing.JFrame {
    
    /**
     * Creates new form ViewProvider
     */
    public ViewProvider() {
        initComponents();
        this.adminProvider = null;
        addTabs();
    }
    
    public ViewProvider(AdminProvider adminProvider) {
        initComponents();
        this.adminProvider = adminProvider;
        addTabs();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administracion de proveedores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ViewProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewProvider().setVisible(true);
            }
        });
    }
    
    private void addTabs(){
        addProvider = new ViewAddProviderPanel(adminProvider);
        
        modifyProviderTable = new ViewModifyProviderTablePanel(adminProvider);
        modifyProvider = new ViewModifyProviderPanel(adminProvider);
        
        modifyProviderTable.setTabs(tabs);
        modifyProviderTable.setSwitchPanel(modifyProvider);
        
        modifyProvider.setTabs(tabs);
        modifyProvider.setSwitchPanel(modifyProviderTable);
        
        removeProvider = new ViewRemoveProviderPanel(adminProvider);
        removeProviderTable = new ViewRemoveProviderTablePanel(adminProvider);
        
        
        tabs.addTab("Agregar proveedor",addProvider);
        tabs.setMnemonicAt(0, KeyEvent.VK_1);
        tabs.addTab("Modificar proveedor",modifyProviderTable);
        tabs.addTab("Eliminar proveedor",removeProviderTable);
        pack();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables

    private AdminProvider adminProvider;
    private JPanel addProvider;
    private ViewModifyProviderTablePanel modifyProviderTable;
    private ViewModifyProviderPanel modifyProvider;
    private ViewRemoveProviderTablePanel removeProviderTable;
    private ViewRemoveProviderPanel removeProvider;
}
