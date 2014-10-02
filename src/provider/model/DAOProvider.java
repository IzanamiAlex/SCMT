/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package provider.model;

import core.AbstractDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

/**
 *
 * @author Izanami
 */
public class DAOProvider extends AbstractDAO<Provider>{
    
    @Override
    public int store(Provider provider) throws SQLException {
        Connection con = getConnection();
        
        String scriptStore = "INSERT INTO provider(indentifier, name, phone, address) "
            + "VALUES ($indentifier$, $name$, $phone$, $address$);";
        long indentifier = nextIdentifierProvider();
        scriptStore = scriptStore.replace("$indentifier$", Long.toString(indentifier));
        scriptStore = scriptStore.replace("$name$", provider.getName());
        scriptStore = scriptStore.replace("$phone$", provider.getPhone());
        scriptStore = scriptStore.replace("$address$", provider.getAddres());
        
        Statement sentencia = con.createStatement();
        sentencia.executeUpdate(scriptStore);
        sentencia.close();
        closeConnection(con);
        return (int)indentifier;
    }

    @Override
    public int update(Provider entity) throws SQLException {
        
        return 0;
    }

    @Override
    public int delete(Provider entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Provider find(String identifier) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Provider> load(String condition) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
