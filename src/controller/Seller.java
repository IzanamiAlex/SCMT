/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.DAOSaleRegister;
import model.SaleRegister;
import data.DAOProduct;
import model.Note;
import model.Printer;
import model.Product;

/**
 *
 * @author A12216422
 */
public class Seller {

    public Seller(){
        daoProduct = new DAOProduct();
        daoSaleRegis = new DAOSaleRegister();
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
            dataProducts[rowNumber][BARCODE] = product.getBarcode();
            dataProducts[rowNumber][DESCRIPTION] = product.getDescription();
            dataProducts[rowNumber][PRICE_UNIT] = product.getPriceUnit();
            dataProducts[rowNumber][QUANTITY] = productList.get(product);
            rowNumber++;
        }

        return dataProducts;
    }

    public void addProduct(long barcode){
        try {
            Product product = daoProduct.find(((Long)barcode).toString());
//            Map<Product,Integer> productList = saleRegister.getProductList();
//            if ( productList.containsKey(product) ) {
//                int quantity = productList.get(product)+1;
//                productList.put(product, quantity);
//            }else{
//                productList.put(product, 1);
//            }
            saleRegister.addProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeProduct(long barcode){
        try {
            Product product = daoProduct.find(((Long)barcode).toString());
//            Map<Product,Integer> productList = saleRegister.getProductList();
//            if ( productList.containsKey(product) ) {
//                if ( productList.get(product) > 1 ) {
//                    int quantity = productList.get(product)-1;
//                    productList.put(product, quantity);
//                }else{
//                    productList.remove(product);
//                }
//            }
            saleRegister.removeProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, null, ex);
        }
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



    public double returnChange(double moneyClient){
        double charge = moneyClient - getTotalCost();
        if (charge>=0) {
            saleRegister.setCharged(true);
            finishSaleRegister();
        }
        return charge;
    }

    public void createNote(){
        Note note = new Note();
        note.setDate();
        note.setProductListDescription(getProductListDescription());
        note.buildNote();
        printNote(note.getBodyNote());
    }

    public void printNote(String bodyNote){
        Printer printer = new Printer();
        printer.printNote(bodyNote);
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

    public String getProductListDescription(){
        String productListDescription = "";

        Map<Product, Integer> productList = saleRegister.getProductList();
        Set<Product> products = productList.keySet();
        for(Product product : products){
            double dbl_priceByProductClass = 0;
            String productDescription = product.getDescription();
            int int_productQuantity = productList.get(product);
            String str_productQuantity = String.valueOf(int_productQuantity);
            dbl_priceByProductClass = product.getPriceUnit() * int_productQuantity;
            String str_priceByProductClass = String.valueOf(dbl_priceByProductClass);
            productListDescription += productDescription + "\t" +
                    str_productQuantity + "\t" +
                    str_priceByProductClass + "\n";
        }

        return productListDescription;
    }

    private DAOProduct daoProduct;
    private DAOSaleRegister daoSaleRegis;
    private SaleRegister saleRegister;

    private static final int BARCODE = 0;
    private static final int DESCRIPTION =1;
    private static final int PRICE_UNIT = 2;
    private static final int QUANTITY = 3;
}

