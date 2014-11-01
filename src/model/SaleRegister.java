/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author A12216422
 */
public class SaleRegister {

    public SaleRegister() {
        this.productList = new HashMap<>();

        Calendar calendario = Calendar.getInstance();
        this.saleDate = calendario.getTime();
    }

    public SaleRegister(long saleNumber, Date saleDate, Map<Product, Integer> productList) {
        this.saleNumber = saleNumber;
        this.productList = productList;
        this.saleDate = saleDate;
    }

//    public void addProduct(Product product){
//        if ( getProductList().containsKey(product) ) {
//            int quantity = getProductList().get(product)+1;
//            getProductList().put(product, quantity);
//        }else{
//            getProductList().put(product, 1);
//        }
//    }
//
//    public void removeProduct(Product product){
//        if ( getProductList().containsKey(product) ) {
//            if ( getProductList().get(product) > 1 ) {
//                int quantity = getProductList().get(product)-1;
//                getProductList().put(product, quantity);
//            }else{
//                getProductList().remove(product);
//            }
//        }
//    }

    public boolean isCharged(){
        return charged;
    }

    public long getSaleNumber() {
        return saleNumber;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public double getTotalCost(){
        double totalCost = 0;
        Set<Product> products = getProductList().keySet();

        for( Product product : products ){
            double priceProduct = product.getPriceUnit();
            totalCost += priceProduct * (getProductList().get(product));
        }

        return totalCost;
    }

    public void setCharged(boolean charged){
        this.charged = charged;
    }

    public Map<Product,Integer> getProductList(){
        return productList;

    }

    private long saleNumber;
    private boolean charged = false;
    private Map<Product,Integer> productList;
    private Date saleDate;

    /**
     * @param productList the productList to set
     */
    public void setProductList(Map<Product,Integer> productList) {
        this.productList = productList;
    }

    /**
     * @param saleDate the saleDate to set
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

}
