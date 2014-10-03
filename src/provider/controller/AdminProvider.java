/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import provider.model.DAOProvider;
import provider.model.Provider;

/**
 *
 * @author Izanami
 */
public class AdminProvider {

    private DAOProvider daoProviders;

    public AdminProvider(DAOProvider dataProviders) {
        this.daoProviders = dataProviders;
    }
    
    public Map<String,String> locateProvider(String identifier){
        Map<String,String> dataProvider = null;
        try {
            Provider provider = daoProviders.find(identifier);
            dataProvider = new HashMap<>();
            dataProvider.put("indentifier", Long.toString(provider.getIndentifier()));
            dataProvider.put("name",provider.getName());
            dataProvider.put("phone",provider.getPhone());
            dataProvider.put("address",provider.getAddres());
        } catch (SQLException ex) {
            Logger.getLogger(AdminProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataProvider;
    }
    
    public Object[][] getProviderList(String name){
        Set<Provider> providers=null;
        Object dataProviders[][] = null;
        try {
            providers = daoProviders.load(name);
            dataProviders = new Object[providers.size()][2];
            int index = 0;
            
            System.out.print(providers.size());
            
            for (Provider provider : providers) {
                dataProviders[index][0] = provider.getIndentifier();
                dataProviders[index][1] = provider;
                index++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dataProviders;
    }
    
    public int addProvider(String name,String address, String phone){
        int indentifier=0;
        try {
            Provider provider = new Provider(name, phone, address);
            indentifier = daoProviders.store(provider);
        } catch (SQLException ex) {
            Logger.getLogger(AdminProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return indentifier; 
    }
    
    public void modifyProvider(String indentifier, String name,String address, String phone){
        try {
            Provider provider = new Provider(Long.valueOf(indentifier), name, phone, address);
            daoProviders.update(provider);
        } catch (SQLException ex) {
            Logger.getLogger(AdminProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeProvider(String identifier){
        try {
            Provider provider = daoProviders.find(identifier);
            daoProviders.delete(provider);
        } catch (SQLException ex) {
            Logger.getLogger(AdminProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
