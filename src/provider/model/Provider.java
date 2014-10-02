/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider.model;

import java.sql.Date;

/**
 *
 * @author Izanami
 */

/*
 * Esta clase representa la abstraccion de un proveedor
 */

public class Provider {
    private String name;
    private String phone;
    private String addres;
    private Date dateProvision;
    private Date dayRangeProvision;
    
    public Provider(){
        
    }

    public Provider(String name, 
            String phone, 
            String addres, 
            Date dateProvision, 
            Date dayRangeProvision) {
        this.name = name;
        this.phone = phone;
        this.addres = addres;
        this.dateProvision = dateProvision;
        this.dayRangeProvision = dayRangeProvision;
    }

    /**
     * @return the nameProvider
     */
    public String getName() {
        return name;
    }

    /**
     * @return the poneProvider
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phoneProvider the phoneProvider to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the addresProvider
     */
    public String getAddres() {
        return addres;
    }

    /**
     * @return the dateProvider
     */
    public Date getDateProvision() {
        return dateProvision;
    }

    /**
     * @param dateProvider the dateProvider to set
     * 
     */
    public void setDateProvision(Date dateProvision) {
        this.dateProvision = dateProvision;
    }
    
        /**
     * @return the dayRangeProvision
     */
    public Date getDayRangeProvision() {
        return dayRangeProvision;
    }
    
}
