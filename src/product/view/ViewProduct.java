/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package product.view;

import java.awt.event.KeyEvent;
import product.controller.AdminProduct;

/**
 *
 * @author BuiRai
 */
public class ViewProduct extends javax.swing.JFrame {
    
    /**
     * Creates new form ViewProvider
     */
    public ViewProduct() {
        initComponents();
        this.adminProduct = null;
        addTabs();
    }
    
    public ViewProduct(AdminProduct adminProduct) {
        initComponents();
        this.adminProduct = adminProduct;
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
        setBackground(new java.awt.Color(255, 255, 255));

        tabs.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabs.setPreferredSize(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(ViewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewProduct().setVisible(true);
            }
        });
    }
    
    private void addTabs(){
        addProduct = new ViewAddProductPanel(adminProduct);
        
        modifyProductTable = new ViewModifyProductTablePanel(adminProduct);
        modifyProduct = new ViewModifyProductPanel(adminProduct);
        
        modifyProductTable.setTabs(tabs);
        modifyProductTable.setSwitchPanel(modifyProduct);
        modifyProductTable.setName("Modificar producto");
        
        modifyProduct.setTabs(tabs);
        modifyProduct.setSwitchPanel(modifyProductTable);
        modifyProduct.setName("Modificar producto");
        
        removeProductTable = new ViewRemoveProductTablePanel(adminProduct);
        removeProduct = new ViewRemoveProductPanel(adminProduct);
        
        removeProductTable.setTabs(tabs);
        removeProductTable.setSwitchPanel(removeProduct);
        removeProductTable.setName("Eliminar producto");
        
        removeProduct.setTabs(tabs);
        removeProduct.setSwitchPanel(removeProductTable);
        removeProduct.setName("Eliminar producto");
                
        tabs.addTab("Agregar producto",addProduct);
        tabs.setMnemonicAt(0, KeyEvent.VK_1);
        tabs.addTab("Modificar producto",modifyProductTable);
        tabs.addTab("Eliminar producto",removeProductTable);
        pack();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables

    private AdminProduct adminProduct;
    private ViewAddProductPanel addProduct;
    private ViewModifyProductTablePanel modifyProductTable;
    private ViewModifyProductPanel modifyProduct;
    private ViewRemoveProductTablePanel removeProductTable;
    private ViewRemoveProductPanel removeProduct;
}
