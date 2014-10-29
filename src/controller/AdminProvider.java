/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import data.DAOProvider;
import model.Provider;

/**
 *
 * @author Izanami
 */
public class AdminProvider {

    private DAOProvider daoProviders;

    public AdminProvider(DAOProvider dataProviders) {
        this.daoProviders = dataProviders;
    }
    
    public Map<String, String> getDataProvider(String identifier){
        Map<String, String> dataProvider = null;
        try {
            Provider provider = daoProviders.find(identifier);
            dataProvider = new HashMap<>();
            dataProvider.put("identifier", Long.toString(provider.getIdentifier()));
            dataProvider.put("name", provider.getName());
            dataProvider.put("phone", provider.getPhone());
            dataProvider.put("address", provider.getAddres());
        } catch (SQLException ex) {
            
        }
        return dataProvider;
    }
    
    public Object[][] getProviderList(String name){
        Object dataProviders[][] = null;
        try {
            Set<Provider> providers = daoProviders.load(name);
            dataProviders = new Object[providers.size()][2];
            int index = 0;
            for (Provider provider : providers) {
                dataProviders[index][0] = provider.getIdentifier();
                dataProviders[index][1] = provider;
                index++;
            }
        } catch (SQLException ex) {
            
        }
        return dataProviders;
    }
    
    public int addProvider(String name, String address, String phone){
        int indentifier = 0;
        try {
            Provider provider = new Provider(name, phone, address);
            indentifier = daoProviders.store(provider);
        } catch (SQLException ex) {
            
        }
        return indentifier; 
    }
    
    public void modifyProvider(String identifier, String name, String address, String phone){
        try {
            long id = Long.valueOf(identifier);
            Provider provider = new Provider(id, name, phone, address);
            daoProviders.update(provider);
        } catch (SQLException ex) {
            
        }
    }
    
    public void removeProvider(String identifier){
        try {
            Provider provider = daoProviders.find(identifier);
            daoProviders.delete(provider);
        } catch (SQLException ex) {
            
        }
    }
}
