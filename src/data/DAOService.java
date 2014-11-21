/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static data.ServiceConstants.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Service;


/**
 *
 * @author BuiRai
 */
public class DAOService extends AbstractDAO<Service>{

    @Override
    protected String buildScriptStore(Service service) {
        String scriptStore = "INSERT INTO service("+IDENTIFIER+", "+DESCRIPTION+
                ", "+CHARACTERISTICS+", "+ESTIMATE_PRICE+", "+DATE_RECEIPT+", "+
                DATE_DELIVERY+", "+IS_DID+", "+IS_DELIVERED+") "+
                "VALUES ($"+IDENTIFIER+"$, '$"+DESCRIPTION+
                "$', '$"+CHARACTERISTICS+"$', $"+ESTIMATE_PRICE+"$, '$"+DATE_RECEIPT+
                "$', '$"+DATE_DELIVERY+"$', $"+IS_DID+"$, $"+IS_DELIVERED+"$);";
        long identifier = 0;
        try {
            identifier = nextIdentifierService();
        } catch (SQLException ex) {
            Logger.getLogger(DAOService.class.getName()).log(Level.SEVERE, null, ex);
        }
        scriptStore = scriptStore.replace("$"+IDENTIFIER+"$", Long.toString(identifier));
        scriptStore = scriptStore.replace("$"+DESCRIPTION+"$", service.getDescription());
        scriptStore = scriptStore.replace("$"+CHARACTERISTICS+"$", service.getCharacteristics());
        scriptStore = scriptStore.replace("$"+ESTIMATE_PRICE+"$", Double.toString(service.getEstimatePrice()));
        scriptStore = scriptStore.replace("$"+DATE_RECEIPT+"$", String.valueOf(service.getDateOfReceipt()));
        scriptStore = scriptStore.replace("$"+DATE_DELIVERY+"$", String.valueOf(service.getDateOfDelivery()));
        scriptStore = scriptStore.replace("$"+IS_DID+"$", String.valueOf(service.isDid()));
        scriptStore = scriptStore.replace("$"+IS_DELIVERED+"$", String.valueOf(service.isDelivery()));
        
        return scriptStore;
    }

    @Override
    protected String buildScriptUpdate(Service service) {
        String scriptUpdate = "UPDATE service\n" +
            "   SET  "+DESCRIPTION+" = '$"+DESCRIPTION+"$', "+CHARACTERISTICS+" = '$"+CHARACTERISTICS+
                "$', "+ESTIMATE_PRICE+" = $"+ESTIMATE_PRICE+"$,  "+DATE_RECEIPT+" = '$"+DATE_RECEIPT+
                "$', "+DATE_DELIVERY+" = '$"+DATE_DELIVERY+"$', "+IS_DID+" = $"+IS_DID+
                "$, "+IS_DELIVERED+" = $"+IS_DELIVERED+"$\n" +
            " WHERE "+IDENTIFIER+" = $"+IDENTIFIER+"$;";

        scriptUpdate = scriptUpdate.replace("$"+IDENTIFIER+"$", 
            Long.toString(service.getId()));
        scriptUpdate = scriptUpdate.replace("$"+DESCRIPTION+"$", service.getDescription());
        scriptUpdate = scriptUpdate.replace("$"+CHARACTERISTICS+"$", service.getCharacteristics());
        scriptUpdate = scriptUpdate.replace("$"+ESTIMATE_PRICE+"$", Double.toString(service.getEstimatePrice()));
        scriptUpdate = scriptUpdate.replace("$"+DATE_RECEIPT+"$", String.valueOf(service.getDateOfReceipt()));
        scriptUpdate = scriptUpdate.replace("$"+DATE_DELIVERY+"$", String.valueOf(service.getDateOfDelivery()));
        scriptUpdate = scriptUpdate.replace("$"+IS_DID+"$", String.valueOf(service.isDid()));
        scriptUpdate = scriptUpdate.replace("$"+IS_DELIVERED+"$", String.valueOf(service.isDelivery()));
        
        return scriptUpdate;
    }

    @Override
    protected String buildScriptDelete(Service service) {
        String scriptDelete = "DELETE FROM service WHERE "+IDENTIFIER+" = $"+IDENTIFIER+"$";
        scriptDelete = scriptDelete.replace("$"+IDENTIFIER+"$", 
            Long.toString(service.getId()));
        return scriptDelete;
    }

    @Override
    protected String buildScriptFind(String identifier) {
        String scriptFind = "SELECT * FROM service WHERE "+IDENTIFIER+" = $"+IDENTIFIER+"$;";
        scriptFind = scriptFind.replace("$"+IDENTIFIER+"$", identifier);
        return scriptFind;
    }

    @Override
    protected String buildScriptLoad(String description) {
        String scriptLoad = "SELECT * FROM service WHERE "+DESCRIPTION+" ILIKE '%$"+DESCRIPTION+"$%';";
        scriptLoad = scriptLoad.replace("$"+DESCRIPTION+"$", description);
        return scriptLoad;
    }
    
    public String getScriptServicesUnperformed(){
        return "SELECT * FROM service WHERE "+IS_DID+" = false;";
    }
    
    public Set<Service> loadServicesUnperformed() throws SQLException{
        String script = getScriptServicesUnperformed();
        Set<Service> servicesUnperformed = executeQueryServices(script);
        return servicesUnperformed;
    }
    
    public Set<Service> executeQueryServices(String query) throws SQLException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultQuery = statement.executeQuery(query);
        Set<Service> servicesUnperformed =  buildEntitys(resultQuery);
        statement.close();
        closeConnection(connection);
        return servicesUnperformed;
    }
    
    @Override
    protected Set<Service> buildEntitys(ResultSet resultQuery) throws SQLException {
        Set<Service> setService = new HashSet<>();
        while(resultQuery.next()){
            long identifierService = resultQuery.getLong(IDENTIFIER);
            String descriptionService = resultQuery.getString(DESCRIPTION);
            String characteristicsService = resultQuery.getString(CHARACTERISTICS);
            double estimatePriceProduct = resultQuery.getDouble(ESTIMATE_PRICE);
            Date dateReceipt = resultQuery.getDate(DATE_RECEIPT);
            Date dateDelivery = resultQuery.getDate(DATE_DELIVERY);
            boolean isDid = resultQuery.getBoolean(IS_DID);
            boolean isDeliveried = resultQuery.getBoolean(IS_DELIVERED);
            Service service = new Service(identifierService, descriptionService, 
                    characteristicsService, estimatePriceProduct, dateReceipt, 
                    dateDelivery,isDid,isDeliveried);
            setService.add(service);
        }
        return setService;
    }

    private long nextIdentifierService() throws SQLException {
        long identifier = 0;
        
        /******************
         ** Build script **
         ******************/
        
        String scriptIdentifier = "SELECT nextval('serial_id_service')";
        
        /******************************
         ** Get the next identifier. **
         ******************************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptIdentifier);
        resultSet.next();
        identifier = resultSet.getLong(1);
        
        return identifier;
    }
    
}
