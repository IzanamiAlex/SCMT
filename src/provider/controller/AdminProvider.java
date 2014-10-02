/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider.controller;

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
        return 0;
    }
    
    public void modifyProvider(String identifier, String name,String address, String phone){
        
    }
    
    public void removerProvider(String identifier){
        
    }
}
