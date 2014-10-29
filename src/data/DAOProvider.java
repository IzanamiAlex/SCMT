/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Provider;

/**
 *
 * @author Izanami
 */
public class DAOProvider extends AbstractDAO<Provider>{

    @Override
    protected String buildScriptStore(Provider provider){
        String scriptStore = "INSERT INTO provider(identifier, name, phone, address) "+
            "VALUES ($identifier$, '$name$', '$phone$', '$address$');";
        long indentifier=0;
        try {
            indentifier = nextIdentifierProvider();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        scriptStore = scriptStore.replace("$identifier$", Long.toString(indentifier));
        scriptStore = scriptStore.replace("$name$", provider.getName());
        scriptStore = scriptStore.replace("$phone$", provider.getPhone());
        scriptStore = scriptStore.replace("$address$", provider.getAddres());
        return scriptStore;
    }
    
    @Override
    protected String buildScriptUpdate(Provider provider){
        
        String scriptUpdate = "UPDATE provider\n" +
            "   SET  name = '$name$', phone = '$phone$', address = '$address$'\n" +
            " WHERE identifier = $identifier$;";
        scriptUpdate = scriptUpdate.replace("$identifier$", 
            Long.toString(provider.getIdentifier()));
        scriptUpdate = scriptUpdate.replace("$name$", provider.getName());
        scriptUpdate = scriptUpdate.replace("$phone$", provider.getPhone());
        scriptUpdate = scriptUpdate.replace("$address$", provider.getAddres());
        
        return scriptUpdate;
    }

    @Override
    protected String buildScriptDelete(Provider provider) {
        String scriptDelete = "DELETE FROM provider WHERE identifier = $identifier$";
        scriptDelete = scriptDelete.replace("$identifier$", 
            Long.toString(provider.getIdentifier()));
        return scriptDelete;
    }
    
    private long nextIdentifierProvider() throws SQLException{
        long identifier = 0;
        
        /******************
         ** Build script **
         ******************/
        
        String scriptIdentifier = "SELECT nextval('serial_id_provider')";
        
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

    @Override
    protected String buildScriptFind(String identifier) {
        String scriptFind = "SELECT * FROM provider WHERE identifier = $identifier$;";
        scriptFind = scriptFind.replace("$identifier$", identifier);
        return scriptFind;
    }

    @Override
    protected String buildScriptLoad(String name) {
        String scriptLoad = "SELECT * FROM provider WHERE name ILIKE '%$name$%';";
        scriptLoad = scriptLoad.replace("$name$", name);
        return scriptLoad;
    }

    @Override
    protected Set<Provider> buildEntitys(ResultSet resultQuery) throws SQLException {
        Set<Provider> setProviders = new HashSet<>();
        while(resultQuery.next()){
            long identifierProvider = resultQuery.getLong("identifier");
            String nameProvider = resultQuery.getString("name");
            String phoneProvider = resultQuery.getString("phone");
            String addressProvider = resultQuery.getString("address");
            Provider provider = new Provider(identifierProvider, nameProvider, 
                phoneProvider, addressProvider);
            setProviders.add(provider);
        }
        return setProviders;
    }
}
