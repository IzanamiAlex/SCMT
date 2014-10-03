/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import provider.model.DAOProvider;
import provider.model.Provider;

/**
 *
 * @author Izanami
 */
public class AdminProvider {

    private DAOProvider dataProviders;

    public AdminProvider(DAOProvider dataProviders) {
        this.dataProviders = dataProviders;
    }
    
    public Provider locateProvider(){
        return null;
    }
    
    public Object[][] getProviderList(String name){
        return null;
    }
    
    public int addProvider(String name,String address, String phone){
        int indentifier=0;
        try {
            Provider provider = new Provider(name, phone, address);
            indentifier = dataProviders.store(provider);
        } catch (SQLException ex) {
            Logger.getLogger(AdminProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return indentifier; 
    }
    
    public void modifyProvider(String identifier, String name,String address, String phone){
        
    }
    
    public void removeProvider(String identifier){
        try {
            Provider provider = dataProviders.find(identifier);
            dataProviders.delete(provider);
        } catch (SQLException ex) {
            Logger.getLogger(AdminProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
