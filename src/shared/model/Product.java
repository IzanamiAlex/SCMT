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
public class Product {
    private long barcode;
    private String description;
    private double priceUnit;
    private String salesUnit;
    private String departament;

    public Product(long barcode, String description, double priceUnit, String salesUnit, String departament) {
        this.barcode = barcode;
        this.description = description;
        this.priceUnit = priceUnit;
        this.salesUnit = salesUnit;
        this.departament = departament;
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
    
    
}
