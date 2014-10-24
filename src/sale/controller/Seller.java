/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sale.controller;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import sale.model.DAOSaleRegister;
import sale.model.SaleRegister;
import shared.model.DAOProduct;
import shared.model.Product;

/**
 *
 * @author A12216422
 */
public class Seller {
    private DAOProduct daoProduct = new DAOProduct();
    private DAOSaleRegister daoSaleRegis = new DAOSaleRegister();
    private SaleRegister saleRegister;
    
    public Seller(){
        
    }
    
    public void createRegister(){
        saleRegister = new SaleRegister();
    }
    
    public double getTotalCost(){
        return saleRegister.getTotalCost();
    }
    
    public Object[][] getProductList(){
        Map<Product, Integer> productList = saleRegister.getProductList();
        Set<Product> products = productList.keySet();
        Object[][] dataProducts = new Object[productList.size()][4];
        
        int rowNumber = 0;
        for( Product product : products ){
            dataProducts[rowNumber][0] = product.getBarcode();
            dataProducts[rowNumber][1] = product.getDescription();
            dataProducts[rowNumber][2] = product.getPriceUnit();
            dataProducts[rowNumber][3] = productList.get(product);
            rowNumber++;
        }
        
        return dataProducts;
    }
    
    public void addProduct(long barcode){
        Product product = findProduct(barcode);
        saleRegister.addProduct(product);
    }
    
    public Product findProduct(long barcode){
        String barcode_str = String.valueOf(barcode);
        Product product = null;
        try {
            product = daoProduct.find(barcode_str);
        } catch (SQLException ex) {
            Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    
    public double getCostProductQuantity(long barcode){
        Map<Product, Integer> productList = saleRegister.getProductList();
        Set<Product> products = productList.keySet();
        
        for( Product product : products ){
            if (product.getBarcode() == barcode) {
                int productQuantity = productList.get(product);
                return product.getPriceUnit() * productQuantity;
            }
        }
        return 0;
    }
    
    public void removeProduct(long barcode){
        Product product = findProduct(barcode);
        saleRegister.removeProduct(product);
    }
 
    public double returnChange(double moneyClient){
        double charge = moneyClient - getTotalCost();
        if (charge>=0) {
            saleRegister.setCharged(true);
            finishSaleRegister();
        }
        return charge;
    }
 
    public void cancelSaleRegister(){
        this.saleRegister = null;
    }
    
    public void finishSaleRegister(){
        if (saleRegister.isCharged()) {
            try {
                daoSaleRegis.store(saleRegister);
            } catch (SQLException ex) {
                Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            cancelSaleRegister();
        }
    }
    
    public SaleRegister getSaleRegister(){
        return saleRegister;
    }

}

