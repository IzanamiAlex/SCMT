/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.services;

import controller.AdminService;

/**
 *
 * @author BuiRai
 */
public class ViewService extends javax.swing.JFrame {

    /**
     * Creates new form ViewService
     */
    public ViewService() {
        initComponents();
        this.adminService = new AdminService();
        addTabs();
    }

    public ViewService(AdminService adminService) {
        initComponents();
        this.adminService = adminService;
        addTabs();
    }
    
    private void addTabs(){
        addService = new ViewAddService(adminService);
        
        modifyServiceTable = new ViewServiceSelectModifyPanel(adminService);
        modifyService = new ViewModifyServicePanel(adminService);
        
        modifyServiceTable.setTabs(tabs);
        modifyServiceTable.setSwitchPanel(modifyService);
        modifyServiceTable.setName("Modificar servicio");
        
        modifyService.setTabs(tabs);
        modifyService.setSwitchPanel(modifyServiceTable);
        modifyService.setName("Modificar servicio");
        
        removeServiceTable = new ViewServiceSelectRemovePanel(adminService);
        removeService = new ViewRemoveServicePanel(adminService);
        
        removeServiceTable.setTabs(tabs);
        removeServiceTable.setSwitchPanel(removeService);
        removeServiceTable.setName("Eliminar servicio");
        
        removeService.setTabs(tabs);
        removeService.setSwitchPanel(removeServiceTable);
        removeService.setName("Eliminar servicio");
        
        servicesUnperformed = new ViewServicesUnperformed(adminService);
        
        tabs.add("Agregar servicio", addService);
        tabs.add("Servicios proximos",servicesUnperformed);
        tabs.add("Modificar servicio",modifyServiceTable);
        tabs.add("Eliminar servicio",removeServiceTable);
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
        setPreferredSize(new java.awt.Dimension(680, 690));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(ViewService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewService().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
    
    private AdminService adminService;
    private ViewAddService addService;
    private ViewServicesUnperformed servicesUnperformed; 
    private ViewModifyServicePanel modifyService;
    private ViewServiceSelectModifyPanel modifyServiceTable;
    private ViewRemoveServicePanel removeService;
    private ViewServiceSelectRemovePanel removeServiceTable;

}