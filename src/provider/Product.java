/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider;

/**
 *
 * @author Izanami
 */
public class Product {
    private long barcode;
    private String nameProduct;
    private String description;
    private double priceUnit;

    public Product(long barcode, String nameProduct, String description, double priceUnit) {
        this.barcode = barcode;
        this.nameProduct = nameProduct;
        this.description = description;
        this.priceUnit = priceUnit;
    }

    /**
     * @return the barcode
     */
    public long getBarcode() {
        return barcode;
    }

    /**
     * @return the nameProduct
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the precioUnit
     */
    public double getPrecioUnit() {
        return priceUnit;
    }

    /**
     * @param precioUnit the precioUnit to set
     */
    public void setPrecioUnit(double precioUnit) {
        this.priceUnit = precioUnit;
    }
    
    
}
