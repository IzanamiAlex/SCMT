/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

/**
 *
 * @author Izanami
 */
public class Inventory {
    private final Product product;
    private int stock_minimun;
    private int currentStock;

    public Inventory(Product product, int stock_minimun, int currentStock) {
        this.product = product;
        this.stock_minimun = stock_minimun;
        this.currentStock = currentStock;
    }
    
    public void increaseCurrentStock(int increment){
        currentStock = currentStock + increment;
    }
    
    public void decreaseCurrentStock(int decrement){
        currentStock = currentStock - decrement;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return the stock_minimun
     */
    public int getStock_minimun() {
        return stock_minimun;
    }

    /**
     * @param stock_minimun the stock_minimun to set
     */
    public void setStock_minimun(int stock_minimun) {
        this.stock_minimun = stock_minimun;
    }

    /**
     * @return the currentStock
     */
    public int getCurrentStock() {
        return currentStock;
    }

}
