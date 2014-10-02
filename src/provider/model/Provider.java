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
    private String address;

    public Provider() {
        name=null;
        phone=null;
        address=null;
    }

    public Provider(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    @Override
    public String toString(){
        return name;
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
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the addresProvider
     */
    public String getAddres() {
        return address;
    }
}
