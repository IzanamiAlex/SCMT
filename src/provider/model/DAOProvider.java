/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider.model;

import core.AbstractDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        
        String scriptStore = "INSERT INTO provider(indentifier, name, phone, address) "
            + "VALUES ($indentifier$, '$name$', '$phone$', '$address$');";
        long indentifier = nextIdentifierProvider();
        scriptStore = scriptStore.replace("$indentifier$", Long.toString(indentifier));
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
        
        return (int)indentifier;
    }

    @Override
    public int update(Provider provider) throws SQLException {
        
        /******************
         ** Build script **
         ******************/
        
        String scriptUpdate = "UPDATE provider\n" +
            "   SET  name = '$name$', phone = '$phone$', address = '$address$'\n" +
            " WHERE indentifier = $indentifier$;";
        scriptUpdate = scriptUpdate.replace("$indentifier$", 
            Long.toString(provider.getIndentifier()));
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
         
        return (int) provider.getIndentifier();
    }

    @Override
    public int delete(Provider provider) throws SQLException {
        int numRowsModify = 0;
        
        /******************
         ** Build script **
         ******************/
        
        String scriptDelete = "DELETE FROM provider WHERE indentifier = $indentifier$";
        scriptDelete = scriptDelete.replace("$indentifier$", 
                Long.toString(provider.getIndentifier()));
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        numRowsModify=statement.executeUpdate(scriptDelete);
        statement.close();
        closeConnection(connection);
        return numRowsModify;
    }

    @Override
    public Provider find(String indentifier) throws SQLException {
        Provider provider;
        Connection connection = getConnection();
        
        String scriptFind = "SELECT * FROM provider WHERE indentifier = $indentifier$;";
        scriptFind = scriptFind.replace("$indentifier$", indentifier);
        
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptFind);
        resultSet.next();
        
        long indentifierProvider = resultSet.getLong("indentifier");
        String nameProvider = resultSet.getString("name");
        String phoneProvider = resultSet.getString("phone");
        String addressProvider = resultSet.getString("address");
        provider = new Provider(indentifierProvider, nameProvider, phoneProvider, addressProvider);
        
        statement.close();
        closeConnection(connection);
        
        return provider;
    }
    //cambiar el nombre atributo por otro
    @Override
    public Set<Provider> load(String name) throws SQLException {
        Provider provider = null;
        Set<Provider> setProviders = new HashSet<>();
        Connection connection = getConnection();
        
        String scriptLoad = "SELECT * FROM provider WHERE name ILIKE '%$name$%';";
        scriptLoad = scriptLoad.replace("$name$", name);
        
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptLoad);
                
        while(resultSet.next()){
            long indentifierProvider = resultSet.getLong("indentifier");
            String nameProvider = resultSet.getString("name");
            String phoneProvider = resultSet.getString("phone");
            String addressProvider = resultSet.getString("address");
            provider = new Provider(indentifierProvider, nameProvider, phoneProvider, addressProvider);
            setProviders.add(provider);
        }
        statement.close();
        closeConnection(connection);
        return setProviders;
    }
    
    private long nextIdentifierProvider() throws SQLException{
        long indentifier = 0;
        Connection connection = getConnection();
        
        String queryString = "SELECT nextval('serial_id_provider')";
        Statement statement = null;
        ResultSet resultSet = null;
        /* Get the next identifier. */
        statement = connection.createStatement();
        resultSet = statement.executeQuery(queryString);
        resultSet.next();
        indentifier = resultSet.getLong(1);
        return indentifier;
    }
}
