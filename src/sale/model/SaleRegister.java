/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sale.model;


import java.util.Date;
import shared.model.Product;
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
        this.sale_date = calendario.getTime();
    }

    public SaleRegister(long saleNumber, Date sale_date, Map<Product, Integer> productList) {
        this.saleNumber = saleNumber;
        this.productList = productList;
        this.sale_date = sale_date;
    }  
    
    public void addProduct(Product product){
        if ( productList.containsKey(product) ) {
            int quantity = productList.get(product)+1;
            System.out.println("Aqui: "+ quantity);
            productList.put(product, quantity);
        }else{
            System.out.println(":v");
            productList.put(product, 1);
        }
    }
    
    public void removeProduct(Product product){
        if ( productList.containsKey(product) ) {
            if ( productList.get(product) > 1 ) {
                productList.put(product, productList.get(product)-1);
            }else{
                productList.remove(product);
            }
        }
    }
    
    public boolean isCharged(){
        return charged;
    }

    public long getSaleNumber() {
        return saleNumber;
    }

    public Date getSale_date() {
        return sale_date;
    }
    
    public double getTotalCost(){
        double totalCost = 0;
        Set<Product> products = productList.keySet();
        
        for( Product product : products ){
            double priceProduct = product.getPriceUnit();
            totalCost += priceProduct * (productList.get(product));
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
    private Date sale_date;
 
}
