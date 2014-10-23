/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sale.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    DAOProduct daoProduct = new DAOProduct();
    DAOSaleRegister daoSaleRegis = new DAOSaleRegister();
    private SaleRegister saleRegister;
    
    public void createRegister(){
        
    }
    
    public Date getDate(){
        Calendar calendario = Calendar.getInstance();
        Date sale_date = calendario.getTime();
        return sale_date;
    }
    
    public double getTotalPrice(){
        double totalPrice = 0;
        List<Product> productList = saleRegister.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            totalPrice += productList.get(i).getPriceUnit();
        }
        return totalPrice;
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
    
    public void removeProduct(long barcode){
        Product product = findProduct(barcode);
        saleRegister.removeProduct(product);
    }
 
    /**********************************************
    ** PENSAR EN UN BUEN NOMBRE PARA ESTE MÉTODO **
    ***********************************************/
    public double returnChange(double moneyClient){
        double charge = moneyClient - getTotalPrice();
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
            daoSaleRegis.addSaleRegister(saleRegister);
        }else{
            cancelSaleRegister();
        }
    }
    
    public SaleRegister getSaleRegister(){
        return saleRegister;
    }

}

