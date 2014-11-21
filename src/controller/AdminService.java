/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.DAOService;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Service;
import static data.ServiceConstants.*;

/**
 *
 * @author BuiRai
 */
public class AdminService {
    
    public AdminService() {
        service = new Service();
        daoService = new DAOService();
    }

    public AdminService(DAOService daoService) {
        service = new Service();
        this.daoService = daoService;
    }
    
    public void rebootService(){
        if (service == null) service = new Service();       
    }
    
    public void fillDatesOfService(HashMap<String, Object> serviceData) throws NumberFormatException{
        rebootService();
        
        //Obtiene cada dato de los datos del servicio
        Date dateOfReceipt = (Date)serviceData.get(DATE_RECEIPT);
        Date dateOfDelivery = (Date)serviceData.get(DATE_DELIVERY);
        String description = (String)serviceData.get(DESCRIPTION);
        String characteristics = (String)serviceData.get(CHARACTERISTICS);
        String str_estimatePrice = (String)serviceData.get(ESTIMATE_PRICE);
        double dbl_estimatePrice = Double.parseDouble(str_estimatePrice);
        
        //asigna al servicio los valores anteriormente encontrados
        service.setDateOfReceipt(dateOfReceipt);
        service.setDateOfDelivery(dateOfDelivery);
        service.setDescription(description);
        service.setCharacteristics(characteristics);
        service.setEstimatePrice(dbl_estimatePrice);
    }
    
    public long addService(){
        int identifier = 0;
        try {
            identifier = daoService.store(service);
            service = null;
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return identifier;
    }
   
    public Object[][] getServiceList(String desccription){
        Object dataProviders[][] = null;
        try {
            Set<Service> services = daoService.load(desccription);
            dataProviders = new Object[services.size()][NUMBER_COLUMNS];
            int index = 0;
            for (Service service : services) {
                dataProviders[index][SERVICE_IDENTIFIER] = service.getId();
                dataProviders[index][SERVICE_DESCRIPTION] = service.getDescription();
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataProviders;
    }
    
    public Map<String, String> getDataService(String identifier){
        Map<String, String> dataService = null;
        try {
            Service service = daoService.find(identifier);
            dataService = new HashMap<>();
            dataService.put(IDENTIFIER, Long.toString(service.getId()));
            dataService.put(DESCRIPTION, service.getDescription());
            dataService.put(CHARACTERISTICS, service.getCharacteristics());
            dataService.put(ESTIMATE_PRICE, Double.toString(service.getEstimatePrice()));
            dataService.put(DATE_RECEIPT, String.valueOf(service.getDateOfReceipt()));
            dataService.put(DATE_DELIVERY, String.valueOf(service.getDateOfDelivery()));
            dataService.put(IS_DID, String.valueOf(service.isDid()));
            dataService.put(IS_DELIVERED, String.valueOf(service.isDelivery()));
            
        } catch (SQLException ex) {
            
        }
        return dataService;
    }
    
    public void removeService(String identifier){
        try {
            Service service = daoService.find(identifier);
            daoService.delete(service);
        } catch (SQLException ex) {
            
        }
    }
    
    //REFACTORIZAR PASANDOLE EL OBJETO
    public void modifyService(String identifier, String description, 
            String characteristics, String estimatePrice, String dateReceipt, 
            String dateDelivery, boolean isDid, boolean isDeliveried){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            long id = Long.valueOf(identifier);
            double estimatPrice = Double.parseDouble(estimatePrice);
            Date dateOfReceipt = formatter.parse(dateReceipt);
            Date dateOfDelivery = formatter.parse(dateDelivery);
            Service service = new Service(id, description, characteristics,
                    estimatPrice, dateOfReceipt, dateOfDelivery,isDid,isDeliveried);
            daoService.update(service);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Object[][] getServicesUnperformed(){
        Object dataServicesUnperformed[][] = null;
        try {
            Set<Service> servicesUnperformed = daoService.loadServicesUnperformed();
            dataServicesUnperformed = new Object[servicesUnperformed.size()][NUMBER_DATAS_SERVICE];
            int index = 0;
            for (Service service : servicesUnperformed) {
                dataServicesUnperformed[index][SERVICE_IDENTIFIER] = service.getId();
                dataServicesUnperformed[index][SERVICE_DESCRIPTION] = service.getDescription();
                dataServicesUnperformed[index][SERVICE_CHARACTERISTICS] = service.getCharacteristics();
                dataServicesUnperformed[index][SERVICE_ESTIMATE_PRICE] = service.getEstimatePrice();
                dataServicesUnperformed[index][SERVICE_DATE_RECEIPT] = service.getDateOfReceipt();
                dataServicesUnperformed[index][SERVICE_DATE_DELIVERY] = service.getDateOfDelivery();
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataServicesUnperformed;
    }
    
    private Service service;
    private DAOService daoService;
    private static final int NUMBER_COLUMNS = 2;
    private static final int NUMBER_DATAS_SERVICE = 6;
    
    //Constants variables
    private static final int SERVICE_IDENTIFIER = 0;
    private static final int SERVICE_DESCRIPTION = 1;
    private static final int SERVICE_CHARACTERISTICS = 2;
    private static final int SERVICE_ESTIMATE_PRICE = 3;
    private static final int SERVICE_DATE_RECEIPT = 4;
    private static final int SERVICE_DATE_DELIVERY = 5;
    
}
