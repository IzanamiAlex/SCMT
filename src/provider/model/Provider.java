/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider.model;

/**
 *
 * @author Izanami
 */

/*
 * Esta clase representa la abstraccion de un proveedor
 */

public class Provider {
    
    public Provider() {
        this.identifier = 0;
        this.name = null;
        this.phone = null;
        this.address = null;
    }

    public Provider(String name, String phone, String address) {
        this.identifier = 0;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    public Provider(long identifier, String name, String phone, String address) {
        this.identifier = identifier;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    @Override
    public int hashCode(){
        return  (int)identifier;
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
     * @return the addresProvider
     */
    public String getAddres() {
        return address;
    }
    
    /**
     * @return the indentifier
     */  
    public long getIdentifier(){
        return identifier;
    }
    
    private long identifier;
    private String name;
    private String phone;
    private String address;
}
