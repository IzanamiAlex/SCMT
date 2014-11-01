/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Izanami
 */
public class Product {

    public Product(long barcode,
            String description,
            double priceUnit,
            String salesUnit,
            String departament,
            double currentStock,
            double minimunStock) {
        this.barcode = barcode;
        this.description = description;
        this.priceUnit = priceUnit;
        this.salesUnit = salesUnit;
        this.departament = departament;
        this.currentStock = currentStock;
        this.minimunStock = minimunStock;
    }

    /**
     * @return the barcode
     */
    public long getBarcode() {
        return barcode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the priceUnit
     */
    public double getPriceUnit() {
        return priceUnit;
    }

    /**
     * @return the salesUnit
     */
    public String getSalesUnit() {
        return salesUnit;
    }

    /**
     * @return the departament
     */
    public String getDepartament() {
        return departament;
    }

    /**
     * @return the currentStock
     */
    public double getCurrentStock() {
        return currentStock;
    }

    /**
     * @return the minimunStock
     */
    public double getMinimunStock() {
        return minimunStock;
    }

    public int hashCode() {
        return (int)(barcode);
    }

    @Override
    public boolean equals(Object obj) {
        if(Product.class.isInstance(obj)){
            Product p = (Product)obj;
            return  p.getBarcode()==this.barcode;
        }else return false;
    }

    private long barcode;
    private String description;
    private double priceUnit;
    private String salesUnit;
    private String departament;
    private double currentStock;
    private double minimunStock;
}
