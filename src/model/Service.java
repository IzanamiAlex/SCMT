/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author BuiRai
 */
public class Service {
    
    public Service(){
        this.id = 0;
        this.estimatePrice = 0;
    }
    
    public Service(long id, String description, 
            String characteristics, double estimatePrice, 
            Date dateOfReceipt, Date dateOfDelivery, boolean isDid,
            boolean isDeliveried) {
        this.id = id;
        this.description = description;
        this.characteristics = characteristics;
        this.estimatePrice = estimatePrice;
        this.dateOfReceipt = dateOfReceipt;
        this.dateOfDelivery = dateOfDelivery;
        this.isDid = isDid;
        this.isDelivered = isDeliveried;
    }

    public Service(long id, String description, 
            String characteristics, double estimatePrice, 
            Date dateOfReceipt, Date dateOfDelivery) {
        this.id = id;
        this.description = description;
        this.characteristics = characteristics;
        this.estimatePrice = estimatePrice;
        this.dateOfReceipt = dateOfReceipt;
        this.dateOfDelivery = dateOfDelivery;
        this.isDid = false;
        this.isDelivered = false;
    }
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the adicionalMaterials
     */
    public String getAdicionalMaterials() {
        return adicionalMaterials;
    }

    /**
     * @param adicionalMaterials the adicionalMaterials to set
     */
    public void setAdicionalMaterials(String adicionalMaterials) {
        this.adicionalMaterials = adicionalMaterials;
    }

    /**
     * @return the characteristics
     */
    public String getCharacteristics() {
        return characteristics;
    }

    /**
     * @param characteristics the characteristics to set
     */
    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    /**
     * @return the estimatePrice
     */
    public double getEstimatePrice() {
        return estimatePrice;
    }

    /**
     * @param estimatePrice the estimatePrice to set
     */
    public void setEstimatePrice(double estimatePrice) {
        this.estimatePrice = estimatePrice;
    }

    /**
     * @return the dateOfReceipt
     */
    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    /**
     * @param dateOfReceipt the dateOfReceipt to set
     */
    public void setDateOfReceipt(Date dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    /**
     * @return the dateOfDelivery
     */
    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    /**
     * @param dateOfDelivery the dateOfDelivery to set
     */
    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }
    
    @Override
    public String toString(){
        return "ID: " + id + "\n" +
                "Description: " + description + "\n" +
                "Materia adicional: " + adicionalMaterials + "\n" +
                "caracteristicas: " + characteristics + "\n" +
                "cotización: " + estimatePrice + "\n" +
                "fecha de recepcion: " + dateOfReceipt + "\n" +
                "fecha de entrega: " + dateOfDelivery + "\n";
    }

    /**
     * @return the isDo
     */
    public boolean isDid() {
        return isDid;
    }

    /**
     * @param isDid the isDo to set
     */
    public void setIsDid(boolean isDid) {
        this.isDid = isDid;
    }

    /**
     * @return the isDelivery
     */
    public boolean isDelivery() {
        return isDelivered;
    }

    /**
     * @param isDeliveried the isDelivery to set
     */
    public void setIsDeliveried(boolean isDeliveried) {
        this.isDelivered = isDeliveried;
    }
    
    private long id;
    private String description;
    private String adicionalMaterials;
    private String characteristics;
    private double estimatePrice;
    private Date dateOfReceipt;
    private Date dateOfDelivery;
    private boolean isDid;
    private boolean isDelivered;
    
}
