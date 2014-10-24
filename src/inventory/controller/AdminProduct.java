/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import shared.model.DAOProduct;
import shared.model.Product;

/**
 *
 * @author BuiRai
 */
public class AdminProduct {
    private DAOProduct daoProduct;

    public AdminProduct(DAOProduct dataProduct) {
        this.daoProduct = dataProduct;
    }
    
    public Map<String, String> getDataProduct(String barcode){
        Map<String, String> dataProduct = null;
        try {
            Product product = daoProduct.find(barcode);
            dataProduct = new HashMap<>();
            dataProduct.put("barcode", Long.toString(product.getBarcode()));
            dataProduct.put("description", product.getDescription());
            dataProduct.put("priceUnit", Double.toString(product.getPriceUnit()));
            dataProduct.put("priceSaleUnit", product.getSalesUnit());
            dataProduct.put("departament", product.getDepartament());
            
        } catch (SQLException ex) {
            
        }
        return dataProduct;
    }
    
    public Object[][] getProductList(String description){
        Object dataProduct[][] = null;
        try {
            Set<Product> products = daoProduct.load(description);
            dataProduct = new Object[products.size()][2];
            int index = 0;
            for (Product product : products) {
                dataProduct[index][0] = product.getBarcode();
                dataProduct[index][1] = product.getDescription();
                index++;
            }
        } catch (SQLException ex) {
            
        }
        return dataProduct;
    }
    
    public void addProduct(long barcode,String description, double priceUnit, String saleUnit, String departament){
        
        try {
            Product product = new Product( barcode, description,  priceUnit, saleUnit, departament);
            barcode = daoProduct.store(product);
        } catch (SQLException ex) {
            
        } 
    }
    
    public void modifyProduct(long barcode,String description, double priceUnit, String salesUnit, String departament){
        try {
            Product product = new Product( barcode, description,  priceUnit, salesUnit, departament);
            daoProduct.update(product);
        } catch (SQLException ex) {
            
        }
    }
    
    public void removeProduct(String barcode){
        try {
            Product product = daoProduct.find(barcode);
            daoProduct.delete(product);
        } catch (SQLException ex) {
            
        }
    }
}
