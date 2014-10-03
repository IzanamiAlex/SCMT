/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider.model;

import shared.model.AbstractDAO;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Izanami
 */
public class DAOProvider extends AbstractDAO<Provider>{
    
    @Override
    public int store(Provider provider) throws SQLException {
        
        /******************
         ** Build script **
         ******************/
        
        String scriptStore = "INSERT INTO provider(identifier, name, phone, address) "+
            "VALUES ($identifier$, '$name$', '$phone$', '$address$');";
        long identifier = nextIdentifierProvider();
        scriptStore = scriptStore.replace("$identifier$", Long.toString(identifier));
        scriptStore = scriptStore.replace("$name$", provider.getName());
        scriptStore = scriptStore.replace("$phone$", provider.getPhone());
        scriptStore = scriptStore.replace("$address$", provider.getAddres());
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(scriptStore);
        statement.close();
        closeConnection(connection);
        
        return (int)identifier;
    }

    @Override
    public int update(Provider provider) throws SQLException {
        
        /******************
         ** Build script **
         ******************/
        
        String scriptUpdate = "UPDATE provider\n" +
            "   SET  name = '$name$', phone = '$phone$', address = '$address$'\n" +
            " WHERE identifier = $identifier$;";
        scriptUpdate = scriptUpdate.replace("$identifier$", 
            Long.toString(provider.getIdentifier()));
        scriptUpdate = scriptUpdate.replace("$name$", provider.getName());
        scriptUpdate = scriptUpdate.replace("$phone$", provider.getPhone());
        scriptUpdate = scriptUpdate.replace("$address$", provider.getAddres());
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(scriptUpdate);
        statement.execute();
        statement.close();
         
        return (int) provider.getIdentifier();
    }

    @Override
    public int delete(Provider provider) throws SQLException {
        int numRowsModify = 0;
        
        /******************
         ** Build script **
         ******************/
        
        String scriptDelete = "DELETE FROM provider WHERE identifier = $identifier$";
        scriptDelete = scriptDelete.replace("$identifier$", 
            Long.toString(provider.getIdentifier()));
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        numRowsModify=statement.executeUpdate(scriptDelete);
        statement.close();
        closeConnection(connection);
        return numRowsModify;
    }

    @Override
    public Provider find(String identifier) throws SQLException {

        /******************
         ** Build script **
         ******************/
        
        String scriptFind = "SELECT * FROM provider WHERE identifier = $identifier$;";
        scriptFind = scriptFind.replace("$identifier$", identifier);
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptFind);
        resultSet.next();
        
        /********************
         ** Build provider **
         ********************/
        
        long identifierProvider = resultSet.getLong("identifier");
        String nameProvider = resultSet.getString("name");
        String phoneProvider = resultSet.getString("phone");
        String addressProvider = resultSet.getString("address");
        Provider provider = new Provider(identifierProvider, nameProvider, 
            phoneProvider, addressProvider);
        
        /**********************
         ** Close connection **
         **********************/
        
        statement.close();
        closeConnection(connection);
        
        return provider;
    }
    
    @Override
    public Set<Provider> load(String name) throws SQLException {
        
        /******************
         ** Build script **
         ******************/
        
        String scriptLoad = "SELECT * FROM provider WHERE name ILIKE '%$name$%';";
        scriptLoad = scriptLoad.replace("$name$", name);
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptLoad);
        
        /********************
         ** Build set of providers**
         ********************/
        
        Set<Provider> setProviders = new HashSet<>();
        while(resultSet.next()){
            long identifierProvider = resultSet.getLong("identifier");
            String nameProvider = resultSet.getString("name");
            String phoneProvider = resultSet.getString("phone");
            String addressProvider = resultSet.getString("address");
            Provider provider = new Provider(identifierProvider, nameProvider, 
                phoneProvider, addressProvider);
            setProviders.add(provider);
        }
        
        /**********************
         ** Close connection **
         **********************/
        
        statement.close();
        closeConnection(connection);
        
        return setProviders;
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
}
