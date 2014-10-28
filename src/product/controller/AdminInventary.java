/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product.controller;

import java.sql.SQLException;
import shared.model.DAOInventory;
import shared.model.DAOProduct;
import shared.model.Inventory;
import shared.model.Product;

/**
 *
 * @author Izanami
 */
public class AdminInventary {
    private DAOInventory daoInventory;
    private AdminProduct adminProduct;
    
    public AdminInventary() {
        daoInventory = new DAOInventory();
        adminProduct =  new AdminProduct(new DAOProduct());
    }
    
    public void addInventory(long barcode,
            String description, 
            double priceUnit, 
            String saleUnit, 
            String departament,
            int minimunStock, 
            int currentStock){
        
        try {
            Product product = new Product( barcode, description,  priceUnit, saleUnit, departament);
            adminProduct.addProduct(barcode, description, priceUnit, saleUnit, departament);
            Inventory inventory = new Inventory(product, minimunStock, currentStock);
            daoInventory.store(inventory);
        } catch (SQLException ex) {
            
        } 
    }
}
