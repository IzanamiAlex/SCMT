/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package product.view;

import javax.swing.JOptionPane;
import product.controller.AdminProduct;
import provider.controller.AdminProvider;

/**
 *
 * @author Izanami
 */
public class ViewAddProductPanel extends javax.swing.JPanel {

    /**
     * Creates new form ViewAddProviderPanel
     */
    public ViewAddProductPanel() {
        initComponents();
    }

    public ViewAddProductPanel(AdminProduct adminProduct) {
        initComponents();
        this.adminProduct = adminProduct;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        descriptionProductLabel = new javax.swing.JLabel();
        descriptionTextField = new javax.swing.JTextField();
        barcodeTextField = new javax.swing.JTextField();
        barcodeLabel = new javax.swing.JLabel();
        departamentLabel = new javax.swing.JLabel();
        saleUnitTextField = new javax.swing.JTextField();
        createProductButton = new javax.swing.JButton();
        moduleNameLabel = new javax.swing.JLabel();
        departamentTextField = new javax.swing.JTextField();
        saleUnitLabel = new javax.swing.JLabel();
        priceUnitLabel = new javax.swing.JLabel();
        priceUnitTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 500));

        titleLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(204, 204, 0));
        titleLabel.setText("NUEVO PRODUCTO");

        descriptionLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        descriptionLabel.setForeground(new java.awt.Color(204, 204, 0));
        descriptionLabel.setText("- Llene la siguiente informacion acerca del nuevo producto:");

        descriptionProductLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        descriptionProductLabel.setText("Descripción");

        descriptionTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        descriptionTextField.setPreferredSize(new java.awt.Dimension(10, 20));

        barcodeTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        barcodeTextField.setPreferredSize(new java.awt.Dimension(10, 20));

        barcodeLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        barcodeLabel.setText("Código de barras");

        departamentLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        departamentLabel.setText("Departamento");

        saleUnitTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        saleUnitTextField.setPreferredSize(new java.awt.Dimension(10, 20));

        createProductButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        createProductButton.setText("Crear Producto");
        createProductButton.setPreferredSize(new java.awt.Dimension(150, 30));
        createProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProvider(evt);
            }
        });

        moduleNameLabel.setBackground(new java.awt.Color(204, 204, 0));
        moduleNameLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        moduleNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        moduleNameLabel.setText("    PRODUCTOS");
        moduleNameLabel.setOpaque(true);
        moduleNameLabel.setPreferredSize(new java.awt.Dimension(600, 30));

        departamentTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        departamentTextField.setPreferredSize(new java.awt.Dimension(10, 20));

        saleUnitLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        saleUnitLabel.setText("Unidad de venta");

        priceUnitLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        priceUnitLabel.setText("Precio de venta");

        priceUnitTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        priceUnitTextField.setPreferredSize(new java.awt.Dimension(10, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(moduleNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(descriptionLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(descriptionProductLabel)
                            .addComponent(barcodeLabel)
                            .addComponent(saleUnitLabel)
                            .addComponent(priceUnitLabel)
                            .addComponent(departamentLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceUnitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saleUnitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departamentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(moduleNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barcodeLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionProductLabel)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceUnitLabel)
                    .addComponent(priceUnitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saleUnitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saleUnitLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departamentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departamentLabel))
                .addGap(91, 91, 91)
                .addComponent(createProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createProvider(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProvider
        String description = descriptionTextField.getText();
        long barcode = Long.valueOf(barcodeTextField.getText());
        String saleUnit = saleUnitTextField.getText();
        double priceUnit = Double.valueOf(priceUnitTextField.getText());
        String departament = departamentTextField.getText();
        
        adminProduct.addProduct(barcode, description, priceUnit, saleUnit, departament);
        
        String message = "Se ha creado el producto con el codigo de barras: "+ 
            barcode + ".";
            JOptionPane.showMessageDialog(null, message);
    }//GEN-LAST:event_createProvider


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barcodeLabel;
    private javax.swing.JTextField barcodeTextField;
    private javax.swing.JButton createProductButton;
    private javax.swing.JLabel departamentLabel;
    private javax.swing.JTextField departamentTextField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel descriptionProductLabel;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JLabel moduleNameLabel;
    private javax.swing.JLabel priceUnitLabel;
    private javax.swing.JTextField priceUnitTextField;
    private javax.swing.JLabel saleUnitLabel;
    private javax.swing.JTextField saleUnitTextField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
    
    private AdminProduct adminProduct;
}
