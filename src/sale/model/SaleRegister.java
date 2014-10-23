/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sale.model;


import java.util.Date;
import java.util.List;
import shared.model.Product;
import java.util.Calendar;


/**
 *
 * @author A12216422
 */
public class SaleRegister {
    
    public SaleRegister() {
        obtenerFecha();
    }
    
    public void addProduct(Product product){
        productList.add(product);
    }

    public void setSaleNumber(long saleNumber) {
        this.saleNumber = saleNumber;
    }

    public void setCharge_total(double charge_total) {
        this.charge_total = charge_total;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }
    
    public void obtenerFecha(){
        Calendar calendario = Calendar.getInstance();
        sale_date = calendario.getTime();
    }
    
    public void removeProduct(Product product){
        productList.remove(product);
    }
    
    public boolean isCharged(){
        return charged;
        
    }

    public long getSaleNumber() {
        return saleNumber;
    }

    public double getCharge_total() {
        return charge_total;
    }

    public Date getSale_date() {
        return sale_date;
    }
    
    public double getTotalPrice(){
        double totalPrice = 0;

        for (int i = 0; i < productList.size(); i++) {
            totalPrice += productList.get(i).getPriceUnit();
        }
        return totalPrice;
    }
    
    public void setCharged(boolean charged){
        this.charged = charged;
    }
    
    public List<Product> getProductList(){
        return productList;
        
    }
   
    private long saleNumber;
    private double charge_total;
    private boolean charged;
    private List<Product> productList;
    private Date sale_date;
 
}
